package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.domain.Lesson;

public class LessonDAOImpl {

	private static final String INSERT_LESSON = "insert into lessons (number,date,subject_id,teacher_id,group_id,room_id) values(?,?,?,?,?,?)";

	private static final String FIND_TEACHER_BY_ID = "select * from lessons where id=?";
	private static final String FIND_ALL_TEACHERS = "select * from lessons";
	private static final String UPDATE_TEACHER = "update lessons set number=?,date=?,subject_id=?,teacher_id=?,group_id=?,room_id=? where id =?";
	private static final String DELETE_TEACHER = "delete from lessons where id =?";

	public Lesson create(Lesson lesson) {
		Lesson newLesson = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_LESSON,
						Statement.RETURN_GENERATED_KEYS);) {

			if (lesson.getNumber() == 0) {
				statement.setInt(1, 0);
			} else {
				statement.setInt(1, lesson.getNumber());
			}
			if (lesson.getDate() == null) {
				statement.setTimestamp(2, null);
			} else {
				Timestamp date = new Timestamp(lesson.getDate().getTime());
				statement.setTimestamp(2, date);
			}

			statement.setInt(3, lesson.getSubject().getId());
			statement.setInt(4, lesson.getTeacher().getId());
			statement.setInt(5, lesson.getGroup().getId());
			statement.setInt(6, lesson.getRoom().getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newLesson = new Lesson();
					newLesson.setId(resultSet.getInt("id"));
					newLesson.setNumber(resultSet.getInt("number"));
					newLesson.setDate(resultSet.getDate("date"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return newLesson;
	}

	public Lesson findById(int id) {
		Lesson lessonRecieved = new Lesson();

		try (Connection connection = Connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(FIND_TEACHER_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					lessonRecieved.setId(resultSet.getInt("id"));
					lessonRecieved.setNumber(resultSet.getInt("number"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return lessonRecieved;
	}

	public List<Lesson> findAll() {
		List<Lesson> lessonRecieved = new ArrayList<Lesson>();

		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_TEACHERS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Lesson lesson = new Lesson();
				lesson.setId(resultSet.getInt("id"));
				lesson.setNumber(resultSet.getInt("number"));
				lessonRecieved.add(lesson);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return lessonRecieved;
	}

	public Lesson update(Lesson lesson) {
		Lesson newLesson = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_TEACHER,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setInt(1, lesson.getNumber());
			Timestamp date = new Timestamp(lesson.getDate().getTime());
			statement.setTimestamp(2, date);
			statement.setInt(3, lesson.getSubject().getId());
			statement.setInt(4, lesson.getTeacher().getId());
			statement.setInt(5, lesson.getGroup().getId());
			statement.setInt(6, lesson.getRoom().getId());
			statement.setInt(7, lesson.getId());

			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newLesson = new Lesson();
					newLesson.setId(resultSet.getInt("id"));
					newLesson.setNumber(resultSet.getInt("number"));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return newLesson;
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
