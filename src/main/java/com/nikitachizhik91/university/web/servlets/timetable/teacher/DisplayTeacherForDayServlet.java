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

@WebServlet("/displayTeacherTimetableForDay")
public class DisplayTeacherForDayServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DisplayTeacherForDayServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started displayTeacherTimetableForDay servlet.");

		System.out.println(request.getParameter("teacherIdAndName").split(","));
		System.out.println(request.getParameter("teacherIdAndName"));

		String[] parameters = request.getParameter("teacherIdAndName").split(",");
		String teacherId = parameters[0];
		String dateString = request.getParameter("date");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
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

		try {

			lessons = lessonManager.getTeacherTimetableForDay(Integer.parseInt(teacherId), date);

			teachers = teacherManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot getTeacherTimetableForDay() for teacher with id=" + teacherId + " and Date :" + date, e);
			throw new WebException(
					"Cannot getTeacherTimetableForDay() for teacher with id=" + teacherId + " and Date :" + date, e);
		} catch (NumberFormatException e) {

			log.error("The teacher id=" + teacherId + " is wrong.", e);
			throw new WebException("The teacher id=" + teacherId + " is wrong.", e);
		}

		request.setAttribute("teachers", teachers);
		request.setAttribute("lessons", lessons);
		request.setAttribute("teacherName", parameters[1]);
		request.getRequestDispatcher("/findTeacherTimetableForDay.jsp").forward(request, response);

		log.trace("Finished displayTeacherTimetableForDay servlet.");
	}
}
