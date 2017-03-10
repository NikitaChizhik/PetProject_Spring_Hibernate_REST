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
import com.nikitachizhik91.university.dao.TeacherDao;
import com.nikitachizhik91.university.model.Teacher;

public class TeacherDaoImpl implements TeacherDao {
	private Connector connector;
	private static final String INSERT_TEACHER = "insert into teachers (name,subject_id) values(?,?)";
	private static final String FIND_TEACHER_BY_ID = "select * from teachers where id=?";
	private static final String FIND_ALL_TEACHERS = "select * from teachers";
	private static final String UPDATE_TEACHER = "update teachers set name=?,subject_id=? where id =?";
	private static final String DELETE_TEACHER = "delete from teachers where id =?";
	private SubjectDao subjectDao;

	public TeacherDaoImpl() {
		connector = new Connector();
		subjectDao = new SubjectDaoImpl();
	}

	public Teacher create(Teacher teacherArg) {
		Teacher teacher = null;

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_TEACHER,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, teacherArg.getName());
			statement.setInt(2, teacherArg.getSubject().getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					teacher = new Teacher();
					teacher.setId(resultSet.getInt("id"));
					teacher.setName(resultSet.getString("name"));
					teacher.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return teacher;
	}

	public Teacher findById(int id) {
		Teacher teacher = new Teacher();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_TEACHER_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					teacher.setId(resultSet.getInt("id"));
					teacher.setName(resultSet.getString("name"));
					teacher.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return teacher;
	}

	public List<Teacher> findAll() {
		List<Teacher> teachers = new ArrayList<Teacher>();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_TEACHERS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(resultSet.getInt("id"));
				teacher.setName(resultSet.getString("name"));
				teacher.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));

				teachers.add(teacher);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return teachers;
	}

	public Teacher update(Teacher teacherArg) {
		Teacher teacher = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_TEACHER,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, teacherArg.getName());
			statement.setInt(2, teacherArg.getSubject().getId());
			statement.setInt(3, teacherArg.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					teacher = new Teacher();
					teacher.setId(resultSet.getInt("id"));
					teacher.setName(resultSet.getString("name"));
					teacher.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return teacher;
	}

	public void delete(int id) {
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_TEACHER);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
