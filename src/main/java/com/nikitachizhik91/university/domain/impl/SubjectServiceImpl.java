package com.nikitachizhik91.university.domain.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.SubjectDao;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.SubjectService;
import com.nikitachizhik91.university.model.Subject;

@Service
public class SubjectServiceImpl implements SubjectService {

	private final static Logger log = LogManager.getLogger(SubjectServiceImpl.class.getName());

	@Autowired
	private SubjectDao subjectDao;

	@Override
	public Subject create(Subject subject) throws DomainException {
		log.trace("Started create() method.");

		Subject subjectTemp = null;
		try {
			log.trace("Creating room.");

			subjectTemp = subjectDao.create(subject);

		} catch (DaoException e) {
			log.error("Cannot create subject=" + subject, e);
			throw new DomainException("Cannot create subject=" + subject, e);
		}
		log.trace("Finished create() method.");

		return subjectTemp;
	}

	@Override
	public Subject findById(int id) throws DomainException {
		log.trace("Started findById() method.");

		Subject subject = null;
		try {
			log.trace("Finding subject by id.");

			subject = subjectDao.findById(id);

		} catch (DaoException e) {
			log.error("Cannot find subject by id=" + id, e);
			throw new DomainException("Cannot find subject by id=" + id, e);
		}
		log.trace("Finished findById() method.");

		return subject;
	}

	@Override
	public List<Subject> findAll() throws DomainException {
		log.trace("Started findAll() method.");

		List<Subject> subjects = null;

		try {
			log.trace("Finding all subjects");

			subjects = subjectDao.findAll();

		} catch (DaoException e) {
			log.error("Cannot find all subjects.", e);
			throw new DomainException("Cannot find all subjects.", e);
		}
		log.trace("Finished findAll() method.");

		return subjects;
	}

	@Override
	public Subject update(Subject subject) throws DomainException {
		log.trace("Started update() method.");

		Subject subjectTemp = null;
		try {
			log.trace("Updating subject.");

			subjectTemp = subjectDao.update(subject);

		} catch (DaoException e) {
			log.error("Cannot update subject=" + subject, e);
			throw new DomainException("Cannot update subject=" + subject, e);
		}
		log.trace("Finished update() method.");

		return subjectTemp;
	}

	@Override
	public void delete(int id) throws DomainException {
		log.trace("Started delete() method.");

		try {
			log.trace("Deleting subject by id.");

			subjectDao.delete(id);

		} catch (DaoException e) {
			log.error("Cannot delete subject by id=" + id, e);
			throw new DomainException("Cannot delete subject by id=" + id, e);
		}
		log.trace("Finished delete() method.");
	}

	@Override
	public List<Subject> findSubjectsWithoutDepartment() throws DomainException {
		log.trace("Started findSubjectsWithoutDepartment() method.");

		List<Subject> subjects = null;
		try {
			log.trace("Finding all subjects which are without department.");

			subjects = subjectDao.findSubjectsWithoutDepartment();

		} catch (DaoException e) {
			log.error("Cannot find all subjects which are without department.", e);
			throw new DomainException("Cannot find all subjects which are without department.", e);
		}
		log.trace("Finished findSubjectsWithoutDepartment() method.");

		return subjects;
	}
}
