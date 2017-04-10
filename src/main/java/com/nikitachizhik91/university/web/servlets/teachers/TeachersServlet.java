package com.nikitachizhik91.university.web.servlets.teachers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.SubjectManager;
import com.nikitachizhik91.university.domain.TeacherManager;
import com.nikitachizhik91.university.domain.impl.SubjectManagerImpl;
import com.nikitachizhik91.university.domain.impl.TeacherManagerImpl;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/teachers")
public class TeachersServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(TeachersServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started findAll() method.");

		List<Teacher> teachers = null;
		TeacherManager teacherManager = new TeacherManagerImpl();
		List<Subject> subjects = new ArrayList<Subject>();
		SubjectManager subjectManager = new SubjectManagerImpl();

		try {
			teachers = teacherManager.findAll();

			subjects = subjectManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all teachers.", e);
			throw new WebException("Cannot find all teachers.", e);
		}

		request.setAttribute("teachers", teachers);
		request.setAttribute("subjects", subjects);
		request.getRequestDispatcher("/teachers.jsp").forward(request, response);

		log.trace("Finished findAll() method.");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started addSubject() method.");

		String name = request.getParameter("name");
		String subjectId = request.getParameter("subjectId");

		Teacher teacher = new Teacher();
		teacher.setName(name);
		SubjectManager subjectManager = new SubjectManagerImpl();

		try {
			Subject subject = subjectManager.findById(Integer.parseInt(subjectId));
			teacher.setSubject(subject);

		} catch (NumberFormatException e) {
			log.error("The subject id=" + subjectId + " is wrong.", e);
			throw new WebException("The subject id=" + subjectId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Can't find subject with id=" + subjectId, e);
			throw new WebException("Can't find subject with id=" + subjectId, e);
		}

		TeacherManager teacherManager = new TeacherManagerImpl();

		try {
			teacherManager.create(teacher);

		} catch (DomainException e) {

			log.error("Cannot add teacher=" + teacher, e);
			throw new WebException("Cannot add teacher=" + teacher, e);
		}

		response.sendRedirect("teachers");

		log.trace("Finished addTeacher() method.");
	}

}
