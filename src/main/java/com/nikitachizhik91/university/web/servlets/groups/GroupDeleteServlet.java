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

@WebServlet("/groupDelete")
public class GroupDeleteServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(GroupDeleteServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String groupId = request.getParameter("groupId");

		log.trace("Post request to delete group with id=" + groupId);

		GroupManager groupManager = new GroupManagerImpl();

		try {
			groupManager.delete(Integer.parseInt(groupId));

		} catch (NumberFormatException e) {
			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete group with id=" + groupId, e);
			throw new WebException("Cannot delete group with id=" + groupId, e);
		}

		response.sendRedirect("groups");

		log.trace("Deleted group with id=" + groupId);
	}
}
