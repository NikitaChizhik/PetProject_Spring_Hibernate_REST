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
import org.springframework.stereotype.Repository;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.SubjectDao;
import com.nikitachizhik91.university.model.Subject;

@Repository
public class SubjectDaoImpl implements SubjectDao {

	private final static Logger log = LogManager.getLogger(SubjectDaoImpl.class.getName());
	@Autowired
	private DataSource dataSource;
	private static final String INSERT_SUBJECT = "insert into subjects (name) values(?)";
	private static final String FIND_SUBJECT_BY_ID = "select * from subjects where id=?";
	private static final String FIND_ALL_SUBJECTS = "select * from subjects";
	private static final String UPDATE_SUBJECT = "update subjects set name=? where id =?";
	private static final String DELETE_SUBJECT = "delete from subjects where id =?";

	private static final String FIND_SUBJECTS_WITHOUT_DEPARTMENT = "SELECT id FROM subjects s WHERE NOT EXISTS(SELECT NULL FROM departments_subjects ds WHERE ds.subject_id = s.id)";

	public Subject create(Subject subjectArg) throws DaoException {
		log.trace("Started create() method.");
		Subject subject = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, subjectArg.getName());
			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				log.trace("Got the result set.");
				while (resultSet.next()) {
					subject = new Subject();
					subject.setId(resultSet.getInt("id"));
					subject.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			log.error("Cannot create Subject :" + subjectArg, e);
			throw new DaoException("Cannot create Subject :", e);

		}
		log.info("Created a Subject :" + subjectArg);
		log.trace("Finished create() method.");
		return subject;
	}

	public Subject findById(int id) throws DaoException {
		log.trace("Started findById() method.");

		Subject subject = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_SUBJECT_BY_ID)) {

			statement.setInt(1, id);

			log.trace("Statement :" + statement + " is received.");
			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.executeQuery()) {
				log.debug("Executed query :" + statement);
				log.trace("Got the result set.");

				if (resultSet.next()) {
					subject = new Subject();
					subject.setId(resultSet.getInt("id"));
					subject.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			log.error("Cannot find Subject with id=" + id, e);
			throw new DaoException("Cannot find Subject with id=" + id, e);
		}
		log.info("Found the Subject :" + subject);
		log.trace("Finished findById() method.");
		return subject;
	}

	public List<Subject> findAll() throws DaoException {
		log.trace("Started findAll() method.");

		List<Subject> subjects = new ArrayList<Subject>();

		log.trace("Getting Conncetion and creating prepared statement and getting the result set.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_SUBJECTS);
				ResultSet resultSet = statement.executeQuery();) {

			log.debug("Executed query :" + statement);
			log.trace("Got the result set.");

			while (resultSet.next()) {
				Subject subject = new Subject();
				subject.setId(resultSet.getInt("id"));
				subject.setName(resultSet.getString("name"));
				subjects.add(subject);
			}
		} catch (SQLException e) {
			log.error("Cannot find all subjects.", e);
			throw new DaoException("Cannot find all subjects.", e);
		}
		log.info("Found all subjects :");
		log.trace("Finished findAll() method.");
		return subjects;
	}

	public Subject update(Subject subjectArg) throws DaoException {
		log.trace("Started update() method.");

		Subject subject = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, subjectArg.getName());
			statement.setInt(2, subjectArg.getId());

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				log.trace("Got the result set.");

				while (resultSet.next()) {
					subject = new Subject();
					subject.setId(resultSet.getInt("id"));
					subject.setName(resultSet.getString("name"));
				}
			}

		} catch (SQLException e) {
			log.error("Cannot update Subject :" + subjectArg, e);
			throw new DaoException("Cannot update Subject :" + subjectArg, e);
		}
		log.info("Updated Subject :" + subjectArg);
		log.trace("Finished update() method.");
		return subject;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT);) {

			statement.setInt(1, id);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete Subject with id=" + id, e);
			throw new DaoException("Cannot delete Subject with id=" + id, e);
		}
		log.info("Deleted Subject with id=" + id);
		log.trace("Finished delete() method.");
	}

	public List<Subject> findSubjectsWithoutDepartment() throws DaoException {
		log.trace("Started findSubjectsWithoutDepartment() method.");

		List<Subject> subjects = new ArrayList<Subject>();

		log.trace("Getting Conncetion and creating prepared statement and getting the result set.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_SUBJECTS_WITHOUT_DEPARTMENT);
				ResultSet resultSet = statement.executeQuery();) {

			log.debug("Executed query :" + statement);
			log.trace("Got the result set.");

			while (resultSet.next()) {

				Subject subject = findById(resultSet.getInt("id"));
				subjects.add(subject);
			}
		} catch (SQLException e) {
			log.error("Cannot find all subjects which are without department.", e);
			throw new DaoException("Cannot find all subjects which are without department.", e);
		}
		log.info("Found all subjects which are without department.");
		log.trace("Finished findSubjectsWithoutDepartment() method.");

		return subjects;
	}

}
