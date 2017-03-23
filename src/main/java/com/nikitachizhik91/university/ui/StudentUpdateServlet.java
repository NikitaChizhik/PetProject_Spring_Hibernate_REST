package com.nikitachizhik91.university.ui;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentUpdateServlet")
public class StudentUpdateServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(StudentUpdateServlet.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		updateStudent(request, response);

	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String name = request.getParameter("name");
		Student student = null;
		StudentManager studentManager = new StudentManagerImpl();

		try {
			student = studentManager.findById(Integer.parseInt(request.getParameter("studentId")));
			student.setName(name);
			studentManager.update(student);

		} catch (NumberFormatException | DomainException e) {
			log.error("Cannot update student.", e);
			throw new ServletException("Cannot update student.", e);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentServlet");
		dispatcher.forward(request, response);

		log.trace("Dispathcer forwarded requeset and response to /Studentservlet.");
	}

}
