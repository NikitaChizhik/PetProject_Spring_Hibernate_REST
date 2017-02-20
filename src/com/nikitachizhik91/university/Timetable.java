package com.nikitachizhik91.university;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Timetable {
	private int id;
	private List<Lesson> lessons;
	private Timetable requiredTimetable;
	private Calendar requiredDate;
	private Calendar lessonDate;

	public Timetable() {
		lessons = new ArrayList<Lesson>();
	}

	public Timetable getTeachersTimetableForDay(Teacher teacher, Date date) {
		if (teacher == null || date == null) {
			throw new IllegalArgumentException();
		}

		requiredTimetable = new Timetable();
		requiredDate = Calendar.getInstance();
		requiredDate.setTime(date);

		lessonDate = Calendar.getInstance();

		for (Lesson lesson : lessons) {

			lessonDate.setTime(lesson.getDate());

			if (lessonDate.get(Calendar.YEAR) == requiredDate.get(Calendar.YEAR)
					&& lessonDate.get(Calendar.MONTH) == requiredDate.get(Calendar.MONTH)
					&& lessonDate.get(Calendar.DAY_OF_MONTH) == requiredDate.get(Calendar.DAY_OF_MONTH)
					&& lesson.getTeacher().equals(teacher)) {

				requiredTimetable.addLesson(lesson);
			}
		}

		return requiredTimetable;

	}

	public Timetable getTeachersTimetableForMonth(Teacher teacher, Date date) {
		if (teacher == null || date == null) {
			throw new IllegalArgumentException();
		}
		requiredTimetable = new Timetable();
		requiredDate = Calendar.getInstance();
		requiredDate.setTime(date);

		lessonDate = Calendar.getInstance();

		for (Lesson lesson : lessons) {
			lessonDate.setTime(lesson.getDate());

			if (lessonDate.get(Calendar.YEAR) == requiredDate.get(Calendar.YEAR)
					&& lessonDate.get(Calendar.MONTH) == requiredDate.get(Calendar.MONTH)
					&& lesson.getTeacher().equals(teacher)) {
				requiredTimetable.addLesson(lesson);
			}
		}
		return requiredTimetable;

	}

	public Timetable getStudentsTimetableForMonth(Student student, Date date) {
		if (student == null || date == null) {
			throw new IllegalArgumentException();
		}
		requiredTimetable = new Timetable();
		requiredDate = Calendar.getInstance();
		requiredDate.setTime(date);

		lessonDate = Calendar.getInstance();
		for (Lesson lesson : lessons) {
			lessonDate.setTime(lesson.getDate());

			if (lessonDate.get(Calendar.YEAR) == requiredDate.get(Calendar.YEAR)
					&& lessonDate.get(Calendar.MONTH) == requiredDate.get(Calendar.MONTH)
					&& lesson.getGroup().getStudents().contains(student)) {
				requiredTimetable.addLesson(lesson);
			}
		}
		return requiredTimetable;

	}

	public Timetable getStudentsTimetableForDay(Student student, Date date) {
		if (student == null || date == null) {
			throw new IllegalArgumentException();
		}
		requiredTimetable = new Timetable();
		requiredDate = Calendar.getInstance();
		requiredDate.setTime(date);

		lessonDate = Calendar.getInstance();
		for (Lesson lesson : lessons) {
			lessonDate.setTime(lesson.getDate());

			if (lessonDate.get(Calendar.YEAR) == requiredDate.get(Calendar.YEAR)
					&& lessonDate.get(Calendar.MONTH) == requiredDate.get(Calendar.MONTH)
					&& lessonDate.get(Calendar.DAY_OF_MONTH) == requiredDate.get(Calendar.DAY_OF_MONTH)
					&& lesson.getGroup().getStudents().contains(student)) {
				requiredTimetable.addLesson(lesson);
			}
		}
		return requiredTimetable;

	}

	public void addLesson(Lesson lesson) {
		lessons.add(lesson);
	}

	public void deleteLesson(Lesson lesson) {
		lessons.remove(lesson);
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

}
