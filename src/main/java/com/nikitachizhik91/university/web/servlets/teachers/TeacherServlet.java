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

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(TeacherServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started findById() method.");

		String teacherId = request.getParameter("teacherId");
		Teacher teacher = null;
		TeacherManager teacherManager = new TeacherManagerImpl();
		List<Subject> subjects = new ArrayList<Subject>();
		SubjectManager subjectManager = new SubjectManagerImpl();

		try {
			teacher = teacherManager.findById(Integer.parseInt(teacherId));

			subjects = subjectManager.findAll();

		} catch (NumberFormatException e) {
			log.error("The id=" + teacherId + " is wrong.", e);
			throw new WebException("The id=" + teacherId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot find teacher by id=" + teacherId, e);
			throw new WebException("Cannot find teacher by id=" + teacherId, e);
		}

		request.setAttribute("teacher", teacher);
		request.setAttribute("subjects", subjects);
		request.getRequestDispatcher("/teacher.jsp").forward(request, response);

		log.trace("Finished findById() method.");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started update() method.");

		String name = request.getParameter("name");
		String subjectId = request.getParameter("subjectId");
		String teacherId = request.getParameter("teacherId");
		Teacher teacher = null;
		TeacherManager teacherManager = new TeacherManagerImpl();
		SubjectManager subjectManager = new SubjectManagerImpl();

		try {
			teacher = teacherManager.findById(Integer.parseInt(teacherId));
			teacher.setName(name);
			teacher.setSubject(subjectManager.findById(Integer.parseInt(subjectId)));

			teacherManager.update(teacher);

		} catch (DomainException e) {
			log.error("Cannot update teacher=" + teacher, e);
			throw new WebException("Cannot update teacher=" + teacher, e);

		} catch (NumberFormatException e) {
			log.error("The id=" + teacherId + " is wrong.", e);
			throw new WebException("The id=" + teacherId + " is wrong.", e);
		}

		response.sendRedirect("teacher?teacherId=" + teacherId);

		log.trace("Finished update() method.");
	}

}
