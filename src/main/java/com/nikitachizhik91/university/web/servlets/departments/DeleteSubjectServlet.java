package com.nikitachizhik91.university.web.servlets.departments;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.domain.DepartmentManager;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.impl.DepartmentManagerImpl;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/department/deleteSubject")
public class DeleteSubjectServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteSubjectServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String subjectId = request.getParameter("subjectId");
		String departmentId = request.getParameter("departmentId");

		log.trace("Post request to delete subject with id=" + subjectId + " from department with id=" + departmentId);

		DepartmentManager departmentManager = new DepartmentManagerImpl();

		try {
			departmentManager.deleteSubjectFromDepartment(Integer.parseInt(subjectId));

		} catch (NumberFormatException e) {
			log.error("The id=" + subjectId + " is wrong.", e);
			throw new WebException("The id=" + subjectId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete subject with id=" + subjectId + " from group with id=" + departmentId, e);
			throw new WebException("Cannot delete subject with id=" + subjectId + " from group with id=" + departmentId,
					e);
		}

		response.sendRedirect("/university/department?departmentId=" + departmentId);

		log.trace("Deleted subject with id=" + subjectId + " from department with id=" + departmentId);
	}
}
