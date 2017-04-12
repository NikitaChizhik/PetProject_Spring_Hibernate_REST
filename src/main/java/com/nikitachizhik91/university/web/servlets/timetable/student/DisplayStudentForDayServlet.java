package com.nikitachizhik91.university.web.servlets.timetable.student;

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
import com.nikitachizhik91.university.domain.StudentManager;
import com.nikitachizhik91.university.domain.impl.LessonManagerImpl;
import com.nikitachizhik91.university.domain.impl.StudentManagerImpl;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/displayStudentTimetableForDay")
public class DisplayStudentForDayServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DisplayStudentForDayServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentId = request.getParameter("studentId");
		String dateString = request.getParameter("date");

		log.trace("Get request to find student timetable for day,with student id=" + studentId + " and date="
				+ dateString);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date date = null;

		try {

			date = formatter.parse(dateString);
		} catch (ParseException e) {

			log.error("Date=" + date + " is wrong.", e);
			throw new WebException("Date=" + date + " is wrong.", e);
		}

		LessonManager lessonManager = new LessonManagerImpl();
		StudentManager studentManager = new StudentManagerImpl();

		List<Lesson> lessons = null;
		List<Student> students = null;
		Student student = null;

		try {

			student = studentManager.findById(Integer.parseInt(studentId));

			lessons = lessonManager.getStudentTimetableForDay(Integer.parseInt(studentId), date);

			students = studentManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot getStudentTimetableForDay() for student with id=" + studentId + " and Date :" + date, e);
			throw new WebException(
					"Cannot getStudentTimetableForDay() for student with id=" + studentId + " and Date :" + date, e);
		} catch (NumberFormatException e) {

			log.error("The student id=" + studentId + " is wrong.", e);
			throw new WebException("The student id=" + studentId + " is wrong.", e);
		}

		request.setAttribute("students", students);
		request.setAttribute("lessons", lessons);
		request.setAttribute("student", student);
		request.getRequestDispatcher("/findStudentTimetableForDay.jsp").forward(request, response);

		log.trace("Found " + lessons.size() + " lessons for student with id=" + studentId + " and date=" + dateString);
	}
}
