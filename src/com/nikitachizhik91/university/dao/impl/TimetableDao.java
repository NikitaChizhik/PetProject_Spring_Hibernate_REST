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
import com.nikitachizhik91.university.model.Teacher;

public class TimetableDao {
	private Connector connector;
	private final static Logger log = LogManager.getLogger(LessonDaoImpl.class.getName());
	private static final String GET_TEACHER_TIMETABLE_FOR_DAY = "write SQL query!!";
	private GroupDao groupDao;
	private RoomDao roomDao;
	private SubjectDao subjectDao;
	private TeacherDao teacherDao;

	// TODO "write SQL query for getTeacherTimetableForDay" + u menya est +
	// +zagotovka;
	public TimetableDao() {
		connector = new Connector();

		connector = new Connector();
		groupDao = new GroupDaoImpl();
		roomDao = new RoomDaoImpl();
		subjectDao = new SubjectDaoImpl();
		teacherDao = new TeacherDaoImpl();
	}

	public List<Lesson> getTeacherTimetableForDay(Teacher teacher, Date date) throws DaoException {
		List<Lesson> lessons = new ArrayList<Lesson>();

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_TEACHER_TIMETABLE_FOR_DAY);
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
			log.error("Cannot find Lessons :", e);
			throw new DaoException("Cannot find Lessons :", e);
		}

		return lessons;
	}

}
