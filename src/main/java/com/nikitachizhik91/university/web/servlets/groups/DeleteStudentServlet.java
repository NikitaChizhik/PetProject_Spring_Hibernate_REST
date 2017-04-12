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

@WebServlet("/group/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteStudentServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentId = request.getParameter("studentId");

		log.trace("Post request to delete student with id=" + studentId + "from group.");

		GroupManager groupManager = new GroupManagerImpl();

		try {
			groupManager.deleteStudentFromGroup(Integer.parseInt(studentId));

		} catch (NumberFormatException e) {
			log.error("The id=" + studentId + " is wrong.", e);
			throw new WebException("The id=" + studentId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete student with id=" + studentId + " from group with id="
					+ request.getParameter("groupId"), e);
			throw new WebException("Cannot delete student with id=" + studentId + " from group with id="
					+ request.getParameter("groupId"), e);
		}

		response.sendRedirect("/university/group?groupId=" + request.getParameter("groupId"));

		log.trace("Deleted student with id=" + studentId + "from group.");
	}
}
