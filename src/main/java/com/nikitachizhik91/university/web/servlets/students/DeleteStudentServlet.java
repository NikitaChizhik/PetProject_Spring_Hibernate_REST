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
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/studentDelete")
public class DeleteStudentServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteStudentServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started delete() method.");

		StudentManager studentManager = new StudentManagerImpl();
		String studentId = request.getParameter("studentId");

		try {
			studentManager.delete(Integer.parseInt(studentId));

		} catch (NumberFormatException e) {
			log.error("The id=" + studentId + " is wrong.", e);
			throw new WebException("The id=" + studentId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete student with id=" + studentId, e);
			throw new WebException("Cannot delete student with id=" + studentId, e);
		}

		response.sendRedirect("students");

		log.trace("Finished delete() method.");
	}

}
