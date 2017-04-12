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

@WebServlet("/department/delete")
public class DeleteDepartmentServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteDepartmentServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String departmentId = request.getParameter("departmentId");

		log.trace("Post request to delete department with id=" + departmentId);

		DepartmentManager departmentManager = new DepartmentManagerImpl();

		try {
			departmentManager.delete(Integer.parseInt(departmentId));

		} catch (NumberFormatException e) {
			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete department with id=" + departmentId, e);
			throw new WebException("Cannot delete department with id=" + departmentId, e);
		}

		response.sendRedirect("/university/departments");

		log.trace("Deleted department with id=" + departmentId);
	}

}
