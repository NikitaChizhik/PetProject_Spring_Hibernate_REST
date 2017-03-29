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
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/subject")
public class TeacherServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(TeacherServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started findById() method.");

		String subjectId = request.getParameter("subjectId");
		Subject subject = null;
		SubjectManager subjectManager = new SubjectManagerImpl();

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

		log.trace("Finished findById() method.");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started update() method.");

		String name = request.getParameter("name");
		Subject subject = null;
		SubjectManager subjectManager = new SubjectManagerImpl();
		String subjectId = request.getParameter("subjectId");

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

		log.trace("Finished update() method.");
	}

}
