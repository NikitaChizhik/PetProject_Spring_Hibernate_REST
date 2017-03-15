package com.nikitachizhik91.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.dao.Connector;
import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.DepartmentDao;
import com.nikitachizhik91.university.dao.SubjectDao;
import com.nikitachizhik91.university.dao.TeacherDao;
import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;

public class DepartmentDaoImlp implements DepartmentDao {
	private final static Logger log = LogManager.getLogger(DepartmentDaoImlp.class.getName());
	Connector connector;
	private static final String INSERT_DEPARTMENT = "insert into departments (name) values(?)";
	private static final String FIND_DEPARTMENT_BY_ID = "select * from departments where id=?";
	private static final String FIND_ALL_DEPARTMENTS = "select * from departments";
	private static final String UPDATE_DEPARTMENT = "update departments set name=? where id =?";
	private static final String DELETE_DEPARTMENT = "delete from departments where id =?";

	private static final String INSERT_SUBJECT = "insert into departments_subjects (department_id,subject_id) values (?,?)";
	private static final String INSERT_TEACHER = "insert into departments_teachers (department_id,teacher_id) values (?,?)";

	private static final String FIND_TEACHERS_BY_DEPARTMENT_ID = "select teacher_id from departments_teachers where department_id=?";
	private static final String DELETE_ALL_TEACHERS_FROM_DEPARTMENT = "delete from departments_teachers where department_id=?";
	private static final String DELETE_TEACHER_FROM_DEPARTMENT = "delete from departments_teachers where teacher_id=?";

	private static final String FIND_SUBJECTS_BY_DEPARTMENT_ID = "select subject_id from departments_subjects where department_id=?";
	private static final String DELETE_ALL_SUBJECTS_FROM_DEPARTMENT = "delete from departments_subjects where department_id=?";
	private static final String DELETE_SUBJECT_FROM_DEPARTMENT = "delete from departments_subjects where subject_id=?";

	public DepartmentDaoImlp() {
		connector = new Connector();
	}

	public Department create(Department departmentArg) throws DaoException {
		log.trace("Started create() method.");

		Department department = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_DEPARTMENT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, departmentArg.getName());
			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				log.trace("Got the result set.");

