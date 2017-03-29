package com.nikitachizhik91.university.web.servlets.teachers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.SubjectManager;
import com.nikitachizhik91.university.domain.impl.SubjectManagerImpl;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/subjectDelete")
public class DeleteTeacherServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteTeacherServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started delete() method.");

		SubjectManager subjectManager = new SubjectManagerImpl();
		String subjectId = request.getParameter("subjectId");

		try {
			subjectManager.delete(Integer.parseInt(subjectId));

		} catch (NumberFormatException e) {
			log.error("The id=" + subjectId + " is wrong.", e);
			throw new WebException("The id=" + subjectId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete room with id=" + subjectId, e);
			throw new WebException("Cannot delete room with id=" + subjectId, e);
		}

		response.sendRedirect("subjects");

		log.trace("Finished delete() method.");
	}

}
