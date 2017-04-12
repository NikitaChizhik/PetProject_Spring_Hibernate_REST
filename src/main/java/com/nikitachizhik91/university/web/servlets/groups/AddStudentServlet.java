package com.nikitachizhik91.university.web.servlets.groups;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.GroupManager;
import com.nikitachizhik91.university.domain.impl.GroupManagerImpl;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/group/addStudent")
public class AddStudentServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(AddStudentServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentId = request.getParameter("studentId");
		String groupId = request.getParameter("groupId");

		log.trace("Post request to add student with id=" + studentId + "to group with id=" + groupId);

		GroupManager groupManager = new GroupManagerImpl();

		try {
			groupManager.addStudent(Integer.parseInt(studentId), Integer.parseInt(groupId));

		} catch (NumberFormatException e) {
			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot add student with id=" + studentId + " to group with id=" + groupId, e);
			throw new WebException("Cannot add student with id=" + studentId + " to group with id=" + groupId, e);

		}

		response.sendRedirect("/university/group?groupId=" + groupId);

		log.trace("Added student with id=" + studentId + "to group with id=" + groupId);
	}
}
