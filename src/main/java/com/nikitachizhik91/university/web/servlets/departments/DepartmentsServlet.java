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
import com.nikitachizhik91.university.domain.impl.DepartmentManagerImpl;
import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/departments")
public class DepartmentsServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DepartmentsServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started findAll() method.");

		List<Department> departments = null;
		DepartmentManager departmentManager = new DepartmentManagerImpl();

		try {
			departments = departmentManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all departments.", e);
			throw new WebException("Cannot find all departments.", e);
		}

		request.setAttribute("departments", departments);
		request.getRequestDispatcher("/departments.jsp").forward(request, response);

		log.trace("Finished findAll() method.");
		log.info("Found all departments.");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started addDepartment() method.");

		String name = request.getParameter("name");

		Department department = new Department();
		department.setName(name);

		DepartmentManager departmentManager = new DepartmentManagerImpl();

		try {
			departmentManager.create(department);

		} catch (DomainException e) {

			log.error("Cannot add department=" + department, e);
			throw new WebException("Cannot add department=" + department, e);
		}

		response.sendRedirect("departments");

		log.trace("Finished addDepartment() method.");
	}

}
