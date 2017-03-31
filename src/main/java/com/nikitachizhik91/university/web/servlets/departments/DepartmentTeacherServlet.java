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

@WebServlet("/departmentTeacher")
public class DepartmentTeacherServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DepartmentTeacherServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started deleteTeacherFromDepartment() method.");

		DepartmentManager departmentManager = new DepartmentManagerImpl();
		String teacherId = request.getParameter("teacherId");
		String departmentId = request.getParameter("departmentId");

		try {
			departmentManager.deleteTeacherFromDepartment(Integer.parseInt(teacherId));

		} catch (NumberFormatException e) {
			log.error("The id=" + teacherId + " is wrong.", e);
			throw new WebException("The id=" + teacherId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete teacher with id=" + teacherId + " from group with id=" + departmentId, e);
			throw new WebException("Cannot delete teacher with id=" + teacherId + " from group with id=" + departmentId,
					e);
		}

		response.sendRedirect("department?departmentId=" + departmentId);

		log.trace("Finished deleteTeacherFromDepartment() method.");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started addTeacher() method.");

		String teacherId = request.getParameter("teacherId");
		String departmentId = request.getParameter("departmentId");
		DepartmentManager departmentManager = new DepartmentManagerImpl();

		try {
			departmentManager.addTeacher(Integer.parseInt(departmentId), Integer.parseInt(teacherId));

		} catch (DomainException e) {
			log.error("Cannot add teacher with id=" + teacherId + " to department with id=" + departmentId, e);
			throw new WebException("Cannot add teacher with id=" + teacherId + " to department with id=" + departmentId,
					e);

		} catch (NumberFormatException e) {
			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);
		}
		response.sendRedirect("department?departmentId=" + departmentId);

		log.trace("Finished addTeacher() method.");
	}
}
