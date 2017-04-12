package com.nikitachizhik91.university.web.servlets.timetable.teacher;

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
import com.nikitachizhik91.university.domain.TeacherManager;
import com.nikitachizhik91.university.domain.impl.TeacherManagerImpl;
import com.nikitachizhik91.university.model.Teacher;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/teacherTimetableForMonth")
public class TeacherForMonthServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(TeacherForMonthServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.trace("Get request to find all teachers");

		TeacherManager teacherManager = new TeacherManagerImpl();
		List<Teacher> teachers = null;

		try {
			
			teachers = teacherManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all teachers.", e);
			throw new WebException("Cannot find all teachers.", e);
		}

		request.setAttribute("teachers", teachers);
		request.getRequestDispatcher("/findTeacherTimetableForMonth.jsp").forward(request, response);

		log.trace("Found all teachers");
	}
}
