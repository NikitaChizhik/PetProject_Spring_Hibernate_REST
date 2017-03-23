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
@WebServlet("/StudentAddServlet")
public class StudentAddServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(StudentAddServlet.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		addStudent(request, response);

		log.info("Added student.");
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.trace("Started addStudent() method.");

		String name = request.getParameter("name");

		Student student = new Student();
		student.setName(name);

		StudentManager studentManager = new StudentManagerImpl();

		try {
			studentManager.create(student);

		} catch (DomainException e) {

			log.error("Cannot addStudent student.", e);
			throw new ServletException("Cannot addStudent student.", e);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentServlet");
		dispatcher.forward(request, response);

		log.trace("Dispathcer forwarded requeset and response to /Studentservlet.");
		log.trace("Finished addStudent() method.");
	}

}
