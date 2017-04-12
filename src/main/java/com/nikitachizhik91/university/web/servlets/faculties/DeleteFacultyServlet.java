package com.nikitachizhik91.university.web.servlets.faculties;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.FacultyManager;
import com.nikitachizhik91.university.domain.impl.FacultyManagerImpl;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/faculty/delete")
public class DeleteFacultyServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteFacultyServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String facultyId = request.getParameter("facultyId");

		log.trace("Post request to delete faculty with id=" + facultyId);

		FacultyManager facultyManager = new FacultyManagerImpl();

		try {
			facultyManager.delete(Integer.parseInt(facultyId));

		} catch (NumberFormatException e) {

			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot delete faculty with id=" + facultyId, e);
			throw new WebException("Cannot delete faculty with id=" + facultyId, e);
		}

		response.sendRedirect("/university/faculties");

		log.trace("Deleted faculty with id=" + facultyId);
	}

}
