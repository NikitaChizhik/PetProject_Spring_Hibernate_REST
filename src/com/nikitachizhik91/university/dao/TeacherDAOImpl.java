package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;

public class TeacherDAOImpl implements TeacherDAO {
	private static final String INSERT_TEACHER = "insert into teachers (name,subject_id) values(?,?)";
	private static final String FIND_TEACHER_BY_ID = "select * from teachers where id=?";
	private static final String FIND_ALL_TEACHERS = "select * from teachers";
	private static final String UPDATE_TEACHER = "update teachers set name=?,subject_id=? where id =?";
	private static final String DELETE_TEACHER = "delete from teachers where id =?";

	public Teacher create(Teacher teacher) {
		Teacher teacherReceived = null;
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_TEACHER,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, teacher.getName());
			statement.setInt(2, teacher.getSubject().getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					teacherReceived = new Teacher();
					teacherReceived.setId(resultSet.getInt("id"));
					teacherReceived.setName(resultSet.getString("name"));
					Subject subject = new Subject();
					subject.setId(resultSet.getInt("subject_id"));
					teacherReceived.setSubject(subject);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return teacherReceived;
	}

	public Teacher findById(int id) {
		Teacher teacherReceived = new Teacher();
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(FIND_TEACHER_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					teacherReceived.setId(resultSet.getInt("id"));
					teacherReceived.setName(resultSet.getString("name"));
					Subject subject = new Subject();
					subject.setId(resultSet.getInt("subject_id"));
					teacherReceived.setSubject(subject);
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return teacherReceived;
	}

	public List<Teacher> findAll() {
		List<Teacher> teachersReceived = new ArrayList<Teacher>();
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_TEACHERS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Teacher teacherReceived = new Teacher();
				teacherReceived.setId(resultSet.getInt("id"));
				teacherReceived.setName(resultSet.getString("name"));
				Subject subject = new Subject();
				subject.setId(resultSet.getInt("subject_id"));
				teacherReceived.setSubject(subject);

				teachersReceived.add(teacherReceived);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return teachersReceived;
	}

	public Teacher update(Teacher teacher) {
		Teacher teacherReceived = null;
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_TEACHER,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, teacher.getName());
			statement.setInt(2, teacher.getSubject().getId());
			statement.setInt(3, teacher.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					teacherReceived = new Teacher();
					teacherReceived.setId(resultSet.getInt("id"));
					teacherReceived.setName(resultSet.getString("name"));
					Subject subject = new Subject();
					subject.setId(resultSet.getInt("subject_id"));
					teacherReceived.setSubject(subject);
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return teacherReceived;
	}

	public void delete(int id) {
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_TEACHER);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
