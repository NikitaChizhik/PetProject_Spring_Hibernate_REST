package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.domain.Room;
import com.nikitachizhik91.university.domain.Teacher;

public class TeacherDAO {

	public Teacher create(Teacher teacher) {
		Teacher newTeacher = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement("insert into teachers (name) values(?)");
				PreparedStatement statement2 = connection.prepareStatement("select * from teachers where name=?");

		) {

			statement.setString(1, teacher.getName());

			statement.executeUpdate();

			statement2.setString(1, teacher.getName());
			try (ResultSet resultSet = statement2.executeQuery()) {
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

	public Teacher getById(int id) {
		Teacher teacherRecieved = new Teacher();

		try (Connection connection = Connector.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from teacherss where teacher_id=?")) {

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

	public List<Teacher> getAll() {
		List<Teacher> teachersRecieved = new ArrayList<Teacher>();

		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement("select * from teachers order by id");
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(resultSet.getInt("teacher_id"));
				teacher.setName(resultSet.getString("name"));
				teachersRecieved.add(teacher);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return teachersRecieved;
	}

	public Teacher update(int id, Teacher teacher) {
		Teacher newTeacher = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("update teachers set name=? where teacher_id =?");
				PreparedStatement statement2 = connection.prepareStatement("select * from teachers where teacher_id=?");) {

			statement.setString(1, teacher.getName());
			statement.setInt(2, id);
			statement.executeUpdate();

			statement2.setInt(1, id);
			try (ResultSet resultSet = statement2.executeQuery();) {
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
				PreparedStatement statement = connection.prepareStatement("delete from teachers where id =?");) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
