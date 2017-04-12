package com.nikitachizhik91.university.web.servlets.lessons;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.nikitachizhik91.university.domain.GroupManager;
import com.nikitachizhik91.university.domain.LessonManager;
import com.nikitachizhik91.university.domain.RoomManager;
import com.nikitachizhik91.university.domain.SubjectManager;
import com.nikitachizhik91.university.domain.TeacherManager;
import com.nikitachizhik91.university.domain.impl.GroupManagerImpl;
import com.nikitachizhik91.university.domain.impl.LessonManagerImpl;
import com.nikitachizhik91.university.domain.impl.RoomManagerImpl;
import com.nikitachizhik91.university.domain.impl.SubjectManagerImpl;
import com.nikitachizhik91.university.domain.impl.TeacherManagerImpl;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Room;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/lessons")
public class LessonsServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(LessonsServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.trace("Get request to find all lessons.");

		List<Lesson> lessons = null;
		LessonManager lessonManager = new LessonManagerImpl();

		List<Subject> subjects = new ArrayList<Subject>();
		SubjectManager subjectManager = new SubjectManagerImpl();

		GroupManager groupManager = new GroupManagerImpl();
		List<Group> groups = null;

		List<Teacher> teachers = null;
		TeacherManager teacherManager = new TeacherManagerImpl();

		List<Room> rooms = null;
		RoomManager roomManager = new RoomManagerImpl();

		try {
			lessons = lessonManager.findAll();

			subjects = subjectManager.findAll();

			groups = groupManager.findAll();

			teachers = teacherManager.findAll();

			rooms = roomManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all lessons.", e);
			throw new WebException("Cannot find all lessons.", e);
		}

		int[] numbers = { 1, 2, 3, 4, 5 };

		request.setAttribute("rooms", rooms);
		request.setAttribute("teachers", teachers);
		request.setAttribute("groups", groups);
		request.setAttribute("numbers", numbers);
		request.setAttribute("lessons", lessons);
		request.setAttribute("subjects", subjects);
		request.getRequestDispatcher("/lessons.jsp").forward(request, response);

		log.trace("Found all lessons.");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String number = request.getParameter("number");
		String subjectId = request.getParameter("subjectId");
		String groupId = request.getParameter("groupId");
		String teacherId = request.getParameter("teacherId");
		String roomId = request.getParameter("roomId");
		String dateString = request.getParameter("date");

		log.trace("Post request to create lesson" + "with number=" + number + ",subjectId=" + subjectId + ",groupId="
				+ groupId + ",teacherId=" + teacherId + ",roomId=" + roomId + ",date=" + dateString);

		Lesson lesson = new Lesson();
		lesson.setNumber(Integer.parseInt(number));

		SubjectManager subjectManager = new SubjectManagerImpl();
		GroupManager groupManager = new GroupManagerImpl();
		TeacherManager teacherManager = new TeacherManagerImpl();
		RoomManager roomManager = new RoomManagerImpl();

		try {
			Subject subject = subjectManager.findById(Integer.parseInt(subjectId));
			lesson.setSubject(subject);

			Group group = groupManager.findById(Integer.parseInt(groupId));
			lesson.setGroup(group);

			Teacher teacher = teacherManager.findById(Integer.parseInt(teacherId));
			lesson.setTeacher(teacher);

			Room room = roomManager.findById(Integer.parseInt(roomId));
			lesson.setRoom(room);

		} catch (NumberFormatException e) {
			log.error("The id=" + subjectId + " is wrong.", e);
			throw new WebException("The id=" + subjectId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Can't find subject with id=" + subjectId, e);
			throw new WebException("Can't find subject with id=" + subjectId, e);
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = null;

		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			log.error("Date=" + date + " is wrong.", e);
			throw new WebException("Date=" + date + " is wrong.", e);
		}
		lesson.setDate(date);

		LessonManager lessonManager = new LessonManagerImpl();

		try {
			lessonManager.create(lesson);

		} catch (DomainException e) {

			log.error("Cannot add lesson=" + lesson, e);
			throw new WebException("Cannot add lesson=" + lesson, e);
		}
		response.sendRedirect("lessons");

		log.trace("Created lesson" + "with number=" + number + ",subjectId=" + subjectId + ",groupId=" + groupId
				+ ",teacherId=" + teacherId + ",roomId=" + roomId + ",date=" + dateString);
	}
}
