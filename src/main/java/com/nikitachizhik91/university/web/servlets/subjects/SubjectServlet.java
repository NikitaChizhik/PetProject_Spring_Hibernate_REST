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
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.web.WebException;

//@WebServlet("/subject")
public class SubjectServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(SubjectServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String subjectId = request.getParameter("subjectId");

		log.trace("Get request to find subject by id=" + subjectId);

		SubjectManager subjectManager = new SubjectManagerImpl();
		Subject subject = null;

		try {

			subject = subjectManager.findById(Integer.parseInt(subjectId));

		} catch (NumberFormatException e) {
			log.error("The id=" + subjectId + " is wrong.", e);
			throw new WebException("The id=" + subjectId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot find subject by id=" + subjectId, e);
			throw new WebException("Cannot find subject by id=" + subjectId, e);
		}

		request.setAttribute("subject", subject);
		request.getRequestDispatcher("/subject.jsp").forward(request, response);

		log.trace("Found subject by id=" + subjectId);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String subjectId = request.getParameter("subjectId");
		String name = request.getParameter("name");

		log.trace("Post request to update subject with id=" + subjectId + " on name=" + name);

		SubjectManager subjectManager = new SubjectManagerImpl();
		Subject subject = null;

		try {

			subject = subjectManager.findById(Integer.parseInt(subjectId));
			subject.setName(name);

			subjectManager.update(subject);

		} catch (DomainException e) {
			log.error("Cannot update subject=" + subject, e);
			throw new WebException("Cannot update subject=" + subject, e);

		} catch (NumberFormatException e) {
			log.error("The id=" + subjectId + " is wrong.", e);
			throw new WebException("The id=" + subjectId + " is wrong.", e);
		}

		response.sendRedirect("subject?subjectId=" + subjectId);

		log.trace("Updated subject with id=" + subjectId + " on name=" + name);
	}
}
