package com.nikitachizhik91.university.web.servlets.departments;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.domain.DepartmentManager;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.SubjectManager;
import com.nikitachizhik91.university.domain.TeacherManager;
import com.nikitachizhik91.university.domain.impl.DepartmentManagerImpl;
import com.nikitachizhik91.university.domain.impl.SubjectManagerImpl;
import com.nikitachizhik91.university.domain.impl.TeacherManagerImpl;
import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DepartmentServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String departmentId = request.getParameter("departmentId");

		log.trace("Get request to find department with id=" + departmentId);

		Department department = null;
		DepartmentManager departmentManager = new DepartmentManagerImpl();

		SubjectManager subjectManager = new SubjectManagerImpl();
		List<Subject> subjectsWithoutDepartment = null;

		TeacherManager teacherManager = new TeacherManagerImpl();
		List<Teacher> teachersWithoutDepartment = null;

		try {
			department = departmentManager.findById(Integer.parseInt(departmentId));

			subjectsWithoutDepartment = subjectManager.findSubjectsWithoutDepartment();

			teachersWithoutDepartment = teacherManager.findTeachersWithoutDepartment();

		} catch (NumberFormatException e) {
			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot find department by id=" + departmentId, e);
			throw new WebException("Cannot find department by id=" + departmentId, e);
		}

		request.setAttribute("department", department);
		request.setAttribute("subjectsWithoutDepartment", subjectsWithoutDepartment);
		request.setAttribute("teachersWithoutDepartment", teachersWithoutDepartment);

		request.getRequestDispatcher("/department.jsp").forward(request, response);

		log.trace("Found department with id=" + departmentId);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String departmentId = request.getParameter("departmentId");
		String name = request.getParameter("name");

		log.trace("Post request to update department with id=" + departmentId + " on name=" + name);

		Department department = null;
		DepartmentManager departmentManager = new DepartmentManagerImpl();

		try {
			department = departmentManager.findById(Integer.parseInt(departmentId));
			department.setName(name);

			departmentManager.update(department);

		} catch (DomainException e) {
			log.error("Cannot update department=" + department, e);
			throw new WebException("Cannot update department=" + department, e);

		} catch (NumberFormatException e) {
			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);
		}

		response.sendRedirect("department?departmentId=" + departmentId);

		log.trace("Updated department with id=" + departmentId + " on name=" + name);
	}
}
