package com.nikitachizhik91.university.web.servlets.lessons;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.LessonManager;
import com.nikitachizhik91.university.domain.impl.LessonManagerImpl;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/lessonDelete")
public class DeleteLessonServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteLessonServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started delete() method.");

		LessonManager lessonManager = new LessonManagerImpl();
		String lessonId = request.getParameter("lessonId");

		try {
			lessonManager.delete(Integer.parseInt(lessonId));

		} catch (NumberFormatException e) {
			log.error("The id=" + lessonId + " is wrong.", e);
			throw new WebException("The id=" + lessonId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete lesson with id=" + lessonId, e);
			throw new WebException("Cannot delete lesson with id=" + lessonId, e);
		}

		response.sendRedirect("lessons");

		log.trace("Finished delete() method.");
	}

}
