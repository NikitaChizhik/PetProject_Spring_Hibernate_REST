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
import org.springframework.beans.factory.annotation.Autowired;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.SubjectDao;
import com.nikitachizhik91.university.dao.TeacherDao;
import com.nikitachizhik91.university.model.Teacher;

public class TeacherDaoImpl implements TeacherDao {
	
	private final static Logger log = LogManager.getLogger(TeacherDaoImpl.class.getName());
	@Autowired
	private DataSource dataSource;
	private static final String INSERT_TEACHER = "insert into teachers (name,subject_id) values(?,?)";
	private static final String FIND_TEACHER_BY_ID = "select * from teachers where id=?";
	private static final String FIND_ALL_TEACHERS = "select * from teachers";
	private static final String UPDATE_TEACHER = "update teachers set name=?,subject_id=? where id =?";
	private static final String DELETE_TEACHER = "delete from teachers where id =?";
	@Autowired
	private SubjectDao subjectDao;
	private static final String FIND_TEACHERS_WITHOUT_DEPARTMENT = "SELECT id FROM teachers t WHERE NOT EXISTS(SELECT NULL FROM departments_teachers dt WHERE dt.teacher_id = t.id)";

	public Teacher create(Teacher teacherArg) throws DaoException {
		log.trace("Started create() method.");
		Teacher teacher = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_TEACHER,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, teacherArg.getName());
			statement.setInt(2, teacherArg.getSubject().getId());

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				log.trace("Got the result set.");
				while (resultSet.next()) {
					teacher = new Teacher();
					teacher.setId(resultSet.getInt("id"));
					teacher.setName(resultSet.getString("name"));
					teacher.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));
				}
			}
		} catch (SQLException e) {
			log.error("Cannot create Teacher :" + teacherArg, e);
			throw new DaoException("Cannot create Teacher :", e);

		}
		log.info("Created a Teacher :" + teacherArg);
		log.trace("Finished create() method.");
		return teacher;
	}

	public Teacher findById(int id) throws DaoException {
		log.trace("Started findById() method.");

		Teacher teacher = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_TEACHER_BY_ID)) {

			statement.setInt(1, id);

			log.trace("Statement :" + statement + " is received.");
			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.executeQuery()) {
				log.debug("Executed query :" + statement);
				log.trace("Got the result set.");

				if (resultSet.next()) {
					teacher = new Teacher();
					teacher.setId(resultSet.getInt("id"));
					teacher.setName(resultSet.getString("name"));
					teacher.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));
				}
			}
		} catch (SQLException e) {
			log.error("Cannot find Teacher with id=" + id, e);
			throw new DaoException("Cannot find Teacher with id=" + id, e);
		}
		log.info("Found the Teacher :" + teacher);
		log.trace("Finished findById() method.");
		return teacher;
	}

	public List<Teacher> findAll() throws DaoException {
		log.trace("Started findAll() method.");

		List<Teacher> teachers = new ArrayList<Teacher>();

		log.trace("Getting Conncetion and creating prepared statement and getting the result set.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_TEACHERS);
				ResultSet resultSet = statement.executeQuery();) {

			log.debug("Executed query :" + statement);
			log.trace("Got the result set.");

			while (resultSet.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(resultSet.getInt("id"));
				teacher.setName(resultSet.getString("name"));
				teacher.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));

				teachers.add(teacher);
			}
		} catch (SQLException e) {
			log.error("Cannot find all teachers.", e);
			throw new DaoException("Cannot find all teachers.", e);
		}
		log.info("Found all teachers :");
		log.trace("Finished findAll() method.");
		return teachers;
	}

	public Teacher update(Teacher teacherArg) throws DaoException {
		log.trace("Started update() method.");

		Teacher teacher = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_TEACHER,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, teacherArg.getName());
			statement.setInt(2, teacherArg.getSubject().getId());
			statement.setInt(3, teacherArg.getId());

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				log.trace("Got the result set.");

				while (resultSet.next()) {
					teacher = new Teacher();
					teacher.setId(resultSet.getInt("id"));
					teacher.setName(resultSet.getString("name"));
					teacher.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));
				}
			}

		} catch (SQLException e) {
			log.error("Cannot update Teacher :" + teacherArg, e);
			throw new DaoException("Cannot update Teacher :" + teacherArg, e);
		}
		log.info("Updated Lesson :" + teacherArg);
		log.trace("Finished update() method.");
		return teacher;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_TEACHER);) {

			statement.setInt(1, id);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete Teacher with id=" + id, e);
			throw new DaoException("Cannot delete Teacher with id=" + id, e);
		}
		log.info("Deleted Teacher with id=" + id);
		log.trace("Finished delete() method.");
	}

	public List<Teacher> findTeachersWithoutDepartment() throws DaoException {
		log.trace("Started findTeachersWithoutDepartment() method.");

		List<Teacher> teachers = new ArrayList<Teacher>();

		log.trace("Getting Conncetion and creating prepared statement and getting the result set.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_TEACHERS_WITHOUT_DEPARTMENT);
				ResultSet resultSet = statement.executeQuery();) {

			log.debug("Executed query :" + statement);
			log.trace("Got the result set.");

			while (resultSet.next()) {

				Teacher teacher = findById(resultSet.getInt("id"));
				teachers.add(teacher);
			}

		} catch (SQLException e) {
			log.error("Cannot find all teachers who are without department.", e);
			throw new DaoException("Cannot find all teachers who are without department.", e);
		}
		log.info("Found all teachers who are without department.");
		log.trace("Finished findTeachersWithoutDepartment() method.");

		return teachers;
	}
}
