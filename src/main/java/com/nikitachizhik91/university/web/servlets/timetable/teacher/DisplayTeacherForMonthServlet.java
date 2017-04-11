package com.nikitachizhik91.university.web.servlets.timetable.teacher;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.LessonManager;
import com.nikitachizhik91.university.domain.TeacherManager;
import com.nikitachizhik91.university.domain.impl.LessonManagerImpl;
import com.nikitachizhik91.university.domain.impl.TeacherManagerImpl;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Teacher;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/displayTeacherTimetableForMonth")
public class DisplayTeacherForMonthServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DisplayTeacherForMonthServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started displayTeacherTimetableForMonth servlet.");

		String teacherId = request.getParameter("teacherId");
		String dateString = request.getParameter("date");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			log.error("Date=" + date + " is wrong.", e);
			throw new WebException("Date=" + date + " is wrong.", e);
		}

		LessonManager lessonManager = new LessonManagerImpl();
		TeacherManager teacherManager = new TeacherManagerImpl();

		List<Lesson> lessons = null;
		List<Teacher> teachers = null;
		Teacher teacher = null;

		try {

			teacher = teacherManager.findById(Integer.parseInt(teacherId));

			lessons = lessonManager.getTeacherTimetableForMonth(Integer.parseInt(teacherId), date);

			teachers = teacherManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot getTeacherTimetableForMonth() for teacher with id=:" + teacherId + " and Date :" + date,
					e);
			throw new WebException(
					"Cannot getTeacherTimetableForMonth() for teacher with id=:" + teacherId + " and Date :" + date, e);
		} catch (NumberFormatException e) {

			log.error("The teacher id=" + teacherId + " is wrong.", e);
			throw new WebException("The teacher id=" + teacherId + " is wrong.", e);
		}

		request.setAttribute("teachers", teachers);
		request.setAttribute("lessons", lessons);
		request.setAttribute("teacher", teacher);
		request.getRequestDispatcher("/findTeacherTimetableForMonth.jsp").forward(request, response);

		log.trace("Finished displayTeacherTimetableForMonth servlet.");
	}
}
