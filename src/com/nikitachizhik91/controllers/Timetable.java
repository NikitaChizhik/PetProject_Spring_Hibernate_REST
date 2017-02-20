package com.nikitachizhik91.controllers;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Timetable {
	private List<Lesson> lessons;

	public Timetable() {
		lessons = new ArrayList<Lesson>();
	}

	public Timetable getTeachersTimetableForDay(Teacher teacher, Date date) {
		if (teacher == null || date == null) {
			throw new IllegalArgumentException();
		}

		Timetable teachersTimetableForDay = new Timetable();
		Calendar requiredDate = Calendar.getInstance();
		requiredDate.setTime(date);
		System.out.println(requiredDate.get(Calendar.YEAR));
		Calendar lessonDate = Calendar.getInstance();

		for (Lesson lesson : lessons) {

			lessonDate.setTime(lesson.getDate());

			if (lessonDate.get(Calendar.YEAR) == requiredDate.get(Calendar.YEAR)
					&& lessonDate.get(Calendar.MONTH) == requiredDate.get(Calendar.MONTH)
					&& lessonDate.get(Calendar.DAY_OF_MONTH) == requiredDate.get(Calendar.DAY_OF_MONTH)
					&& lesson.getTeacher().equals(teacher)) {

				teachersTimetableForDay.addLesson(lesson);
			}
		}

		return teachersTimetableForDay;

	}

	public Timetable a(Teacher teacher, Date date) {
		if (teacher == null || date == null) {
			throw new IllegalArgumentException();
		}
		Timetable teachersTimetableForMonth = new Timetable();

		for (Lesson lesson : lessons) {

			if (lesson.getDate().getYear() == date.getYear() && lesson.getDate().getMonth() == date.getMonth()
					&& lesson.getTeacher().equals(teacher)) {
				teachersTimetableForMonth.addLesson(lesson);
			}
		}
		return teachersTimetableForMonth;

	}

	public Timetable getStudentsTimetableForMonth(Student student, Date date) {
		if (student == null || date == null) {
			throw new IllegalArgumentException();
		}
		Timetable studentsTimetableForMonth = new Timetable();

		for (Lesson lesson : lessons) {

			if (lesson.getDate().getYear() == date.getYear() && lesson.getDate().getMonth() == date.getMonth()
					&& lesson.getGroup().getStudents().contains(student)) {
				studentsTimetableForMonth.addLesson(lesson);
			}
		}
		return studentsTimetableForMonth;

	}

	public Timetable getStudentsTimetableForDay(Student student, Date date) {
		if (student == null || date == null) {
			throw new IllegalArgumentException();
		}
		Timetable studentsTimetableForDay = new Timetable();

		for (Lesson lesson : lessons) {

			if (lesson.getDate().getYear() == date.getYear() && lesson.getDate().getMonth() == date.getMonth()
					&& lesson.getDate().getDay() == date.getDay() && lesson.getGroup().getStudents().contains(student)) {
				studentsTimetableForDay.addLesson(lesson);
			}
		}
		return studentsTimetableForDay;

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
