package com.nikitachizhik91.university.domain.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.LessonDao;
import com.nikitachizhik91.university.dao.StudentDao;
import com.nikitachizhik91.university.dao.TeacherDao;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.LessonService;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.model.Teacher;

@Service
public class LessonServiceImpl implements LessonService {

	private final static Logger log = LogManager.getLogger(LessonServiceImpl.class.getName());

	@Autowired
	private LessonDao lessonDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private TeacherDao teacherDao;

	@Override
	public Lesson create(Lesson lesson) throws DomainException {
		log.trace("Started create() method.");

		Lesson lessonTemp = null;
		try {
			log.trace("Creating lesson.");

			lessonTemp = lessonDao.create(lesson);

		} catch (DaoException e) {
			log.error("Cannot create lesson=" + lesson, e);
			throw new DomainException("Cannot create lesson=" + lesson, e);
		}
		log.trace("Finished create() method.");

		return lessonTemp;
	}

	@Override
	public Lesson findById(int id) throws DomainException {
		log.trace("Started findById() method.");

		Lesson teacher = null;
		try {
			log.trace("Finding lesson by id.");

			teacher = lessonDao.findById(id);

		} catch (DaoException e) {
			log.error("Cannot find lesson by id=" + id, e);
			throw new DomainException("Cannot find lesson by id=" + id, e);
		}
		log.trace("Finished findById() method.");

		return teacher;
	}

	@Override
	public List<Lesson> findAll() throws DomainException {
		log.trace("Started findAll() method.");

		List<Lesson> lessons = null;

		try {
			log.trace("Finding all lessons");

			lessons = lessonDao.findAll();

		} catch (DaoException e) {
			log.error("Cannot find all lessons.", e);
			throw new DomainException("Cannot find all lessons.", e);
		}
		log.trace("Finished findAll() method.");

		return lessons;
	}

	@Override
	public Lesson update(Lesson lesson) throws DomainException {
		log.trace("Started update() method.");

		Lesson lessonTemp = null;
		
		try {
			log.trace("Updating lesson.");

			lessonTemp = lessonDao.update(lesson);

		} catch (DaoException e) {
			log.error("Cannot update lesson=" + lesson, e);
			throw new DomainException("Cannot update lesson=" + lesson, e);
		}
		log.trace("Finished update() method.");

		return lessonTemp;
	}

	@Override
	public void delete(int id) throws DomainException {
		log.trace("Started delete() method.");

		try {
			log.trace("Deleting lesson by id.");

			lessonDao.delete(id);

		} catch (DaoException e) {
			log.error("Cannot delete lesson by id=" + id, e);
			throw new DomainException("Cannot delete lesson by id=" + id, e);
		}
		log.trace("Finished delete() method.");
	}

	@Override
	public List<Lesson> getTeacherTimetableForDay(int teacherId, Date date) throws DomainException {
		log.trace("Started getTeacherTimetableForDay().");
		if (teacherId == 0 || date == null) {
			log.error("Teacher or date == null");
			throw new IllegalArgumentException("Teacher or Date == null");
		}
		log.trace("Finding all lessons.");

		List<Lesson> lessons = null;
		Teacher teacher = new Teacher();

		try {
			log.trace("Finding required lessons.");

			teacher = teacherDao.findById(teacherId);

			lessons = lessonDao.getTeacherTimetableForDay(teacher, date);

		} catch (DaoException e) {
			log.error("Cannot getTeacherTimetableForDay() for teacher :" + teacherId + " and Date :" + date, e);
			throw new DomainException(
					"Cannot getTeacherTimetableForDay() for teacher :" + teacherId + " and Date :" + date, e);
		}

		log.info("Found " + lessons.size() + " lessons for teacher:" + teacherId + " and Date:" + date);
		log.trace("Finished getTeacherTimetableForDay().");

		return lessons;
	}

	@Override
	public List<Lesson> getTeacherTimetableForMonth(int teacherId, Date date) throws DomainException {
		log.trace("Started getTeacherTimetableForMonth().");
		if (teacherId == 0 || date == null) {
			log.error("Teacher or date == null");
			throw new IllegalArgumentException("Teacher or Date == null");
		}

		List<Lesson> lessons = null;
		Teacher teacher = new Teacher();

		try {
			log.trace("Finding required lessons.");

			teacher = teacherDao.findById(teacherId);

			lessons = lessonDao.getTeacherTimetableForMonth(teacher, date);

		} catch (DaoException e) {
			log.error("Cannot getStudentTimetableForMonth() for teacher :" + teacher + " and Date :" + date, e);
			throw new DomainException(
					"Cannot getStudentTimetableForMonth() for teacher :" + teacher + " and Date :" + date, e);
		}

		log.info("Found " + lessons.size() + " lessons for teacher:" + teacher + " and Date:" + date);
		log.trace("Finished getTeacherTimetableForMonth().");

		return lessons;
	}

	@Override
	public List<Lesson> getStudentTimetableForDay(int studentId, Date date) throws DomainException {
		log.trace("Started getStudentTimetableForDay().");
		if (studentId == 0 || date == null) {
			log.error("Student or date == null");
			throw new IllegalArgumentException("Student or Date == null");
		}
		log.trace("Finding all lessons.");

		List<Lesson> lessons = null;
		Student student = new Student();

		try {
			log.trace("Finding required lessons.");

			student = studentDao.findById(studentId);

			lessons = lessonDao.getStudentTimetableForDay(student, date);

		} catch (DaoException e) {
			log.error("Cannot getStudentTimetableForDay() for Student :" + studentId + " and Date :" + date, e);
			throw new DomainException(
					"Cannot getStudentTimetableForDay() for Student :" + studentId + " and Date :" + date, e);
		}

		log.info("Found " + lessons.size() + " lessons for Student:" + studentId + " and Date:" + date);
		log.trace("Finished getStudentTimetableForDay().");
		return lessons;
	}

	@Override
	public List<Lesson> getStudentTimetableForMonth(int studentId, Date date) throws DomainException {
		log.trace("Started getStudentTimetableFoMonth().");
		if (studentId == 0 || date == null) {
			log.error("Student or date == null");
			throw new IllegalArgumentException("Student or Date == null");
		}

		List<Lesson> lessons = null;
		Student student = new Student();

		try {
			log.trace("Finding required lessons.");

			student = studentDao.findById(studentId);

			lessons = lessonDao.getStudentTimetableForMonth(student, date);

		} catch (DaoException e) {
			log.error("Cannot getStudentTimetableForMonth() for Student :" + student + " and Date :" + date, e);
			throw new DomainException(
					"Cannot getStudentTimetableForMonth() for Student :" + student + " and Date :" + date, e);
		}

		log.info("Found " + lessons.size() + " lessons for Student:" + student + " and Date:" + date);
		log.trace("Finished getStudentTimetableFoMonth().");

		return lessons;
	}
}
