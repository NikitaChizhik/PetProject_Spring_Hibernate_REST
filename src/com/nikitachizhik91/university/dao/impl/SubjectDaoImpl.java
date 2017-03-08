package com.nikitachizhik91.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.dao.Connector;
import com.nikitachizhik91.university.dao.SubjectDao;
import com.nikitachizhik91.university.model.Subject;

public class SubjectDaoImpl implements SubjectDao {

	private static final String INSERT_SUBJECT = "insert into subjects (name) values(?)";
	private static final String FIND_SUBJECT_BY_ID = "select * from subjects where id=?";
	private static final String FIND_ALL_SUBJECTS = "select * from subjects";
	private static final String UPDATE_SUBJECT = "update subjects set name=? where id =?";
	private static final String DELETE_SUBJECT = "delete from subjects where id =?";

	public Subject create(Subject subject) {
		Subject subjectReceived = null;
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, subject.getName());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					subjectReceived = new Subject();
					subjectReceived.setId(resultSet.getInt("id"));
					subjectReceived.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return subjectReceived;
	}

	public Subject findById(int id) {
		Subject subjectReceived = new Subject();
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(FIND_SUBJECT_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					subjectReceived.setId(resultSet.getInt("id"));
					subjectReceived.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return subjectReceived;
	}

	public List<Subject> findAll() {
		List<Subject> subjectsReceived = new ArrayList<Subject>();
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_SUBJECTS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Subject subject = new Subject();
				subject.setId(resultSet.getInt("id"));
				subject.setName(resultSet.getString("name"));
				subjectsReceived.add(subject);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return subjectsReceived;
	}

	public Subject update(Subject subject) {
		Subject subjectReceived = null;
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, subject.getName());
			statement.setInt(2, subject.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					subjectReceived = new Subject();
					subjectReceived.setId(resultSet.getInt("id"));
					subjectReceived.setName(resultSet.getString("name"));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return subjectReceived;
	}

	public void delete(int id) {
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
