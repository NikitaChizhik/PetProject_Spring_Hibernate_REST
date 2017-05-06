package com.nikitachizhik91.university.dao.hibernate;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
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
public class LessonDaoHibernate implements LessonDao {

	private final static Logger log = LogManager.getLogger(LessonDaoHibernate.class.getName());

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
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		Date endDate = cal.getTime();
		try (Session session = sessionFactory.openSession()) {
			lessons = (List<Lesson>) session
					.createQuery("from Lesson where teacher = :teacher and date between  :startDate and :endDate")
					.setParameter("teacher", teacher).setParameter("startDate", date).setParameter("endDate", endDate)
					.list();
		}
		log.info("Got " + lessons.size() + " lessons for teacher timetable for day");
		log.trace("Finished getTeacherTimetableForDay() method.");
		return lessons;
	}

	@SuppressWarnings("unchecked")
	public List<Lesson> getTeacherTimetableForMonth(Teacher teacher, Date date) throws DaoException {
		log.trace("Started getTeacherTimetableForMonth().");
		List<Lesson> lessons;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		try (Session session = sessionFactory.openSession()) {
			lessons = (List<Lesson>) session
					.createQuery(
							"from Lesson l where teacher = :teacher and month(l.date)=:month and year(l.date)=:year")
					.setParameter("teacher", teacher).setParameter("month", calendar.get(Calendar.MONTH) + 1)
					.setParameter("year", calendar.get(Calendar.YEAR)).list();
		}
		log.trace("Finished getTeacherTimetableForMonth() method.");
		return lessons;
	}

	@SuppressWarnings("unchecked")
	public List<Lesson> getStudentTimetableForDay(Student student, Date date) throws DaoException {
		log.trace("Started getStudentTimetableForDay().");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		Date endDate = cal.getTime();
		List<Lesson> lessons;
		try (Session session = sessionFactory.openSession()) {
			lessons = (List<Lesson>) session
					.createQuery(
							"from Lesson where group = (from Group g where :student in elements(g.students)) and date between  :startDate and :endDate")
					.setParameter("student", student).setParameter("startDate", date).setParameter("endDate", endDate)
					.list();
		}
		log.info("Got " + lessons.size() + " lessons for student timetable for day");
		log.trace("Finished getStudentTimetableForDay() method.");
		return lessons;
	}

	@SuppressWarnings("unchecked")
	public List<Lesson> getStudentTimetableForMonth(Student student, Date date) throws DaoException {
		log.trace("Started getStudentTimetableForMonth().");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		List<Lesson> lessons;
		try (Session session = sessionFactory.openSession()) {
			System.out.println(date);
			lessons = (List<Lesson>) session
					.createQuery(
							"from Lesson l where group = (from Group g where :student in elements(g.students)) and month(l.date)=:month and year(l.date)=:year")
					.setParameter("student", student).setParameter("month", calendar.get(Calendar.MONTH) + 1)
					.setParameter("year", calendar.get(Calendar.YEAR)).list();
		}
		log.trace("Finished getStudentTimetableForMonth() method.");
		return lessons;
	}
}
