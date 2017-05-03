package com.nikitachizhik91.university.dao.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.LessonDao;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.model.Teacher;

@Repository
public class LessonDaoImpl implements LessonDao {

	private final static Logger log = LogManager.getLogger(LessonDaoImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	public Lesson create(Lesson lesson) throws DaoException {

		log.trace("Started create() method.");

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Integer id = (Integer) session.save(lesson);
			session.getTransaction().commit();
			lesson.setId(id);
		}

		log.info("Created a Lesson :" + lesson);
		log.trace("Finished create() method.");

		return lesson;
	}

	public Lesson findById(int id) throws DaoException {
		log.trace("Started findById() method.");

		Lesson lesson = null;

		try (Session session = sessionFactory.openSession()) {
			lesson = session.get(Lesson.class, id);
		}

		log.info("Found the Lesson :" + lesson);
		log.trace("Finished findById() method.");

		return lesson;
	}

	@SuppressWarnings("unchecked")
	public List<Lesson> findAll() throws DaoException {
		log.trace("Started findAll() method.");

		List<Lesson> lessons = null;
		try (Session session = sessionFactory.openSession()) {
			lessons = (List<Lesson>) session.createQuery("from Lesson").list();
		}

		log.info("Found all Lessons :");
		log.trace("Finished findAll() method.");

		return lessons;
	}

	public Lesson update(Lesson lesson) throws DaoException {
		log.trace("Started update() method.");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.update(lesson);
			session.getTransaction().commit();
		}

		log.info("Updated Lesson :" + lesson);
		log.trace("Finished update() method.");

		return lesson;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.delete(session.get(Lesson.class, id));
			session.getTransaction().commit();
		}

		log.info("Deleted Lesson with id=" + id);
		log.trace("Finished delete() method.");
	}

	@SuppressWarnings("unchecked")
	public List<Lesson> getTeacherTimetableForDay(Teacher teacher, Date date) throws DaoException {
		log.trace("Started getTeacherTimetableForDay().");

		List<Lesson> lessons;

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		gregorianCalendar.set(Calendar.HOUR_OF_DAY, 00);
		gregorianCalendar.set(Calendar.MINUTE, 00);
		gregorianCalendar.set(Calendar.SECOND, 00);
		Timestamp startDate = new Timestamp(gregorianCalendar.getTimeInMillis());

		gregorianCalendar.set(Calendar.HOUR_OF_DAY, 23);
		gregorianCalendar.set(Calendar.MINUTE, 59);
		gregorianCalendar.set(Calendar.SECOND, 59);
		Timestamp endDate = new Timestamp(gregorianCalendar.getTimeInMillis());

		try (Session session = sessionFactory.openSession()) {
			lessons = (List<Lesson>) session
					.createQuery("FROM Lesson WHERE teacher = :teacherId AND date BETWEEN  :startDate AND :endDate")
					.setParameter("teacherId", teacher).setParameter("startDate", startDate)
					.setParameter("endDate", endDate).list();

		}

		log.info("Got " + lessons.size() + " lessons for teacher timetable for day");
		log.trace("Finished getTeacherTimetableForDay() method.");

		return lessons;
	}

	@SuppressWarnings("unchecked")
	public List<Lesson> getTeacherTimetableForMonth(Teacher teacher, Date date) throws DaoException {
		log.trace("Started getTeacherTimetableForMonth().");

		List<Lesson> lessons;

		Timestamp startDate = new Timestamp(date.getTime());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 23);
		Date lastDayOfMonth = calendar.getTime();

		Timestamp endDate = new Timestamp(lastDayOfMonth.getTime());

		try (Session session = sessionFactory.openSession()) {
			lessons = (List<Lesson>) session
					.createQuery("FROM Lesson WHERE teacher = :teacherId AND date BETWEEN  :startDate AND :endDate")
					.setParameter("teacherId", teacher).setParameter("startDate", startDate)
					.setParameter("endDate", endDate).list();

		}

		log.trace("Finished getTeacherTimetableForMonth() method.");

		return lessons;
	}

	// SQL-"select * from lessons where group_id=(select group_id from
	// groups_students where student_id=?) and date between ? and ?";
	@SuppressWarnings("unchecked")
	public List<Lesson> getStudentTimetableForDay(Student student, Date date) throws DaoException {
		log.trace("Started getStudentTimetableForDay().");

		List<Lesson> lessons;

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		gregorianCalendar.set(Calendar.HOUR_OF_DAY, 00);
		gregorianCalendar.set(Calendar.MINUTE, 00);
		gregorianCalendar.set(Calendar.SECOND, 00);
		Timestamp startDate = new Timestamp(gregorianCalendar.getTimeInMillis());

		gregorianCalendar.set(Calendar.HOUR_OF_DAY, 23);
		gregorianCalendar.set(Calendar.MINUTE, 59);
		gregorianCalendar.set(Calendar.SECOND, 59);
		Timestamp endDate = new Timestamp(gregorianCalendar.getTimeInMillis());

		try (Session session = sessionFactory.openSession()) {
			lessons = (List<Lesson>) session
					.createQuery(
							"from Lesson where group =(from Group where Student=:studentId) and date between :startDate and :endDate")
					.setParameter("studentId", student).setParameter("startDate", startDate)
					.setParameter("endDate", endDate).list();
			// select * from lessons where group_id=(select group_id from
			// groups_students where student_id=?) and date between ? and ?
		}

		log.info("Got " + lessons.size() + " lessons for student timetable for day");
		log.trace("Finished getStudentTimetableForDay() method.");

		return lessons;
	}

	// SQL-"select * from lessons where group_id=(select group_id from
	// groups_students where student_id=?) and date between ? and ?";
	@SuppressWarnings("unchecked")
	public List<Lesson> getStudentTimetableForMonth(Student student, Date date) throws DaoException {
		log.trace("Started getStudentTimetableForMonth().");

		List<Lesson> lessons;

		Timestamp startDate = new Timestamp(date.getTime());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 23);
		Date lastDayOfMonth = calendar.getTime();

		Timestamp endDate = new Timestamp(lastDayOfMonth.getTime());

		try (Session session = sessionFactory.openSession()) {
			lessons = (List<Lesson>) session
					.createQuery(
							"from Lesson where Group =(from Group where :studentId) and date between :startDate and :endDate")
					.setParameter("studentId", student).setParameter("startDate", startDate)
					.setParameter("endDate", endDate).list();

		}

		log.trace("Finished getStudentTimetableForMonth() method.");

		return lessons;
	}
}
