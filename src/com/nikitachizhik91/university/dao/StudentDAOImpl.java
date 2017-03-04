package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.domain.Student;

public class StudentDAOImpl implements StudentDAO {

	private static final String INSERT_STUDENT = "insert into students (name) values(?)";
	private static final String FIND_STUDENT_BY_ID = "select * from students where id=?";
	private static final String FIND_ALL_STUDENTS = "select * from students";
	private static final String UPDATE_STUDENT = "update students set name=? where id =?";
	private static final String DELETE_STUDENT = "delete from students where id =?";

	public Student create(Student student) {
		Student newStudent = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, student.getName());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newStudent = new Student();
					newStudent.setId(resultSet.getInt("id"));
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

		PreparedStatement statement = connection.prepareStatement(FIND_STUDENT_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					studentRecieved.setId(resultSet.getInt("id"));
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
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_STUDENTS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt("id"));
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
				PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, student.getName());
			statement.setInt(2, student.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newStudent = new Student();
					newStudent.setId(resultSet.getInt("id"));
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
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
