package com.nikitachizhik91.university.web.servlets.groups;

import java.io.IOException;
import java.util.List;

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
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/groups")
public class GroupsServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(GroupsServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.trace("Get request to find all groups");

		GroupManager groupManager = new GroupManagerImpl();
		List<Group> groups = null;

		try {
			groups = groupManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all groups.", e);
			throw new WebException("Cannot find all groups .", e);
		}

		request.setAttribute("groups", groups);
		request.getRequestDispatcher("/groups.jsp").forward(request, response);

		log.trace("Finished findAll() method.");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");

		log.trace("Post request to create group with name=" + name);

		Group group = new Group();
		group.setName(name);

		GroupManager groupManager = new GroupManagerImpl();

		try {
			groupManager.create(group);

		} catch (DomainException e) {

			log.error("Cannot add group=" + group, e);
			throw new WebException("Cannot add group=" + group, e);
		}

		response.sendRedirect("groups");

		log.trace("Created group with name=" + name);
	}
}
