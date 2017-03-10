package com.nikitachizhik91.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.dao.Connector;
import com.nikitachizhik91.university.dao.StudentDao;
import com.nikitachizhik91.university.model.Student;

public class StudentDaoImpl implements StudentDao {
	private Connector connector;
	private static final String INSERT_STUDENT = "insert into students (name) values(?)";
	private static final String FIND_STUDENT_BY_ID = "select * from students where id=?";
	private static final String FIND_ALL_STUDENTS = "select * from students";
	private static final String UPDATE_STUDENT = "update students set name=? where id =?";
	private static final String DELETE_STUDENT = "delete from students where id =?";

	public StudentDaoImpl() {
		connector = new Connector();
	}

	public Student create(Student studentArg) {
		Student student = null;

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, studentArg.getName());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					student = new Student();
					student.setId(resultSet.getInt("id"));
					student.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return student;
	}

	public Student findById(int id) {
		Student student = null;
		try (Connection connection = connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(FIND_STUDENT_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					student = new Student();
					student.setId(resultSet.getInt("id"));
					student.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return student;
	}

	public List<Student> findAll() {
		List<Student> students = new ArrayList<Student>();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_STUDENTS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt("id"));
				student.setName(resultSet.getString("name"));
				students.add(student);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return students;
	}

	public Student update(Student studentArg) {
		Student student = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, studentArg.getName());
			statement.setInt(2, studentArg.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					student = new Student();
					student.setId(resultSet.getInt("id"));
					student.setName(resultSet.getString("name"));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return student;
	}

	public void delete(int id) {
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
