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
		log.trace("Started findById() method.");

		String departmentId = request.getParameter("departmentId");
		Department department = null;
		DepartmentManager departmentManager = new DepartmentManagerImpl();

		List<Subject> subjectsOfDepartment = null;
		SubjectManager subjectManager = new SubjectManagerImpl();
		List<Subject> subjectsWithoutDepartment = null;

		List<Teacher> teachersOfDepartment = null;
		TeacherManager teacherManagerImpl = new TeacherManagerImpl();
		List<Teacher> teachers = null;

		try {
			department = departmentManager.findById(Integer.parseInt(departmentId));

			subjectsOfDepartment = departmentManager.findSubjectsByDepartmentId(Integer.parseInt(departmentId));

			subjectsWithoutDepartment = subjectManager.findSubjectsWithoutDepartment();

			teachersOfDepartment = departmentManager.findTeachersByDepartmentId(Integer.parseInt(departmentId));

			teachers = teacherManagerImpl.findAll();

		} catch (NumberFormatException e) {
			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot find department by id=" + departmentId, e);
			throw new WebException("Cannot find department by id=" + departmentId, e);
		}

		request.setAttribute("department", department);
		request.setAttribute("subjectsOfDepartment", subjectsOfDepartment);
		request.setAttribute("subjectsWithoutDepartment", subjectsWithoutDepartment);
		request.setAttribute("teachersOfDepartment", teachersOfDepartment);
		request.setAttribute("teachers", teachers);

		request.getRequestDispatcher("/department.jsp").forward(request, response);

		log.trace("Finished findById() method.");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started update() method.");

		String name = request.getParameter("name");
		Department department = null;
		DepartmentManager departmentManager = new DepartmentManagerImpl();
		String departmentId = request.getParameter("departmentId");

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

		log.trace("Finished update() method.");
	}

}
