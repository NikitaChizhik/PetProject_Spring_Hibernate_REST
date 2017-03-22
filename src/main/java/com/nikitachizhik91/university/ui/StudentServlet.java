package com.nikitachizhik91.university.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.StudentManager;
import com.nikitachizhik91.university.domain.impl.StudentManagerImpl;
import com.nikitachizhik91.university.model.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentManager studentManager;

	public StudentServlet() {
		studentManager = new StudentManagerImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");

		if (command == null) {
			command = "findAll";
		}
		if (command.equals("add")) {
			addStudent(request, response);
		} else if (command.equals("load")) {
			loadStudent(request, response);
		} else {
			findAll(request, response);
		}

	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentId = request.getParameter("studentId");

		studentManager = new StudentManagerImpl();
		Student student = null;
		try {
			student = studentManager.findById(Integer.parseInt(studentId));
		} catch (NumberFormatException e) {
			// TODO
		} catch (DomainException e) {
			// TODO
		}

		request.setAttribute("loadStudent", student);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/updateStudentForm.jsp");
		dispatcher.forward(request, response);
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String name = request.getParameter("name");

		Student student = new Student();
		student.setName(name);

		studentManager = new StudentManagerImpl();
		try {
			studentManager.create(student);
		} catch (DomainException e) {
			// TODO
		}

		findAll(request, response);

	}

	private void findAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		studentManager = new StudentManagerImpl();
		List<Student> students = null;

		try {
			students = studentManager.findAll();
		} catch (DomainException e) {

			// TODO
		}

		request.setAttribute("allStudents", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/allStudents.jsp");
		dispatcher.forward(request, response);
	}

}
