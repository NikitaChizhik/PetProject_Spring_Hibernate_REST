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

@WebServlet("/studentDelete")
public class DeleteStudentServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteStudentServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started delete() method.");

		StudentManager studentManager = new StudentManagerImpl();

		try {
			studentManager.delete(Integer.parseInt(request.getParameter("studentId")));

		} catch (NumberFormatException e) {
			log.error("The id is wrong.", e);
			throw new WebException("The id is wrong.", e);

		} catch (DomainException e) {
			log.error("The id is wrong.", e);
			throw new WebException("The id is wrong.", e);
		}

		response.sendRedirect("students");

		log.trace("Finished delete() method.");
		log.info("Deleted the student.");
	}

}
