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

@WebServlet("/faculty/addGroup")
public class AddGroupServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(AddGroupServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String groupId = request.getParameter("groupId");
		String facultyId = request.getParameter("facultyId");

		log.trace("Post request to add group with id=" + groupId + " to faculty with id=" + facultyId);

		FacultyManager facultyManager = new FacultyManagerImpl();

		try {
			facultyManager.addGroup(Integer.parseInt(facultyId), Integer.parseInt(groupId));

		} catch (DomainException e) {
			log.error("Cannot add group with id=" + groupId + " to faculty with id=" + facultyId, e);
			throw new WebException("Cannot add group with id=" + groupId + " to faculty with id=" + facultyId, e);

		} catch (NumberFormatException e) {
			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);
		}
		response.sendRedirect("/university/faculty?facultyId=" + facultyId);

		log.trace("Added group with id=" + groupId + " to faculty with id=" + facultyId);
	}
}
