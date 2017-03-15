package com.nikitachizhik91.university.domain;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.LessonDao;
import com.nikitachizhik91.university.dao.impl.LessonDaoImpl;
import com.nikitachizhik91.university.dao.impl.TimetableDao;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.model.Teacher;

/**
 * @author nikita
 *
 */

public class Timetable {
	private final static Logger log = LogManager.getLogger(Timetable.class.getName());
	private LessonDao lessonDao;
	private TimetableDao timetableDao;

	public Timetable() {
		lessonDao = new LessonDaoImpl();
		timetableDao = new TimetableDao();
	}

	public void addLesson(Lesson lesson) throws DomainException {
		log.trace("Started addLesson().");

		try {
			log.trace("Saving the lesson.");
			lessonDao.create(lesson);
		} catch (DaoException e) {
			log.error("Cannot add lesson." + lesson, e);
			throw new DomainException("Cannot add lesson.", e);
		}

		log.trace("Finished addLesson().");
	}

	public void deleteLesson(Lesson lesson) throws DomainException {
		log.trace("Started deleteLesson().");
		try {
			log.trace("Deleting the lesson.");
			lessonDao.delete(lesson.getId());
		} catch (DaoException e) {
			log.error("Cannot delete lesson." + lesson, e);
			throw new DomainException("Cannot delete lesson.", e);
		}

		log.trace("Finished deleteLesson().");
	}

	public List<Lesson> getTeacherTimetableForDay(Teacher teacher, Date date) throws DomainException {
		log.trace("Started getTeachersTimetableForDay().");
		if (teacher == null || date == null) {
			log.error("Teacher or date == null");
			throw new IllegalArgumentException("Teacher or Date == null");
		}

		List<Lesson> lessons = null;

		try {
			log.trace("Finding required lessons.");
			lessons = timetableDao.getTeacherTimetableForDay(teacher, date);
		} catch (DaoException e) {
			log.error("Cannot getTeacherTimetableForDay() ffor Teacher :" + teacher + " and Date :" + date, e);
			throw new DomainException("Cannot getTeacherTimetableForDay() for Teacher :" + teacher + " and Date :"
					+ date, e);
		}

		log.info("Found " + lessons.size() + " lessons for teacher:" + teacher + " and Date:" + date);
		log.trace("Finished getTeachersTimetableForDay().");
		return lessons;
	}

	public List<Lesson> getTeacherTimetableForMonth(Teacher teacher, Date date) throws DomainException {
		log.trace("Started getTeacherTimetableForMonth().");
		if (teacher == null || date == null) {
			log.error("Teacher or date == null");
			throw new IllegalArgumentException("Teacher or Date == null");
		}

		List<Lesson> lessons = null;

		try {
			log.trace("Finding required lessons.");
			lessons = timetableDao.getTeacherTimetableForMonth(teacher, date);
		} catch (DaoException e) {
			log.error("Cannot getTeacherTimetableForMonth() ffor Teacher :" + teacher + " and Date :" + date, e);
			throw new DomainException("Cannot getTeacherTimetableForMonth() for Teacher :" + teacher + " and Date :"
					+ date, e);
		}

		log.info("Found " + lessons.size() + " lessons for teacher:" + teacher + " and Date:" + date);
		log.trace("Finished getTeacherTimetableForMonth().");

		return lessons;
	}

	public List<Lesson> getStudentTimetableForDay(Student student, Date date) throws DomainException {
		log.trace("Started getStudentTimetableForDay().");
		if (student == null || date == null) {
			log.error("Teacher or date == null");
			throw new IllegalArgumentException("Teacher or Date == null");
		}
		log.trace("Finding all lessons.");

		List<Lesson> lessons = null;

		try {
			log.trace("Finding required lessons.");
			lessons = timetableDao.getStudentTimetableForDay(student, date);
		} catch (DaoException e) {
			log.error("Cannot getStudentTimetableForDay() ffor Teacher :" + student + " and Date :" + date, e);
			throw new DomainException("Cannot getStudentTimetableForDay() for Teacher :" + student + " and Date :"
					+ date, e);
		}

		log.info("Found " + lessons.size() + " lessons for teacher:" + student + " and Date:" + date);
		log.trace("Finished getStudentTimetableForDay().");
		return lessons;
	}

	public List<Lesson> getStudentTimetableFoMonth(Student student, Date date) throws DomainException {
		log.trace("Started getStudentTimetableFoMonth().");
		if (student == null || date == null) {
			log.error("Teacher or date == null");
			throw new IllegalArgumentException("Teacher or Date == null");
		}
		log.trace("Finding all lessons.");

		List<Lesson> lessons = null;

		try {
			log.trace("Finding required lessons.");
			lessons = timetableDao.getStudentTimetableFoMonth(student, date);
		} catch (DaoException e) {
			log.error("Cannot getStudentTimetableFoMonth() ffor Teacher :" + student + " and Date :" + date, e);
			throw new DomainException("Cannot getStudentTimetableFoMonth() for Teacher :" + student + " and Date :"
					+ date, e);
		}

		log.info("Found " + lessons.size() + " lessons for teacher:" + student + " and Date:" + date);
		log.trace("Finished getStudentTimetableFoMonth().");
		return lessons;
	}
}