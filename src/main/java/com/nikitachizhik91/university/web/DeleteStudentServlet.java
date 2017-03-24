package com.nikitachizhik91.university.web;

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

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/studentDelete")
public class DeleteStudentServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteStudentServlet.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started delete() method.");

		StudentManager studentManager = new StudentManagerImpl();

		try {
			studentManager.delete(Integer.parseInt(request.getParameter("studentId")));

		} catch (NumberFormatException | DomainException e) {

			log.error("Cannot delete student.", e);
			throw new ServletException("Cannot delete student.", e);
		}

		response.sendRedirect("students");

		log.trace("Finished delete() method.");
		log.info("Deleted the student.");
	}

}