				while (resultSet.next()) {
					department = new Department();
					department.setId(resultSet.getInt("id"));
					department.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			log.error("Cannot create Department :" + departmentArg, e);
			throw new DaoException("Cannot create Department :", e);
		}
		log.info("Created a Department :" + departmentArg);
		log.trace("Finished create() method.");
		return department;
	}

	public Department findById(int id) throws DaoException {
		log.trace("Started findById() method.");

		Department department = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_DEPARTMENT_BY_ID)) {

			statement.setInt(1, id);

			log.trace("Statement :" + statement + " is received.");
			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.executeQuery()) {
				log.debug("Executed query :" + statement);
				log.trace("Got the result set.");

				if (resultSet.next()) {
					department = new Department();
					int departmentId = resultSet.getInt("id");
					department.setId(departmentId);
					department.setName(resultSet.getString("name"));
					department.setTeachers(findTeachersByDepartmentId(departmentId));
					department.setSubjects(findSubjectsByDepartmentId(departmentId));
				}
			}
		} catch (SQLException e) {
			log.error("Cannot find Department with id=" + id, e);
			throw new DaoException("Cannot find Department with id=" + id, e);
		}
		log.info("Found the Department :" + department);
		log.trace("Finished findById() method.");
		return department;
	}

	public List<Department> findAll() throws DaoException {
		log.trace("Started findAll() method.");

		List<Department> departments = new ArrayList<Department>();

		log.trace("Getting Conncetion and creating prepared statement and getting the result set.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_DEPARTMENTS);
				ResultSet resultSet = statement.executeQuery();) {

			log.debug("Executed query :" + statement);
			log.trace("Got the result set.");

			while (resultSet.next()) {
				Department department = new Department();
				int departmentId = resultSet.getInt("id");
				department.setId(departmentId);
				department.setName(resultSet.getString("name"));
				department.setTeachers(findTeachersByDepartmentId(departmentId));
				department.setSubjects(findSubjectsByDepartmentId(departmentId));

				departments.add(department);
			}
		} catch (SQLException e) {
			log.error("Cannot find all departments.", e);
			throw new DaoException("Cannot find all departments.", e);
		}
		log.info("Found all departments :");
		log.trace("Finished findAll() method.");
		return departments;
	}

	public Department update(Department departmentArg) throws DaoException {
		log.trace("Started update() method.");

		Department department = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_DEPARTMENT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, departmentArg.getName());
			statement.setInt(2, departmentArg.getId());

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				log.trace("Got the result set.");

				while (resultSet.next()) {
					department = new Department();
					int departmentId = resultSet.getInt("id");
					department.setId(departmentId);
					department.setName(resultSet.getString("name"));
					department.setTeachers(findTeachersByDepartmentId(departmentId));
					department.setSubjects(findSubjectsByDepartmentId(departmentId));
				}
			}

		} catch (SQLException e) {
			log.error("Cannot update Department :" + departmentArg, e);
			throw new DaoException("Cannot update Department :" + departmentArg, e);
		}
		log.info("Updated Department :" + departmentArg);
		log.trace("Finished update() method.");
		return department;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_DEPARTMENT);) {

			statement.setInt(1, id);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete Department with id=" + id, e);
			throw new DaoException("Cannot delete Department with id=" + id, e);
		}
		log.info("Deleted Department with id=" + id);
		log.trace("Finished delete() method.");
	}

	public void addSubject(int departmentId, int subjectId) throws DaoException {
		log.trace("Started addSubject() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECT);) {

			statement.setInt(1, departmentId);
			statement.setInt(2, subjectId);
			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot add Subject with id=" + subjectId, e);
			throw new DaoException("Cannot add Subject with id=" + subjectId, e);
		}
		log.info("Added Subject with id=" + subjectId + " to the Department with id=" + departmentId);
		log.trace("Finished addSubject() method.");
	}

	public void addTeacher(int departmentId, int teacherId) throws DaoException {
		log.trace("Started addTeacher() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_TEACHER);) {

			statement.setInt(1, departmentId);
			statement.setInt(2, teacherId);
			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot add Teacher with id=" + teacherId, e);
			throw new DaoException("Cannot add Teacher with id=" + teacherId, e);
		}
		log.info("Added Teacher with id=" + teacherId + " to the Department with id=" + departmentId);
		log.trace("Finished addTeacher() method.");
	}

	public List<Teacher> findTeachersByDepartmentId(int departmentId) throws DaoException {
		log.trace("Started findTeachersByDepartmentId() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		List<Teacher> teachers = new ArrayList<Teacher>();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_TEACHERS_BY_DEPARTMENT_ID)) {

			statement.setInt(1, departmentId);
			TeacherDao teacherDao = new TeacherDaoImpl();

			log.trace("Statement :" + statement + " is received.");
			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.executeQuery();) {
				log.debug("Executed query :" + statement);
				log.trace("Got the result set.");

				while (resultSet.next()) {

					teachers.add(teacherDao.findById(resultSet.getInt("teacher_id")));
				}
			}

		} catch (SQLException e) {
			log.error("Cannot find Teachers by Department id=" + departmentId, e);
			throw new DaoException("Cannot find Teachers by Department id=" + departmentId, e);
		}
		log.info("Found " + teachers.size() + " Teachers by Department id=" + departmentId);
		log.trace("Finished findTeachersByDepartmentId() method.");
		return teachers;
	}

	public void deleteAllTeachersFromDepartment(int departmentId) throws DaoException {
		log.trace("Started deleteAllTeachersFromDepartment() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ALL_TEACHERS_FROM_DEPARTMENT);) {

			statement.setInt(1, departmentId);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete all Teachers from Department with id=" + departmentId, e);
			throw new DaoException("Cannot delete all Teachers from Department with id=" + departmentId, e);
		}
		log.info("Deleted all Teachers from Department with id=" + departmentId);
		log.trace("Finished deleteAllTeachersFromDepartment() method.");
	}

	public void deleteTeacherFromDepartment(int teacherId) throws DaoException {
		log.trace("Started deleteTeacherFromDepartment() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_TEACHER_FROM_DEPARTMENT);) {

			statement.setInt(1, teacherId);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete Teacher with id=" + teacherId, e);
			throw new DaoException("Cannot delete Teacher with id=" + teacherId, e);
		}
		log.info("Deleted Teacher with id=" + teacherId);
		log.trace("Finished deleteTeacherFromDepartment() method.");

	}

	public List<Subject> findSubjectsByDepartmentId(int departmentId) throws DaoException {
		log.trace("Started findSubjectsByDepartmentId() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		List<Subject> subjects = new ArrayList<Subject>();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_SUBJECTS_BY_DEPARTMENT_ID)) {

			statement.setInt(1, departmentId);
			SubjectDao subjectDao = new SubjectDaoImpl();

			log.trace("Statement :" + statement + " is received.");
			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.executeQuery();) {
				log.debug("Executed query :" + statement);
				log.trace("Got the result set.");

				while (resultSet.next()) {

					subjects.add(subjectDao.findById(resultSet.getInt("subject_id")));
				}
			}

		} catch (SQLException e) {
			log.error("Cannot find Subjects by Department id=" + departmentId, e);
			throw new DaoException("Cannot find Subjects by Department id=" + departmentId, e);
		}
		log.info("Found " + subjects.size() + " Subjects by Department id=" + departmentId);
		log.trace("Finished findSubjectsByDepartmentId() method.");

		return subjects;
	}

	public void deleteAllSubjectsFromDepartment(int departmentId) throws DaoException {
		log.trace("Started deleteAllSubjectsFromDepartment() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ALL_SUBJECTS_FROM_DEPARTMENT);) {

			statement.setInt(1, departmentId);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete all Subjects from Department with id=" + departmentId, e);
			throw new DaoException("Cannot delete all Subjects from Department with id=" + departmentId, e);
		}
		log.info("Deleted all Subjects from Department with id=" + departmentId);
		log.trace("Finished deleteAllSubjectsFromDepartment() method.");
	}

	public void deleteSubjectFromDepartment(int subjectId) throws DaoException {
		log.trace("Started deleteSubjectFromDepartment() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT_FROM_DEPARTMENT);) {

			statement.setInt(1, subjectId);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete Subejct with id=" + subjectId, e);
			throw new DaoException("Cannot delete Subejct with id=" + subjectId, e);
		}
		log.info("Deleted Subejct with id=" + subjectId);
		log.trace("Finished deleteSubjectFromDepartment() method.");

	}
}
