package com.nikitachizhik91.university.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.FacultyDao;
import com.nikitachizhik91.university.model.Faculty;

@Repository
public class FacultyDaoHibernate implements FacultyDao {

	private final static Logger log = LogManager.getLogger(FacultyDaoHibernate.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	public Faculty create(Faculty faculty) throws DaoException {
		log.trace("Started create() method.");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Integer id = (Integer) session.save(faculty);
			session.getTransaction().commit();
			faculty.setId(id);
		}
		log.info("Created a Faculty :" + faculty);
		log.trace("Finished create() method.");
		return faculty;
	}

	public Faculty findById(int id) throws DaoException {
		log.trace("Started findById() method.");
		Faculty faculty = null;
		try (Session session = sessionFactory.openSession()) {
			faculty = session.get(Faculty.class, id);
		}
		log.info("Found the Faculty :" + faculty);
		log.trace("Finished findById() method.");
		return faculty;
	}

	@SuppressWarnings("unchecked")
	public List<Faculty> findAll() throws DaoException {
		log.trace("Started findAll() method.");
		List<Faculty> faculties = null;
		try (Session session = sessionFactory.openSession()) {
			faculties = (List<Faculty>) session.createQuery("from Faculty").list();
		}
		log.info("Found all Faculties :");
		log.trace("Finished findAll() method.");
		return faculties;
	}

	public Faculty update(Faculty faculty) throws DaoException {
		log.trace("Started update() method.");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.update(faculty);
			session.getTransaction().commit();
		}
		log.info("Updated Faculty :" + faculty);
		log.trace("Finished update() method.");
		return faculty;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(session.get(Faculty.class, id));
			session.getTransaction().commit();
		}
		log.info("Deleted Faculty with id=" + id);
		log.trace("Finished delete() method.");
	}
}
