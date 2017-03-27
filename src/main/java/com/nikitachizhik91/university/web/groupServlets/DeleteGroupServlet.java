package com.nikitachizhik91.university.web.groupServlets;

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
public class DeleteGroupServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteGroupServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started delete() method.");

		GroupManager groupManager = new GroupManagerImpl();

		try {
			groupManager.delete(Integer.parseInt(request.getParameter("groupId")));

		} catch (NumberFormatException e) {
			log.error("The id=" + request.getParameter("groupId") + " is wrong.", e);
			throw new WebException("The id=" + request.getParameter("groupId") + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete group with id=" + request.getParameter("groupId"), e);
			throw new WebException("Cannot delete group with id=" + request.getParameter("groupId"), e);
		}

		response.sendRedirect("groups");

		log.trace("Finished delete() method.");

	}

}
