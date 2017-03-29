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
import com.nikitachizhik91.university.domain.impl.GroupManagerImpl;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/groupStudent")
public class GroupStudentServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(GroupStudentServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started deleteStudentFromGroup() method.");

		GroupManagerImpl groupManager = new GroupManagerImpl();

		try {
			groupManager.deleteStudentFromGroup(Integer.parseInt(request.getParameter("studentId")));

		} catch (NumberFormatException e) {
			log.error("The id=" + request.getParameter("studentId") + " is wrong.", e);
			throw new WebException("The id=" + request.getParameter("studentId") + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete student with id=" + request.getParameter("studentId") + " from group with id="
					+ request.getParameter("groupId"), e);
			throw new WebException("Cannot delete student with id=" + request.getParameter("studentId")
					+ " from group with id=" + request.getParameter("groupId"), e);
		}

		response.sendRedirect("group?groupId=" + request.getParameter("groupId"));

		log.trace("Finished deleteStudentFromGroup() method.");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started addStudent() method.");

		String studentId = request.getParameter("studentId");
		String groupId = request.getParameter("groupId");
		GroupManagerImpl groupManager = new GroupManagerImpl();
		Group group = null;

		try {
			groupManager.addStudent(Integer.parseInt(studentId), Integer.parseInt(groupId));

			group = groupManager.findById(Integer.parseInt(groupId));

		} catch (DomainException e) {
			log.error("Cannot add student with id=" + studentId + " to group with id=" + groupId, e);
			throw new WebException("Cannot update group=" + group, e);

		} catch (NumberFormatException e) {
			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);
		}
		response.sendRedirect("group?groupId=" + groupId);

		log.trace("Finished addStudent() method.");
	}
}
