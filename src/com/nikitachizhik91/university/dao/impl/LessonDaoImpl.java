package com.nikitachizhik91.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.dao.Connector;
import com.nikitachizhik91.university.dao.DateConverter;
import com.nikitachizhik91.university.dao.GroupDao;
import com.nikitachizhik91.university.dao.LessonDao;
import com.nikitachizhik91.university.dao.RoomDao;
import com.nikitachizhik91.university.dao.SubjectDao;
import com.nikitachizhik91.university.dao.TeacherDao;
import com.nikitachizhik91.university.domain.DaoException;
import com.nikitachizhik91.university.model.Lesson;

public class LessonDaoImpl implements LessonDao {
	private Connector connector;
	private final static Logger log = LogManager.getLogger(LessonDaoImpl.class.getName());
	private static final String INSERT_LESSON = "insert into lessons (number,date,subject_id,teacher_id,group_id,room_id) values(?,?,?,?,?,?)";
	private static final String FIND_LESSON_BY_ID = "select * from lessons where id=?";
	private static final String FIND_ALL_LESSONS = "select * from lessons";
	private static final String UPDATE_LESSON = "update lessons set number=?,date=?,subject_id=?,teacher_id=?,group_id=?,room_id=? where id =?";
	private static final String DELETE_LESSON = "delete from lessons where id =?";
	private GroupDao groupDao;
	private RoomDao roomDao;
	private SubjectDao subjectDao;
	private TeacherDao teacherDao;

	public LessonDaoImpl() {
		connector = new Connector();
		groupDao = new GroupDaoImpl();
		roomDao = new RoomDaoImpl();
		subjectDao = new SubjectDaoImpl();
		teacherDao = new TeacherDaoImpl();
	}

	public Lesson create(Lesson lessonArg) throws DaoException {
		log.trace("Started create() method.");
		Lesson lesson = null;

		log.trace("Getting Conncetion.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_LESSON,
						Statement.RETURN_GENERATED_KEYS);) {
			log.trace("Connection :" + connection + " is received.");
			log.trace("Statement :" + statement + " is received.");
			log.info("Connection :" + connection + " is received.");
			log.info("Statement :" + statement + " is received.");
			log.trace("Fill up statements.");
			statement.setInt(1, lessonArg.getNumber());
			statement.setTimestamp(2, DateConverter.toTimestamp(lessonArg.getDate()));
			statement.setInt(3, lessonArg.getSubject().getId());
			statement.setInt(4, lessonArg.getTeacher().getId());
			statement.setInt(5, lessonArg.getGroup().getId());
			statement.setInt(6, lessonArg.getRoom().getId());
			log.trace("Filled up statements.");
			log.info("Filled up statements.");
			statement.executeUpdate();
			log.trace("Executed query.");
			log.info("Executed query.");

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				if (resultSet == null) {
					log.error("LessonDaoImpl create() - ResultSet is null");
					throw new DaoException("LessonDaoImpl create() - ResultSet is null");
				}
				log.trace("Got the result set.");

				resultSet.next();

				log.trace("Setting data to the Lesson.");
				lesson = new Lesson();
				lesson.setId(resultSet.getInt("id"));
				lesson.setNumber(resultSet.getInt("number"));

				Date date = DateConverter.toDate(resultSet.getTimestamp("date"));
				lesson.setDate(date);

				lesson.setGroup(groupDao.findById(resultSet.getInt("group_id")));

				lesson.setRoom(roomDao.findById(resultSet.getInt("room_id")));

				lesson.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));

				lesson.setTeacher(teacherDao.findById(resultSet.getInt("teacher_id")));
				log.trace("Finished setting data to the Lesson.");

			}
			log.trace("Resultset is closed.");
		} catch (SQLException e) {
			log.error("Prepared statement is wrong :" + e.toString());
			throw new DaoException("Prepared statement is wrong :" + e);
		}

		log.trace("Connection and PreparedStatement are closed.");
		log.trace("Return lesson from database :" + lesson);
		log.info("Created a lesson :" + lesson);
		log.trace("Finished create() method.");
		return lesson;
	}

	public Lesson findById(int id) {

		Lesson lesson = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_LESSON_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					lesson = new Lesson();
					lesson.setId(resultSet.getInt("id"));
					lesson.setNumber(resultSet.getInt("number"));

					lesson.setDate(DateConverter.toDate(resultSet.getTimestamp("date")));

					lesson.setGroup(groupDao.findById(resultSet.getInt("group_id")));

					lesson.setRoom(roomDao.findById(resultSet.getInt("room_id")));

					lesson.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));

					lesson.setTeacher(teacherDao.findById(resultSet.getInt("teacher_id")));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return lesson;
	}

	public List<Lesson> findAll() {

		List<Lesson> lessons = new ArrayList<Lesson>();

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_LESSONS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {

				Lesson lesson = new Lesson();
				lesson.setId(resultSet.getInt("id"));
				lesson.setNumber(resultSet.getInt("number"));

				lesson.setDate(DateConverter.toDate(resultSet.getTimestamp("date")));

				lesson.setGroup(groupDao.findById(resultSet.getInt("group_id")));

				lesson.setRoom(roomDao.findById(resultSet.getInt("room_id")));

				lesson.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));

				lesson.setTeacher(teacherDao.findById(resultSet.getInt("teacher_id")));

				lessons.add(lesson);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return lessons;
	}

	public Lesson update(Lesson lessonArg) {

		Lesson lesson = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_LESSON,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setInt(1, lessonArg.getNumber());
			statement.setTimestamp(2, DateConverter.toTimestamp(lessonArg.getDate()));
			statement.setInt(3, lessonArg.getSubject().getId());
			statement.setInt(4, lessonArg.getTeacher().getId());
			statement.setInt(5, lessonArg.getGroup().getId());
			statement.setInt(6, lessonArg.getRoom().getId());
			statement.setInt(7, lessonArg.getId());

			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					lesson = new Lesson();
					lesson.setId(resultSet.getInt("id"));
					lesson.setNumber(resultSet.getInt("number"));

					lesson.setDate(DateConverter.toDate(resultSet.getTimestamp("date")));

					lesson.setGroup(groupDao.findById(resultSet.getInt("group_id")));

					lesson.setRoom(roomDao.findById(resultSet.getInt("room_id")));

					lesson.setSubject(subjectDao.findById(resultSet.getInt("subject_id")));

					lesson.setTeacher(teacherDao.findById(resultSet.getInt("teacher_id")));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return lesson;
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
