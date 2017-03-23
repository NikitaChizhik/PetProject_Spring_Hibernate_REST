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

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentDeleteServlet")
public class StudentDeleteServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(StudentDeleteServlet.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		deleteStudent(request, response);

	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		StudentManager studentManager = new StudentManagerImpl();

		try {
			studentManager.delete(Integer.parseInt(request.getParameter("studentId")));

		} catch (NumberFormatException | DomainException e) {
			log.error("Cannot delete student.", e);
			throw new ServletException("Cannot delete student.", e);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentServlet");
		dispatcher.forward(request, response);
		
		log.trace("Dispathcer forwarded requeset and response to /Studentservlet.");
	}

}
