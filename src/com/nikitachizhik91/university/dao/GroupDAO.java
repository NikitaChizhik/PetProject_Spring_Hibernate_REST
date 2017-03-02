package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.domain.Group;
import com.nikitachizhik91.university.domain.Student;

public interface GroupDAO extends Crud<Group> {



	private static final String create = "insert into students (name) values(?)";
	private static final String findById = "select * from students where student_id=?";
	private static final String findAll = "select * from students order by name";
	private static final String update = "update students set name=? where student_id =?";
	private static final String delete = "delete from students where student_id =?";

	public Student create(Student student) {
		Student newStudent = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, student.getName());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newStudent = new Student();
					newStudent.setId(resultSet.getInt("student_id"));
					newStudent.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return newStudent;
	}

	public Student findById(int id) {
		Student studentRecieved = new Student();

		try (Connection connection = Connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(findById)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					studentRecieved.setId(resultSet.getInt("student_id"));
					studentRecieved.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return studentRecieved;
	}

	public List<Student> findAll() {
		List<Student> studentRecieved = new ArrayList<Student>();

		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(findAll);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt("student_id"));
				student.setName(resultSet.getString("name"));
				studentRecieved.add(student);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return studentRecieved;
	}

	public Student update(Student student) {
		Student newStudent = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, student.getName());
			statement.setInt(2, student.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newStudent = new Student();
					newStudent.setId(resultSet.getInt("student_id"));
					newStudent.setName(resultSet.getString("name"));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return newStudent;
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
