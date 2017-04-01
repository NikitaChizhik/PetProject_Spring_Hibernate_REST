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

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.FacultyManager;
import com.nikitachizhik91.university.domain.impl.FacultyManagerImpl;
import com.nikitachizhik91.university.model.Faculty;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/faculties")
public class FacultiesServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(FacultiesServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started findAll() method.");

		List<Faculty> faculties = null;
		FacultyManager facultyManager = new FacultyManagerImpl();

		try {
			faculties = facultyManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all faculties.", e);
			throw new WebException("Cannot find all faculties.", e);
		}

		request.setAttribute("faculties", faculties);
		request.getRequestDispatcher("/faculties.jsp").forward(request, response);

		log.trace("Finished findAll() method.");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started addFaculty() method.");

		String name = request.getParameter("name");

		Faculty faculty = new Faculty();
		faculty.setName(name);

		FacultyManager facultyManager = new FacultyManagerImpl();

		try {
			facultyManager.create(faculty);

		} catch (DomainException e) {

			log.error("Cannot add faculty=" + faculty, e);
			throw new WebException("Cannot add faculty=" + faculty, e);
		}

		response.sendRedirect("faculties");

		log.trace("Finished addFaculty() method.");
	}
}
