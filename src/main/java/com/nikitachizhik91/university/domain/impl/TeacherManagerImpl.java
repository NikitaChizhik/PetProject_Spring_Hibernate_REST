package com.nikitachizhik91.university.domain.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.impl.TeacherDaoImpl;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.TeacherManager;
import com.nikitachizhik91.university.model.Teacher;

public class TeacherManagerImpl implements TeacherManager {

	private final static Logger log = LogManager.getLogger(TeacherManagerImpl.class.getName());
	private TeacherDaoImpl teacherDao;

	public TeacherManagerImpl() {
		teacherDao = new TeacherDaoImpl();
	}

	@Override
	public Teacher create(Teacher teacher) throws DomainException {
		log.trace("Started create() method.");

		Teacher teacherTemp = null;
		try {
			log.trace("Creating teacher.");

			teacherTemp = teacherDao.create(teacher);

		} catch (DaoException e) {
			log.error("Cannot create teacher=" + teacher, e);
			throw new DomainException("Cannot create teacher=" + teacher, e);
		}
		log.trace("Finished create() method.");

		return teacherTemp;
	}

	@Override
	public Teacher findById(int id) throws DomainException {
		log.trace("Started findById() method.");

		Teacher teacher = null;
		try {
			log.trace("Finding teacher by id.");

			teacher = teacherDao.findById(id);

		} catch (DaoException e) {
			log.error("Cannot find teacher by id=" + id, e);
			throw new DomainException("Cannot find teacher by id=" + id, e);
		}
		log.trace("Finished findById() method.");

		return teacher;
	}

	@Override
	public List<Teacher> findAll() throws DomainException {
		log.trace("Started findAll() method.");

		List<Teacher> teachers = null;
		try {
			log.trace("Finding all teachers");

			teachers = teacherDao.findAll();

		} catch (DaoException e) {
			log.error("Cannot find all teachers.", e);
			throw new DomainException("Cannot find all teachers.", e);
		}
		log.trace("Finished findAll() method.");

		return teachers;
	}

	@Override
	public Teacher update(Teacher teacher) throws DomainException {
		log.trace("Started update() method.");

		Teacher teacherTemp = null;
		try {
			log.trace("Updating teacher.");

			teacherTemp = teacherDao.update(teacher);

		} catch (DaoException e) {
			log.error("Cannot update teacher=" + teacher, e);
			throw new DomainException("Cannot update teacher=" + teacher, e);
		}
		log.trace("Finished update() method.");

		return teacherTemp;
	}

	@Override
	public void delete(int id) throws DomainException {
		log.trace("Started delete() method.");

		try {
			log.trace("Deleting teacher by id.");

			teacherDao.delete(id);

		} catch (DaoException e) {
			log.error("Cannot delete teacher by id=" + id, e);
			throw new DomainException("Cannot delete teacher by id=" + id, e);
		}
		log.trace("Finished delete() method.");
	}

	@Override
	public List<Teacher> findTeachersWithoutDepartment() throws DomainException {
		log.trace("Started findTeachersWithoutDepartment() method.");

		List<Teacher> teachers = null;
		try {
			log.trace("Finding all teachers who are without department.");

			teachers = teacherDao.findTeachersWithoutDepartment();

		} catch (DaoException e) {
			log.error("Cannot find all teachers who are without department.", e);
			throw new DomainException("Cannot find all teachers who are without department.", e);
		}
		log.trace("Finished findTeachersWithoutDepartment() method.");

		return teachers;
	}
}
