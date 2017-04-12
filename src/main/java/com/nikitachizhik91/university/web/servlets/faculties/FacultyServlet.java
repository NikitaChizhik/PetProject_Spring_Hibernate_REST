package com.nikitachizhik91.university.web.servlets.faculties;

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
import com.nikitachizhik91.university.domain.FacultyManager;
import com.nikitachizhik91.university.domain.GroupManager;
import com.nikitachizhik91.university.domain.impl.DepartmentManagerImpl;
import com.nikitachizhik91.university.domain.impl.FacultyManagerImpl;
import com.nikitachizhik91.university.domain.impl.GroupManagerImpl;
import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Faculty;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/faculty")
public class FacultyServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(FacultyServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started findById() method.");

		String facultyId = request.getParameter("facultyId");

		log.trace("Get request to find faculty by id=" + facultyId);

		FacultyManager facultyManager = new FacultyManagerImpl();
		Faculty faculty = null;

		List<Department> departmentsWithoutFaculty = null;
		DepartmentManager departmentManager = new DepartmentManagerImpl();
		List<Department> departments = null;

		List<Group> groupsWithoutFaculty = null;
		GroupManager groupManager = new GroupManagerImpl();
		List<Group> groups = null;

		try {
			faculty = facultyManager.findById(Integer.parseInt(facultyId));

			departmentsWithoutFaculty = departmentManager.findDepartmentsWithoutFaculty();

			groupsWithoutFaculty = groupManager.findGroupsWithoutFaculty();

			departments = departmentManager.findAll();

			groups = groupManager.findAll();

		} catch (NumberFormatException e) {
			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot find faculty by id=" + facultyId, e);
			throw new WebException("Cannot find faculty by id=" + facultyId, e);
		}

		request.setAttribute("faculty", faculty);
		request.setAttribute("departments", departments);
		request.setAttribute("departmentsWithoutFaculty", departmentsWithoutFaculty);
		request.setAttribute("groupsWithoutFaculty", groupsWithoutFaculty);
		request.setAttribute("groups", groups);

		request.getRequestDispatcher("/faculty.jsp").forward(request, response);

		log.trace("Found faculty by id=" + facultyId);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String facultyId = request.getParameter("facultyId");
		String name = request.getParameter("name");

		log.trace("Post request to update faculty with id=" + facultyId + " on name=" + name);

		FacultyManager facultyManager = new FacultyManagerImpl();
		Faculty faculty = null;

		try {
			faculty = facultyManager.findById(Integer.parseInt(facultyId));
			faculty.setName(name);

			facultyManager.update(faculty);

		} catch (DomainException e) {
			log.error("Cannot update faculty=" + faculty, e);
			throw new WebException("Cannot update faculty=" + faculty, e);

		} catch (NumberFormatException e) {
			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);
		}

		response.sendRedirect("faculty?facultyId=" + facultyId);

		log.trace("Updated faculty with id=" + facultyId + " on name=" + name);
	}
}
