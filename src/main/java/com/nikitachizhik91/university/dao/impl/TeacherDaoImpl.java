package com.nikitachizhik91.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.TeacherDao;
import com.nikitachizhik91.university.model.Teacher;

@Repository
public class TeacherDaoImpl implements TeacherDao {

	private final static Logger log = LogManager.getLogger(TeacherDaoImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	public Teacher create(Teacher teacher) throws DaoException {

		log.trace("Started create() method.");

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Integer id = (Integer) session.save(teacher);
			session.getTransaction().commit();
			teacher.setId(id);
		}

		log.info("Created a Teacher :" + teacher);
		log.trace("Finished create() method.");

		return teacher;
	}

	public Teacher findById(int id) throws DaoException {
		log.trace("Started findById() method.");

		Teacher teacher = null;

		try (Session session = sessionFactory.openSession()) {
			teacher = session.get(Teacher.class, id);
		}

		log.info("Found the Teacher :" + teacher);
		log.trace("Finished findById() method.");

		return teacher;
	}

	@SuppressWarnings("unchecked")
	public List<Teacher> findAll() throws DaoException {
		log.trace("Started findAll() method.");

		List<Teacher> teachers = null;
		try (Session session = sessionFactory.openSession()) {
			teachers = (List<Teacher>) session.createQuery("from Teacher").list();
		}

		log.info("Found all Teachers :");
		log.trace("Finished findAll() method.");

		return teachers;
	}

	public Teacher update(Teacher teacher) throws DaoException {
		log.trace("Started update() method.");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.update(teacher);
			session.getTransaction().commit();
		}

		log.info("Updated Teacher :" + teacher);
		log.trace("Finished update() method.");

		return teacher;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.delete(session.get(Teacher.class, id));
			session.getTransaction().commit();
		}

		log.info("Deleted Teacher with id=" + id);
		log.trace("Finished delete() method.");
	}

	@SuppressWarnings("unchecked")
	public List<Teacher> findTeachersWithoutDepartment() throws DaoException {
		log.trace("Started findTeachersWithoutDepartment() method.");
		List<Teacher> teachers = new ArrayList<>();
		List<Integer> ids;
		try (Session session = sessionFactory.openSession()) {
			ids = (List<Integer>) session
					.createSQLQuery(
							"SELECT id FROM teachers t WHERE NOT EXISTS(SELECT NULL FROM departments_teachers dt WHERE dt.teacher_id = t.id)")
					.list();
		}
		for (Integer id : ids) {
			Teacher teacher = findById(id);
			teachers.add(teacher);
		}
		log.info("Found all Teachers which are without department.");
		log.trace("Finished findTeachersWithoutDepartment() method.");
		return teachers;
	}
}
