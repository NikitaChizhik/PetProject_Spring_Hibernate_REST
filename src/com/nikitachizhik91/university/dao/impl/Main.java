package com.nikitachizhik91.university.dao.impl;

import java.util.Date;

import com.nikitachizhik91.university.domain.DaoException;
import com.nikitachizhik91.university.domain.Timetable;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Teacher;

public class Main {
	public static void main(String[] args) throws DaoException {

		LessonDaoImpl lessonDaoImpl = new LessonDaoImpl();
		Lesson lesson = new Lesson();
		Lesson create = lessonDaoImpl.create(lesson);
		lessonDaoImpl.delete(create.getId());
		//
		// Timetable timetable = new Timetable();
		// timetable.addLesson(lesson);
		// Teacher teacher = new Teacher();
		// Date date = new Date();
		// timetable.getTeachersTimetableForDay(teacher, date);

	}
}
