package com.nikitachizhik91.university.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.dao.impl.LessonDaoImpl;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.model.Teacher;

/**
 * @author nikita
 *
 */
public class Timetable {
	private final static Logger log = LogManager.getLogger(Timetable.class.getName());

	public void addLesson(Lesson lesson) throws DaoException {
		log.trace("Started addLesson().");
		LessonDaoImpl lessonDAOImpl = new LessonDaoImpl();
		log.trace("Creating the lesson in database.");
		lessonDAOImpl.create(lesson);
		log.debug("Created the lesson in database.");
		log.trace("Finished addLesson().");
	}

	public void deleteLesson(Lesson lesson) {
		LessonDaoImpl lessonDAOImpl = new LessonDaoImpl();
		lessonDAOImpl.delete(lesson.getId());
	}

	public List<Lesson> getTeachersTimetableForDay(Teacher teacher, Date date) {
		log.trace("Started getTeachersTimetableForDay().");
		if (teacher == null || date == null) {
			log.error("Teacher or date == null");
			throw new IllegalArgumentException("Teacher or Date == null");
		}
		log.trace("Finding all lessons.");
		LessonDaoImpl lessonDAOImpl = new LessonDaoImpl();
		List<Lesson> lessons = null;
		try {
			lessons = lessonDAOImpl.findAll();
		} catch (DaoException e) {
			log.error("Cannot find all lessons.");
		}
		log.debug("Found all existing lessons.");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
		List<Lesson> timetable = new ArrayList<Lesson>();
		log.trace("Finding required lessons.");
		for (Lesson lesson : lessons) {
			log.trace("Checking whether the lesson :" + lesson + "is matching.");
			if (dateFormat.format(date).equals(dateFormat.format(lesson.getDate()))
					&& lesson.getTeacher().equals(teacher)) {
				log.trace("Lesson" + lesson + " is matching." + "adding lesson :" + lesson);
				log.trace("Adding lesson :" + lesson);
				timetable.add(lesson);
				log.trace("Added lesson :" + lesson);
			}
		}

		log.info("Found required lessons for teacher:" + teacher + " and Date:" + date);
		log.debug("Returns required lessons for teacher:" + teacher + " and Date:" + date);
		log.trace("Lessons are :" + lessons);
		log.trace("Finished getTeachersTimetableForDay().");
		return timetable;
	}

	public List<Lesson> getTeachersTimetableForMonth(Teacher teacher, Date date) {
		if (teacher == null || date == null) {
			throw new IllegalArgumentException();
		}

		LessonDaoImpl lessonDAOImpl = new LessonDaoImpl();
		List<Lesson> lessons = null;
		try {
			lessons = lessonDAOImpl.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("M-yyyy");
		List<Lesson> timetable = new ArrayList<Lesson>();

		for (Lesson lesson : lessons) {

			if (dateFormat.format(date).equals(dateFormat.format(lesson.getDate()))
					&& lesson.getTeacher().equals(teacher)) {
				timetable.add(lesson);
			}
		}
		return timetable;
	}

	public List<Lesson> getStudentsTimetableForDay(Student student, Date date) {
		if (student == null || date == null) {
			throw new IllegalArgumentException();
		}

		LessonDaoImpl lessonDAOImpl = new LessonDaoImpl();
		List<Lesson> lessons = null;
		try {
			lessons = lessonDAOImpl.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
		List<Lesson> timetable = new ArrayList<Lesson>();

		for (Lesson lesson : lessons) {

			if (dateFormat.format(date).equals(dateFormat.format(lesson.getDate()))
					&& lesson.getGroup().getStudents().contains(student)) {

				timetable.add(lesson);

			}
		}
		return timetable;
	}

	public List<Lesson> getStudentsTimetableFoMonth(Student student, Date date) {
		if (student == null || date == null) {
			throw new IllegalArgumentException();
		}

		LessonDaoImpl lessonDAOImpl = new LessonDaoImpl();
		List<Lesson> lessons = null;
		try {
			lessons = lessonDAOImpl.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("M-yyyy");
		List<Lesson> timetable = new ArrayList<Lesson>();

		for (Lesson lesson : lessons) {

			if (dateFormat.format(date).equals(dateFormat.format(lesson.getDate()))
					&& lesson.getGroup().getStudents().contains(student)) {

				timetable.add(lesson);

			}
		}
		return timetable;
	}

}
