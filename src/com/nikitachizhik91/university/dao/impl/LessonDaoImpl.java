package com.nikitachizhik91.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nikitachizhik91.university.dao.Connector;
import com.nikitachizhik91.university.dao.DateConverter;
import com.nikitachizhik91.university.dao.GroupDao;
import com.nikitachizhik91.university.dao.LessonDao;
import com.nikitachizhik91.university.dao.RoomDao;
import com.nikitachizhik91.university.dao.SubjectDao;
import com.nikitachizhik91.university.dao.TeacherDao;
import com.nikitachizhik91.university.model.Lesson;

public class LessonDaoImpl implements LessonDao {
	private Connector connector;
	private static final String INSERT_LESSON = "insert into lessons (number,date,subject_id,teacher_id,group_id,room_id) values(?,?,?,?,?,?)";
	private static final String FIND_LESSON_BY_ID = "select * from lessons where id=?";
	private static final String FIND_ALL_LESSONS = "select * from lessons";
	private static final String UPDATE_LESSON = "update lessons set number=?,date=?,subject_id=?,teacher_id=?,group_id=?,room_id=? where id =?";
	private static final String DELETE_LESSON = "delete from lessons where id =?";
	private GroupDao groupDao;
	private RoomDao roomDao;
	private SubjectDao subjectDao;
	private TeacherDao teacherDao;
	private Lesson lessonReceived;

	public LessonDaoImpl() {
		connector = new Connector();
		groupDao = new GroupDaoImpl();
		roomDao = new RoomDaoImpl();
		subjectDao = new SubjectDaoImpl();
		teacherDao = new TeacherDaoImpl();
		lessonReceived = new Lesson();
	}

	public Lesson create(Lesson lesson) {
		lessonReceived = null;

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_LESSON,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setInt(1, lesson.getNumber());
			Timestamp timestamp = DateConverter.toTimestamp(lesson.getDate());
			statement.setTimestamp(2, timestamp);
			statement.setInt(3, lesson.getSubject().getId());
			statement.setInt(4, lesson.getTeacher().getId());
			statement.setInt(5, lesson.getGroup().getId());
			statement.setInt(6, lesson.getRoom().getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					lessonReceived = new Lesson();
					lessonReceived.setId(resultSet.getInt("id"));
					lessonReceived.setNumber(resultSet.getInt("number"));

					Date date = DateConverter.toDate(resultSet.getTimestamp("date"));
					lessonReceived.setDate(date);

					lessonReceived.setGroup(groupDao.findById(resultSet.getInt("group_id")));

					lessonReceived.setRoom(roomDao.findById(resultSet.getInt("room_id")));

					lessonReceived.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));

					lessonReceived.setTeacher(teacherDao.findById(resultSet.getInt("teacher_id")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return lessonReceived;
	}

	public Lesson findById(int id) {

		lessonReceived = null;
		try (Connection connection = connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(FIND_LESSON_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					lessonReceived = new Lesson();
					lessonReceived.setId(resultSet.getInt("id"));
					lessonReceived.setNumber(resultSet.getInt("number"));

					Date date = DateConverter.toDate(resultSet.getTimestamp("date"));
					lessonReceived.setDate(date);

					lessonReceived.setGroup(groupDao.findById(resultSet.getInt("group_id")));

					lessonReceived.setRoom(roomDao.findById(resultSet.getInt("room_id")));

					lessonReceived.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));

					lessonReceived.setTeacher(teacherDao.findById(resultSet.getInt("teacher_id")));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return lessonReceived;
	}

	public List<Lesson> findAll() {

		List<Lesson> lessonsReceived = new ArrayList<Lesson>();

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_LESSONS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {

				lessonReceived = new Lesson();
				lessonReceived.setId(resultSet.getInt("id"));
				lessonReceived.setNumber(resultSet.getInt("number"));

				Date date = DateConverter.toDate(resultSet.getTimestamp("date"));
				lessonReceived.setDate(date);

				lessonReceived.setGroup(groupDao.findById(resultSet.getInt("group_id")));

				lessonReceived.setRoom(roomDao.findById(resultSet.getInt("room_id")));

				lessonReceived.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));

				lessonReceived.setTeacher(teacherDao.findById(resultSet.getInt("teacher_id")));

				lessonsReceived.add(lessonReceived);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return lessonsReceived;
	}

	public Lesson update(Lesson lesson) {

		lessonReceived = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_LESSON,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setInt(1, lesson.getNumber());
			Timestamp timestamp = DateConverter.toTimestamp(lesson.getDate());
			statement.setTimestamp(2, timestamp);
			statement.setInt(3, lesson.getSubject().getId());
			statement.setInt(4, lesson.getTeacher().getId());
			statement.setInt(5, lesson.getGroup().getId());
			statement.setInt(6, lesson.getRoom().getId());
			statement.setInt(7, lesson.getId());

			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					lessonReceived = new Lesson();
					lessonReceived.setId(resultSet.getInt("id"));
					lessonReceived.setNumber(resultSet.getInt("number"));

					Date date = DateConverter.toDate(resultSet.getTimestamp("date"));
					lessonReceived.setDate(date);

					lessonReceived.setGroup(groupDao.findById(resultSet.getInt("group_id")));

					lessonReceived.setRoom(roomDao.findById(resultSet.getInt("room_id")));

					lessonReceived.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));

					lessonReceived.setTeacher(teacherDao.findById(resultSet.getInt("teacher_id")));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return lessonReceived;
	}

	public void delete(int id) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_LESSON);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
