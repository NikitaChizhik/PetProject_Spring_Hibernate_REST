package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.domain.Teacher;

public class TeacherDAOImpl implements TeacherDAO {
	private static final String INSERT_TEACHER = "insert into teachers (name) values(?)";
	private static final String FIND_TEACHER_BY_ID = "select * from teachers where teacher_id=?";
	private static final String FIND_ALL_TEACHERS = "select * from teachers";
	private static final String UPDATE_TEACHER = "update teachers set name=? where teacher_id =?";
	private static final String DELETE_TEACHER = "delete from teachers where teacher_id =?";

	public Teacher create(Teacher teacher) {
		Teacher newTeacher = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_TEACHER,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, teacher.getName());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newTeacher = new Teacher();
					newTeacher.setId(resultSet.getInt("teacher_id"));
					newTeacher.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return newTeacher;
	}

	public Teacher findById(int id) {
		Teacher teacherRecieved = new Teacher();

		try (Connection connection = Connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(FIND_TEACHER_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					teacherRecieved.setId(resultSet.getInt("teacher_id"));
					teacherRecieved.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return teacherRecieved;
	}

	public List<Teacher> findAll() {
		List<Teacher> roomsRecieved = new ArrayList<Teacher>();

		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_TEACHERS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(resultSet.getInt("teacher_id"));
				teacher.setName(resultSet.getString("name"));
				roomsRecieved.add(teacher);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return roomsRecieved;
	}

	public Teacher update(Teacher teacher) {
		Teacher newTeacher = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_TEACHER,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, teacher.getName());
			statement.setInt(2, teacher.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newTeacher = new Teacher();
					newTeacher.setId(resultSet.getInt("teacher_id"));
					newTeacher.setName(resultSet.getString("name"));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return newTeacher;
	}

	public void delete(int id) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_TEACHER);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
