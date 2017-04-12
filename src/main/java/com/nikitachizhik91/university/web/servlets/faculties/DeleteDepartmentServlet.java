package com.nikitachizhik91.university.web.servlets.faculties;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.FacultyManager;
import com.nikitachizhik91.university.domain.impl.FacultyManagerImpl;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/faculty/deleteDepartment")
public class DeleteDepartmentServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteDepartmentServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String departmentId = request.getParameter("departmentId");
		String facultyId = request.getParameter("facultyId");

		log.trace("Post request to delete department with id=" + departmentId + " from faculty with id=" + facultyId);

		FacultyManager facultyManager = new FacultyManagerImpl();

		try {
			facultyManager.deleteDepartmentFromFaculty(Integer.parseInt(departmentId));

		} catch (NumberFormatException e) {
			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete department with id=" + departmentId + " from faculty with id=" + facultyId, e);
			throw new WebException(
					"Cannot delete department with id=" + departmentId + " from faculty with id=" + facultyId, e);
		}

		response.sendRedirect("/university/faculty?facultyId=" + facultyId);

		log.trace("Deleted department with id=" + departmentId + " from faculty with id=" + facultyId);
	}
}
