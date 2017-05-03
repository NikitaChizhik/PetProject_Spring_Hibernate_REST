package com.nikitachizhik91.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.DepartmentDao;
import com.nikitachizhik91.university.dao.FacultyDao;
import com.nikitachizhik91.university.dao.GroupDao;
import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Faculty;
import com.nikitachizhik91.university.model.Group;

@Repository
public class FacultyDaoImpl implements FacultyDao {

	private final static Logger log = LogManager.getLogger(FacultyDaoImpl.class.getName());

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

	// SQL- "insert into faculties_departments (faculty_id,department_id) values
	// (?,?)"
	public void addDepartment(int facultyId, int departmentId) throws DaoException {
		log.trace("Started addStudent() method.");

		// "INSERT INTO Employee(firstName, lastName, salary)" + "SELECT
		// firstName, lastName, salary FROM old_employee";

		try (Session session = sessionFactory.openSession()) {
			session.createQuery("insert into groups_students (group_id,student_id) values (groupId,studentId)")
					.setParameter("groupId", departmentId).setParameter("studentId", departmentId).executeUpdate();
		}

		log.info("Added Student with id=" + departmentId + " to the group with id=" + departmentId);
		log.trace("Finished addStudent() method.");
	}

	// SQL- "insert into faculties_groups (faculty_id,group_id) values (?,?)"
	public void addGroup(int facultyId, int groupId) throws DaoException {
		log.trace("Started addStudent() method.");

		// "INSERT INTO Employee(firstName, lastName, salary)" + "SELECT
		// firstName, lastName, salary FROM old_employee";

		try (Session session = sessionFactory.openSession()) {
			session.createQuery("insert into groups_students (group_id,student_id) values (groupId,studentId)")
					.setParameter("groupId", groupId).setParameter("studentId", facultyId).executeUpdate();
		}

		log.info("Added Student with id=" + facultyId + " to the group with id=" + groupId);
		log.trace("Finished addStudent() method.");
	}

	// SQL-"delete from faculties_departments where department_id=?"
	public void deleteDepartmentFromFaculty(int departmentId) throws DaoException {
		log.trace("Started deleteStudentFromGroup() method.");

		try (Session session = sessionFactory.openSession()) {
			session.createQuery(":studentId").setParameter("studentId", departmentId).executeUpdate();
		}

		log.info("Deleted Student with id=" + departmentId);
		log.trace("Finished deleteStudentFromGroup() method.");

	}

	// SQL-"delete from faculties_groups where group_id=?"
	public void deleteGroupFromFaculty(int groupId) throws DaoException {
		log.trace("Started deleteStudentFromGroup() method.");

		try (Session session = sessionFactory.openSession()) {
			session.createQuery(":studentId").setParameter("studentId", groupId).executeUpdate();
		}

		log.info("Deleted Student with id=" + groupId);
		log.trace("Finished deleteStudentFromGroup() method.");

	}
}
