package com.nikitachizhik91.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.dao.Connector;
import com.nikitachizhik91.university.dao.DateConverter;
import com.nikitachizhik91.university.dao.GroupDao;
import com.nikitachizhik91.university.dao.RoomDao;
import com.nikitachizhik91.university.dao.SubjectDao;
import com.nikitachizhik91.university.dao.TeacherDao;
import com.nikitachizhik91.university.domain.DaoException;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.model.Teacher;

public class TimetableDao {
	private Connector connector;
	private final static Logger log = LogManager.getLogger(LessonDaoImpl.class.getName());
	private static final String GET_TEACHER_TIMETABLE_FOR_DAY = "write SQL query!!";
	private static final String GET_TEACHER_TIMETABLE_FOR_MONTH = null;
	private static final String GET_STUDENT_TIMETABLE_FOR_DAY = null;
	private static final String GET_STUDENT_TIMETABLE_FOR_MONTH = null;
	private GroupDao groupDao;
	private RoomDao roomDao;
	private SubjectDao subjectDao;
	private TeacherDao teacherDao;

	// TODO "write SQL query for getTeacherTimetableForDay" + u menya est
	// zagotovka;
	public TimetableDao() {
		connector = new Connector();

		connector = new Connector();
		groupDao = new GroupDaoImpl();
		roomDao = new RoomDaoImpl();
		subjectDao = new SubjectDaoImpl();
		teacherDao = new TeacherDaoImpl();
	}

	public List<Lesson> getTeacherTimetableForDay(Teacher teacher, Date date) throws DaoException {
		log.trace("Started getTeacherTimetableForDay().");

		List<Lesson> lessons = new ArrayList<Lesson>();

		log.trace("Getting Conncetion and creating prepared statement and getting the result set.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_TEACHER_TIMETABLE_FOR_DAY);
				ResultSet resultSet = statement.executeQuery();) {

			log.debug("Executed query :" + statement);
			log.trace("Got the result set.");

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
			log.error("Cannot get lessons for teacher timetable for day.", e);
			throw new DaoException("Cannot get lessons for teacher timetable for day.", e);
		}
		log.info("Got " + lessons.size() + " lessons for teacher timetable for day");
		log.trace("Finished getTeacherTimetableForDay() method.");
		return lessons;
	}

	public List<Lesson> getTeacherTimetableForMonth(Teacher teacher, Date date) throws DaoException {
		log.trace("Started getTeacherTimetableForMonth().");

		List<Lesson> lessons = new ArrayList<Lesson>();

		log.trace("Getting Conncetion and creating prepared statement and getting the result set.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_TEACHER_TIMETABLE_FOR_MONTH);
				ResultSet resultSet = statement.executeQuery();) {

			log.debug("Executed query :" + statement);
			log.trace("Got the result set.");

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
			log.error("Cannot get lessons for teacher timetable for Month.", e);
			throw new DaoException("Cannot get lessons for teacher timetable for Month.", e);
		}
		log.info("Got " + lessons.size() + " lessons for teacher timetable for Month");
		log.trace("Finished getTeacherTimetableForMonth() method.");

		return lessons;
	}

	public List<Lesson> getStudentTimetableForDay(Student student, Date date) throws DaoException {
		log.trace("Started getStudentTimetableForDay().");

		List<Lesson> lessons = new ArrayList<Lesson>();

		log.trace("Getting Conncetion and creating prepared statement and getting the result set.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_STUDENT_TIMETABLE_FOR_DAY);
				ResultSet resultSet = statement.executeQuery();) {

			log.debug("Executed query :" + statement);
			log.trace("Got the result set.");

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
			log.error("Cannot get lessons for Student timetable for Day.", e);
			throw new DaoException("Cannot get lessons for Student timetable for Day.", e);
		}
		log.info("Got " + lessons.size() + " lessons for Student timetable for Day");
		log.trace("Finished getStudentTimetableForDay() method.");
		return lessons;
	}

	public List<Lesson> getStudentTimetableFoMonth(Student student, Date date) throws DaoException {
		log.trace("Started getStudentTimetableFoMonth().");

		List<Lesson> lessons = new ArrayList<Lesson>();

		log.trace("Getting Conncetion and creating prepared statement and getting the result set.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_STUDENT_TIMETABLE_FOR_MONTH);
				ResultSet resultSet = statement.executeQuery();) {

			log.debug("Executed query :" + statement);
			log.trace("Got the result set.");

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
			log.error("Cannot get lessons for Student timetable for Month.", e);
			throw new DaoException("Cannot get lessons for Student timetable for Month.", e);
		}
		log.info("Got " + lessons.size() + " lessons for Student timetable for Month");
		log.trace("Finished getStudentTimetableFoMonth() method.");

		return lessons;
	}

}
