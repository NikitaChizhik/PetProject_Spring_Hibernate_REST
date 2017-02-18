package com.nikitachizhik91.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Timetable {
	private List<Lesson> lessons;

	public Timetable() {
		lessons = new ArrayList<Lesson>();
	}

	public Timetable getTeachersTimetableForDay(Teacher teacher, Date date) {

		Timetable teachersTimetableForDay = new Timetable();

		for (Lesson lesson : lessons) {
			System.out.println(lesson.getDate());
			if (lesson.getDate().getYear() == date.getYear() && lesson.getDate().getMonth() == date.getMonth()
					&& lesson.getDate().getDay() == date.getDay() && lesson.getTeacher().equals(teacher)) {
				teachersTimetableForDay.addLesson(lesson);
			}
		}

		return teachersTimetableForDay;

	}

	public Timetable getTeachersTimetableForMonth(Teacher teacher, Date date) {
		Timetable teachersTimetableForMonth = new Timetable();

		for (Lesson lesson : lessons) {
			System.out.println(lesson.getDate());
			if (lesson.getDate().getYear() == date.getYear() && lesson.getDate().getMonth() == date.getMonth()
					&& lesson.getTeacher().equals(teacher)) {
				teachersTimetableForMonth.addLesson(lesson);
			}
		}
		return teachersTimetableForMonth;

	}

	public Timetable getStudentsTimetableForDay(Student student, Date date) {
		Timetable studentsTimetableForDay = new Timetable();

		for (Lesson lesson : lessons) {
			System.out.println(lesson.getDate());
			if (lesson.getDate().getYear() == date.getYear() && lesson.getDate().getMonth() == date.getMonth()
					&& lesson.getDate().getDay() == date.getDay() && lesson.getGroup().getStudents().contains(student)) {
				studentsTimetableForDay.addLesson(lesson);
			}
		}
		return studentsTimetableForDay;

	}

	public Timetable getStudentsTimetableForMonth(Student student, Date date) {
		Timetable studentsTimetableForMonth = new Timetable();

		for (Lesson lesson : lessons) {
			System.out.println(lesson.getDate());
			if (lesson.getDate().getYear() == date.getYear() && lesson.getDate().getMonth() == date.getMonth()
					&& lesson.getGroup().getStudents().contains(student)) {
				studentsTimetableForMonth.addLesson(lesson);
			}
		}
		return studentsTimetableForMonth;

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
