package com.nikitachizhik91.university.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.DepartmentDao;
import com.nikitachizhik91.university.model.Department;

@Repository
public class DepartmentDaoHibernate implements DepartmentDao {

	private final static Logger log = LogManager.getLogger(DepartmentDaoHibernate.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	public Department create(Department department) throws DaoException {
		log.trace("Started create() method.");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Integer id = (Integer) session.save(department);
			session.getTransaction().commit();
			department.setId(id);
		}
		log.info("Created a Department :" + department);
		log.trace("Finished create() method.");
		return department;
	}

	public Department findById(int id) throws DaoException {
		log.trace("Started findById() method.");
		Department department = null;
		try (Session session = sessionFactory.openSession()) {
			department = session.get(Department.class, id);
		}
		log.info("Found the Department :" + department);
		log.trace("Finished findById() method.");
		return department;
	}

	@SuppressWarnings("unchecked")
	public List<Department> findAll() throws DaoException {
		log.trace("Started findAll() method.");
		List<Department> departments = null;
		try (Session session = sessionFactory.openSession()) {
			departments = (List<Department>) session.createQuery("from Department").list();
		}
		log.info("Found all Departments :");
		log.trace("Finished findAll() method.");
		return departments;
	}

	public Department update(Department department) throws DaoException {
		log.trace("Started update() method.");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.update(department);
			session.getTransaction().commit();
		}
		log.info("Updated Department :" + department);
		log.trace("Finished update() method.");
		return department;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(session.get(Department.class, id));
			session.getTransaction().commit();
		}
		log.info("Deleted Department with id=" + id);
		log.trace("Finished delete() method.");
	}

	@SuppressWarnings("unchecked")
	public List<Department> findDepartmentsWithoutFaculty() throws DaoException {
		log.trace("Started findGroupsWithoutFaculty() method.");
		List<Department> departments;
		try (Session session = sessionFactory.openSession()) {
			departments = (List<Department>) session
					.createQuery(
							"from Department d where not exists (from Faculty f where d in elements(f.departments))")
					.list();
		}
		log.info("Found all groups which are without faculty.");
		log.trace("Finished findGroupsWithoutFaculty() method.");
		return departments;
	}
}
