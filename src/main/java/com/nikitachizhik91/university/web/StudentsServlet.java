package com.nikitachizhik91.university.web;

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
import com.nikitachizhik91.university.domain.impl.StudentManagerImpl;
import com.nikitachizhik91.university.model.Student;


@WebServlet("/students")
public class StudentsServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(StudentsServlet.class.getName());
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started findAll() method.");

		List<Student> students = null;
		StudentManager studentManager = new StudentManagerImpl();

		try {
			students = studentManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all students.", e);
			throw new WebException("Cannot find all students.", e);
		}

		request.setAttribute("students", students);
		request.getRequestDispatcher("/students.jsp").forward(request, response);

		log.trace("Finished findAll() method.");
		log.info("Found all students.");

	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started addStudent() method.");

		String name = request.getParameter("name");

		Student student = new Student();
		student.setName(name);

		StudentManager studentManager = new StudentManagerImpl();

		try {
			studentManager.create(student);

		} catch (DomainException e) {

			log.error("Cannot add Student student.", e);
			throw new WebException("Cannot add Student student.", e);
		}

		response.sendRedirect("students");

		log.trace("Finished addStudent() method.");
		log.info("Added student.");

	}

}
