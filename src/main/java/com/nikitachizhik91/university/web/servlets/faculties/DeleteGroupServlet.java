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

@WebServlet("/faculty/deleteGroup")
public class DeleteGroupServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteGroupServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String groupId = request.getParameter("groupId");
		String facultyId = request.getParameter("facultyId");

		log.trace("Post request to delete group with id=" + groupId + " from faculty with id=" + facultyId);

		FacultyManager facultyManager = new FacultyManagerImpl();

		try {
			facultyManager.deleteGroupFromFaculty(Integer.parseInt(groupId));

		} catch (NumberFormatException e) {
			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete group with id=" + groupId + " from faculty with id=" + facultyId, e);
			throw new WebException("Cannot delete group with id=" + groupId + " from faculty with id=" + facultyId, e);
		}

		response.sendRedirect("/university/faculty?facultyId=" + facultyId);

		log.trace("Deleted group with id=" + groupId + " from faculty with id=" + facultyId);
	}
}
