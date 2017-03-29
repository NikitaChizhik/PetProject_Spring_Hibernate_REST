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
import com.nikitachizhik91.university.domain.StudentManager;
import com.nikitachizhik91.university.domain.impl.GroupManagerImpl;
import com.nikitachizhik91.university.domain.impl.StudentManagerImpl;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/groupStudentDelete")
public class DeleteStudentFromGroupServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteStudentFromGroupServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started deleteStudentFromGroup() method.");

		GroupManagerImpl groupManager = new GroupManagerImpl();
		// Group group = null;
		// StudentManager studentManager = new StudentManagerImpl();
		// List<Student> students = null;

		try {
			groupManager.deleteStudentFromGroup(Integer.parseInt(request.getParameter("studentId")));

			// group =
			// groupManager.findById(Integer.parseInt(request.getParameter("groupId")));

			// students = studentManager.findStudentsWithoutGroup();

		} catch (NumberFormatException e) {
			log.error("The id=" + request.getParameter("studentId") + " is wrong.", e);
			throw new WebException("The id=" + request.getParameter("studentId") + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete student with id=" + request.getParameter("studentId") + " from group with id="
					+ request.getParameter("groupId"), e);
			throw new WebException("Cannot delete student with id=" + request.getParameter("studentId")
					+ " from group with id=" + request.getParameter("groupId"), e);
		}

		// request.setAttribute("group", group);
		// request.setAttribute("students", students);
		// request.getRequestDispatcher("/group.jsp").forward(request,
		// response);

		response.sendRedirect("group?groupId=" + request.getParameter("groupId"));

		log.trace("Finished deleteStudentFromGroup() method.");

	}

}
