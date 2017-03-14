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
import com.nikitachizhik91.university.dao.StudentDao;
import com.nikitachizhik91.university.model.Student;

public class StudentDaoImpl implements StudentDao {
	private final static Logger log = LogManager.getLogger(StudentDaoImpl.class.getName());
	private Connector connector;
	private static final String INSERT_STUDENT = "insert into students (name) values(?)";
	private static final String FIND_STUDENT_BY_ID = "select * from students where id=?";
	private static final String FIND_ALL_STUDENTS = "select * from students";
	private static final String UPDATE_STUDENT = "update students set name=? where id =?";
	private static final String DELETE_STUDENT = "delete from students where id =?";

	public StudentDaoImpl() {
		connector = new Connector();
	}

	public Student create(Student studentArg) throws DaoException {
		log.trace("Started create() method.");
		Student student = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, studentArg.getName());
			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				log.trace("Got the result set.");
				while (resultSet.next()) {
					student = new Student();
					student.setId(resultSet.getInt("id"));
					student.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			log.error("Cannot create Student :" + studentArg, e);
			throw new DaoException("Cannot create Student :", e);

		}
		log.info("Created a Student :" + studentArg);
		log.trace("Finished create() method.");
		return student;
	}

	public Student findById(int id) throws DaoException {
		log.trace("Started findById() method.");

		Student student = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(FIND_STUDENT_BY_ID)) {

			statement.setInt(1, id);

			log.trace("Statement :" + statement + " is received.");
			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.executeQuery()) {
				log.debug("Executed query :" + statement);
				log.trace("Got the result set.");

				if (resultSet.next()) {
					student = new Student();
					student.setId(resultSet.getInt("id"));
					student.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			log.error("Cannot find Student with id=" + id, e);
			throw new DaoException("Cannot find Student with id=" + id, e);
		}
		log.info("Found the Student :" + student);
		log.trace("Finished findById() method.");
		return student;
	}

	public List<Student> findAll() throws DaoException {
		log.trace("Started findAll() method.");

		List<Student> students = new ArrayList<Student>();

		log.trace("Getting Conncetion and creating prepared statement and getting the result set.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_STUDENTS);
				ResultSet resultSet = statement.executeQuery();) {

			log.debug("Executed query :" + statement);
			log.trace("Got the result set.");

			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt("id"));
				student.setName(resultSet.getString("name"));
				students.add(student);
			}
		} catch (SQLException e) {
			log.error("Cannot find all students.", e);
			throw new DaoException("Cannot find all students.", e);
		}
		log.info("Found all students :");
		log.trace("Finished findAll() method.");
		return students;
	}

	public Student update(Student studentArg) throws DaoException {
		log.trace("Started update() method.");

		Student student = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, studentArg.getName());
			statement.setInt(2, studentArg.getId());

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				log.trace("Got the result set.");

				while (resultSet.next()) {
					student = new Student();
					student.setId(resultSet.getInt("id"));
					student.setName(resultSet.getString("name"));
				}
			}

		} catch (SQLException e) {
			log.error("Cannot update Student :" + studentArg, e);
			throw new DaoException("Cannot update Student :" + studentArg, e);
		}
		log.info("Updated Student :" + studentArg);
		log.trace("Finished update() method.");
		return student;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT);) {

			statement.setInt(1, id);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete Student with id=" + id, e);
			throw new DaoException("Cannot delete Student with id=" + id, e);
		}
		log.info("Deleted Student with id=" + id);
		log.trace("Finished delete() method.");
	}

}
