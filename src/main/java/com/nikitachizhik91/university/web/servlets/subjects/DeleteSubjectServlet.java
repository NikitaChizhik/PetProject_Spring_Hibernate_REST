package com.nikitachizhik91.university.web.servlets.subjects;

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

//@WebServlet("/subjectDelete")
public class DeleteSubjectServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteSubjectServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String subjectId = request.getParameter("subjectId");

		log.trace("Post request to delete subject with id=" + subjectId);

		SubjectManager subjectManager = new SubjectManagerImpl();

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

		log.trace("Deleted subject with id=" + subjectId);
	}
}
