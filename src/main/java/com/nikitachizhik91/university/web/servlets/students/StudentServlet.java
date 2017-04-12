package com.nikitachizhik91.university.web.servlets.students;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.StudentManager;
import com.nikitachizhik91.university.domain.impl.StudentManagerImpl;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(StudentServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentId = request.getParameter("studentId");

		log.trace("Get request to find student by id=" + studentId);

		StudentManager studentManager = new StudentManagerImpl();
		Student student = null;

		try {
			student = studentManager.findById(Integer.parseInt(studentId));

		} catch (NumberFormatException e) {
			log.error("The id=" + studentId + " is wrong.", e);
			throw new WebException("The id=" + studentId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot find student by id=" + studentId, e);
			throw new WebException("Cannot find student by id=" + studentId, e);
		}

		request.setAttribute("student", student);
		request.getRequestDispatcher("/student.jsp").forward(request, response);

		log.trace("Found student by id=" + studentId);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentId = request.getParameter("studentId");
		String name = request.getParameter("name");

		log.trace("Post request to update student with id=" + studentId + " on name=" + name);

		StudentManager studentManager = new StudentManagerImpl();
		Student student = null;

		try {
			student = studentManager.findById(Integer.parseInt(studentId));
			student.setName(name);

			studentManager.update(student);

		} catch (DomainException e) {
			log.error("Cannot update student=" + student, e);
			throw new WebException("Cannot update student=" + student, e);

		} catch (NumberFormatException e) {
			log.error("The id=" + studentId + " is wrong.", e);
			throw new WebException("The id=" + studentId + " is wrong.", e);
		}

		response.sendRedirect("student?studentId=" + studentId);

		log.trace("Updated student with id=" + studentId + " on name=" + name);
	}
}
