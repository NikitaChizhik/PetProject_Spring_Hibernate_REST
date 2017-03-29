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
import com.nikitachizhik91.university.domain.StudentManager;
import com.nikitachizhik91.university.domain.impl.GroupManagerImpl;
import com.nikitachizhik91.university.domain.impl.StudentManagerImpl;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/group")
public class GroupServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(GroupServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started findById() method.");

		String groupId = request.getParameter("groupId");
		Group group = null;
		GroupManager groupManager = new GroupManagerImpl();
		StudentManager studentManager = new StudentManagerImpl();
		List<Student> students = null;

		try {
			group = groupManager.findById(Integer.parseInt(groupId));

			students = studentManager.findStudentsWithoutGroup();

		} catch (NumberFormatException e) {
			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot find group by id=" + groupId, e);
			throw new WebException("Cannot find group by id=" + groupId, e);
		}

		request.setAttribute("group", group);
		request.setAttribute("students", students);
		System.out.println(students.isEmpty());
		request.getRequestDispatcher("/group.jsp").forward(request, response);

		log.trace("Finished findById() method.");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started update() method.");

		String name = request.getParameter("name");
		Group group = null;
		GroupManager groupManager = new GroupManagerImpl();
		String groupId = request.getParameter("groupId");

		try {
			group = groupManager.findById(Integer.parseInt(groupId));
			group.setName(name);

			groupManager.update(group);

		} catch (DomainException e) {
			log.error("Cannot update group=" + group, e);
			throw new WebException("Cannot update group=" + group, e);

		} catch (NumberFormatException e) {
			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);
		}

		response.sendRedirect("group?groupId=" + groupId);

		log.trace("Finished update() method.");
	}

}
