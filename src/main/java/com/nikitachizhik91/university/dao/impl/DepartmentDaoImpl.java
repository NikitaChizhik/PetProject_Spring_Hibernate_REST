package com.nikitachizhik91.university.dao.impl;

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
public class DepartmentDaoImpl implements DepartmentDao {

	private final static Logger log = LogManager.getLogger(DepartmentDaoImpl.class.getName());

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

	// SQL- "insert into departments_teachers (department_id,teacher_id) values
	// (?,?)"
	public void addTeacher(int departmentId, int teacherId) throws DaoException {
		log.trace("Started addStudent() method.");

		// "INSERT INTO Employee(firstName, lastName, salary)" + "SELECT
		// firstName, lastName, salary FROM old_employee";

		try (Session session = sessionFactory.openSession()) {
			session.createQuery("insert into groups_students (group_id,student_id) values (groupId,studentId)")
					.setParameter("groupId", teacherId).setParameter("studentId", departmentId).executeUpdate();
		}

		log.info("Added Student with id=" + departmentId + " to the group with id=" + teacherId);
		log.trace("Finished addStudent() method.");
	}

	// SQL- "insert into departments_subjects (department_id,subject_id) values
	// (?,?)"
	public void addSubject(int departmentId, int subjectId) throws DaoException {
		log.trace("Started addStudent() method.");

		// "INSERT INTO Employee(firstName, lastName, salary)" + "SELECT
		// firstName, lastName, salary FROM old_employee";

		try (Session session = sessionFactory.openSession()) {
			session.createQuery("insert into groups_students (group_id,student_id) values (groupId,studentId)")
					.setParameter("groupId", subjectId).setParameter("studentId", departmentId).executeUpdate();
		}

		log.info("Added Student with id=" + departmentId + " to the group with id=" + subjectId);
		log.trace("Finished addStudent() method.");
	}

	// SQL-"delete from departments_teachers where teacher_id=?"
	public void deleteTeacherFromDepartment(int teacherId) throws DaoException {
		log.trace("Started deleteStudentFromGroup() method.");

		try (Session session = sessionFactory.openSession()) {
			session.createQuery(":studentId").setParameter("studentId", teacherId).executeUpdate();
		}

		log.info("Deleted Student with id=" + teacherId);
		log.trace("Finished deleteStudentFromGroup() method.");

	}

	// SQL-"delete from departments_subjects where subject_id=?"
	public void deleteSubjectFromDepartment(int subjectId) throws DaoException {
		log.trace("Started deleteStudentFromGroup() method.");

		try (Session session = sessionFactory.openSession()) {
			session.createQuery(":studentId").setParameter("studentId", subjectId).executeUpdate();
		}

		log.info("Deleted Student with id=" + subjectId);
		log.trace("Finished deleteStudentFromGroup() method.");

	}

	// SQL-"SELECT id FROM departments d WHERE NOT EXISTS(SELECT NULL FROM
	// faculties_departments fd WHERE fd.department_id = d.id)"
	public List<Department> findDepartmentsWithoutFaculty() throws DaoException {
		log.trace("Started findGroupsWithoutFaculty() method.");

		List<Department> groups;

		try (Session session = sessionFactory.openSession()) {
			groups = (List<Department>) session.createQuery("from Department").list();
		}

		log.info("Found all groups which are without faculty.");
		log.trace("Finished findGroupsWithoutFaculty() method.");

		return groups;
	}

}
