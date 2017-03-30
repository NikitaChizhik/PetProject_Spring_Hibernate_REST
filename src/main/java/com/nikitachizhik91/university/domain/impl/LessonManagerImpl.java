package com.nikitachizhik91.university.domain.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.impl.LessonDaoImpl;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.LessonManager;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.model.Teacher;

public class LessonManagerImpl implements LessonManager {

	private final static Logger log = LogManager.getLogger(LessonManagerImpl.class.getName());
	private LessonDaoImpl lessonDao;

	public LessonManagerImpl() {
		lessonDao = new LessonDaoImpl();
	}

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
	public List<Lesson> getTeacherTimetableForDay(Teacher teacher, Date date) throws DaoException {
		return null;
	}

	@Override
	public List<Lesson> getTeacherTimetableForMonth(Teacher teacher, Date date) throws DaoException {
		return null;
	}

	@Override
	public List<Lesson> getStudentTimetableForDay(Student student, Date date) throws DaoException {
		return null;
	}

	@Override
	public List<Lesson> getStudentTimetableFoMonth(Student student, Date date) throws DaoException {
		return null;
	}
}
