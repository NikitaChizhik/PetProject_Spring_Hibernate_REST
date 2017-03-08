package com.nikitachizhik91.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.nikitachizhik91.university.dao.Connector;
import com.nikitachizhik91.university.dao.StudentDao;
import com.nikitachizhik91.university.model.Student;

public class Groups_Students {
	private static final String FIND_STUDENTS_BY_GROUP_ID = "select student_id from groups_students where group_id=?";
	private static final String DELETE_GROUP = "delete from groups_students where group_id=?";

	public Set<Student> findStudentsByGroupId(int id) {

		Set<Student> students = new HashSet<Student>();
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_STUDENTS_BY_GROUP_ID,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setInt(1, id);
			StudentDao studentDao = new StudentDaoImpl();

			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {

					studentDao.findById(resultSet.getInt("student_id"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	public void deleteGroup(int id) {

		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_GROUP);) {

			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
