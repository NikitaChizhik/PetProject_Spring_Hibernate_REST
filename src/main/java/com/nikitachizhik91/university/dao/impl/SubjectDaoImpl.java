package com.nikitachizhik91.university.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.SubjectDao;
import com.nikitachizhik91.university.model.Subject;

@Repository
public class SubjectDaoImpl implements SubjectDao {

	private final static Logger log = LogManager.getLogger(SubjectDaoImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	public Subject create(Subject subject) throws DaoException {
		log.trace("Started create() method.");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Integer id = (Integer) session.save(subject);
			session.getTransaction().commit();
			subject.setId(id);
		}
		log.info("Created a Subject :" + subject);
		log.trace("Finished create() method.");
		return subject;
	}

	public Subject findById(int id) throws DaoException {
		log.trace("Started findById() method.");
		Subject subject = null;
		try (Session session = sessionFactory.openSession()) {
			subject = session.get(Subject.class, id);
		}
		log.info("Found the Subject :" + subject);
		log.trace("Finished findById() method.");
		return subject;
	}

	@SuppressWarnings("unchecked")
	public List<Subject> findAll() throws DaoException {
		log.trace("Started findAll() method.");
		List<Subject> subjects = null;
		try (Session session = sessionFactory.openSession()) {
			subjects = (List<Subject>) session.createQuery("from Subject").list();
		}
		log.info("Found all Subjects :");
		log.trace("Finished findAll() method.");
		return subjects;
	}

	public Subject update(Subject subject) throws DaoException {
		log.trace("Started update() method.");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.update(subject);
			session.getTransaction().commit();
		}
		log.info("Updated Subject :" + subject);
		log.trace("Finished update() method.");
		return subject;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(session.get(Subject.class, id));
			session.getTransaction().commit();
		}
		log.info("Deleted Subject with id=" + id);
		log.trace("Finished delete() method.");
	}

	@SuppressWarnings("unchecked")
	public List<Subject> findSubjectsWithoutDepartment() throws DaoException {
		log.trace("Started findSubjectsWithoutDepartment() method.");
		List<Subject> subjects;
		try (Session session = sessionFactory.openSession()) {
			subjects = (List<Subject>) session
					.createQuery(
							"from Subject s where not exists (from Department d where s.id in elements(d.subjects))")
					.list();
		}
		log.info("Found all Subjects which are without department.");
		log.trace("Finished findSubjectsWithoutDepartment() method.");
		return subjects;
	}
}
