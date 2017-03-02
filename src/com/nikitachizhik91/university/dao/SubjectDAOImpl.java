package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.domain.Subject;

public class SubjectDAOImpl implements SubjectDAO {

	private static final String create = "insert into subjects (name) values(?)";
	private static final String findById = "select * from subjects where subject_id=?";
	private static final String findAll = "select * from subjects order by name";
	private static final String update = "update subjects set name=? where subject_id =?";
	private static final String delete = "delete from subjects where subject_id =?";

	public Subject create(Subject subject) {
		Subject newSubject = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, subject.getName());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newSubject = new Subject();
					newSubject.setId(resultSet.getInt("subject_id"));
					newSubject.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return newSubject;
	}

	public Subject findById(int id) {
		Subject subjectRecieved = new Subject();

		try (Connection connection = Connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(findById)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					subjectRecieved.setId(resultSet.getInt("subject_id"));
					subjectRecieved.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return subjectRecieved;
	}

	public List<Subject> findAll() {
		List<Subject> subjectsRecieved = new ArrayList<Subject>();

		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(findAll);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Subject subject = new Subject();
				subject.setId(resultSet.getInt("subject_id"));
				subject.setName(resultSet.getString("name"));
				subjectsRecieved.add(subject);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return subjectsRecieved;
	}

	public Subject update(Subject subject) {
		Subject newSubject = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, subject.getName());
			statement.setInt(2, subject.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newSubject = new Subject();
					newSubject.setId(resultSet.getInt("subject_id"));
					newSubject.setName(resultSet.getString("name"));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return newSubject;
	}

	public void delete(int id) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(delete);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
