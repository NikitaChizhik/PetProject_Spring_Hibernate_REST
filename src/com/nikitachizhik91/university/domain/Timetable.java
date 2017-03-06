package com.nikitachizhik91.university.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nikitachizhik91.university.dao.impl.LessonDAOImpl;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.model.Teacher;

/**
 * @author nikita
 *
 */
public class Timetable {

	public void addLesson(Lesson lesson) {

		LessonDAOImpl lessonDAOImpl = new LessonDAOImpl();
		lessonDAOImpl.create(lesson);
	}

	public void deleteLesson(Lesson lesson) {
		LessonDAOImpl lessonDAOImpl = new LessonDAOImpl();
		lessonDAOImpl.delete(lesson.getId());
	}

	public List<Lesson> getTeachersTimetableForDay(Teacher teacher, Date date) {
		if (teacher == null || date == null) {
			throw new IllegalArgumentException();
		}
		LessonDAOImpl lessonDAOImpl = new LessonDAOImpl();
		List<Lesson> lessons = lessonDAOImpl.findAll();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
		List<Lesson> timetable = new ArrayList<Lesson>();

		for (Lesson lesson : lessons) {
			if (dateFormat.format(date).equals(dateFormat.format(lesson.getDate()))
					&& lesson.getTeacher().equals(teacher)) {

				timetable.add(lesson);
			}
		}

		return timetable;
	}

	public List<Lesson> getTeachersTimetableForMonth(Teacher teacher, Date date) {
		if (teacher == null || date == null) {
			throw new IllegalArgumentException();
		}

		LessonDAOImpl lessonDAOImpl = new LessonDAOImpl();
		List<Lesson> lessons = lessonDAOImpl.findAll();

		SimpleDateFormat dateFormat = new SimpleDateFormat("M-yyyy");
		List<Lesson> timetable = new ArrayList<Lesson>();

		for (Lesson lesson : lessons) {

			if (dateFormat.format(date).equals(dateFormat.format(lesson.getDate()))
					&& lesson.getTeacher().equals(teacher)) {
				timetable.add(lesson);
			}
		}
		return timetable;
	}

	public List<Lesson> getStudentsTimetableForDay(Student student, Date date) {
		if (student == null || date == null) {
			throw new IllegalArgumentException();
		}

		LessonDAOImpl lessonDAOImpl = new LessonDAOImpl();
		List<Lesson> lessons = lessonDAOImpl.findAll();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
		List<Lesson> timetable = new ArrayList<Lesson>();

		for (Lesson lesson : lessons) {

			if (dateFormat.format(date).equals(dateFormat.format(lesson.getDate()))
					&& lesson.getGroup().getStudents().contains(student)) {

				timetable.add(lesson);

			}
		}
		return timetable;
	}

	public List<Lesson> getStudentsTimetableFoMonth(Student student, Date date) {
		if (student == null || date == null) {
			throw new IllegalArgumentException();
		}

		LessonDAOImpl lessonDAOImpl = new LessonDAOImpl();
		List<Lesson> lessons = lessonDAOImpl.findAll();

		SimpleDateFormat dateFormat = new SimpleDateFormat("M-yyyy");
		List<Lesson> timetable = new ArrayList<Lesson>();

		for (Lesson lesson : lessons) {

			if (dateFormat.format(date).equals(dateFormat.format(lesson.getDate()))
					&& lesson.getGroup().getStudents().contains(student)) {

				timetable.add(lesson);

			}
		}
		return timetable;
	}

}
