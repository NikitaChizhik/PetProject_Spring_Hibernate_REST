package com.nikitachizhik91.university.domain.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.impl.StudentDaoImpl;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.StudentManager;
import com.nikitachizhik91.university.model.Student;

public class StudentManagerImpl implements StudentManager {

	private final static Logger log = LogManager.getLogger(StudentManagerImpl.class.getName());
	private StudentDaoImpl studentDao;

	public StudentManagerImpl() {
		studentDao = new StudentDaoImpl();
	}

	@Override
	public Student create(Student student) throws DomainException {
		log.trace("Started create() method.");

		Student studentTemp = null;
		try {
			log.trace("Creating student.");

			studentTemp = studentDao.create(student);

		} catch (DaoException e) {
			log.error("Cannot create student.", e);
			throw new DomainException("Cannot create student.", e);
		}
		log.trace("Finished create() method.");

		return studentTemp;
	}

	@Override
	public Student findById(int id) throws DomainException {
		log.trace("Started findById() method.");

		Student student = null;
		try {
			log.trace("Finding student by id.");

			student = studentDao.findById(id);

		} catch (DaoException e) {
			log.error("Cannot find student by id.", e);
			throw new DomainException("Cannot find student by id.", e);
		}
		log.trace("Finished findById() method.");

		return student;
	}

	@Override
	public List<Student> findAll() throws DomainException {
		log.trace("Started findAll() method.");

		List<Student> students = null;
		try {
			log.trace("Finding all students");

			students = studentDao.findAll();

		} catch (DaoException e) {
			log.error("Cannot find all students.", e);
			throw new DomainException("Cannot find all students.", e);
		}
		log.trace("Finished findAll() method.");

		return students;
	}

	@Override
	public Student update(Student student) throws DomainException {
		log.trace("Started update() method.");

		Student studentTemp = null;
		try {
			log.trace("Updating student.");

			studentTemp = studentDao.update(student);

		} catch (DaoException e) {
			log.error("Cannot update student.", e);
			throw new DomainException("Cannot update student.", e);
		}
		log.trace("Finished update() method.");

		return studentTemp;
	}

	@Override
	public void delete(int id) throws DomainException {
		log.trace("Started delete() method.");

		try {
			log.trace("Deleting student by id.");

			studentDao.delete(id);

		} catch (DaoException e) {
			log.error("Cannot delete student by id.", e);
			throw new DomainException("Cannot delete student by id.", e);
		}
		log.trace("Finished delete() method.");
	}

	@Override
	public List<Student> findStudentsWithoutGroup() throws DomainException {
		log.trace("Started findStudentsWithoutGroup() method.");

		List<Student> students = null;
		try {
			log.trace("Finding all students who are without group.");

			students = studentDao.findStudentsWithoutGroup();

		} catch (DaoException e) {
			log.error("Cannot find all students who are without group.", e);
			throw new DomainException("Cannot find all students who are without group.", e);
		}
		log.trace("Finished findStudentsWithoutGroup() method.");

		return students;
	}

}
