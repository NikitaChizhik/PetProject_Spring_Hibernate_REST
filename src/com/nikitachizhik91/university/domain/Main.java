package com.nikitachizhik91.university.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nikitachizhik91.university.dao.impl.LessonDaoImpl;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Room;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;

public class Main {
	public static void main(String[] args) throws DaoException, ParseException {

		LessonDaoImpl lessonDaoImpl = new LessonDaoImpl();
		Lesson lesson = new Lesson();
		Date date = new SimpleDateFormat("dd-M-yyyy").parse("11-03-2017");
		lesson.setDate(date);
		Group group = new Group();
		group.setId(2);
		lesson.setGroup(group);
		lesson.setNumber(56);
		Room room = new Room();
		room.setId(1);
		room.setNumber("124bbb");
		lesson.setRoom(room);
		Subject subject = new Subject();
		subject.setId(1);
		lesson.setSubject(subject);
		Teacher teacher = new Teacher();
		teacher.setId(1);
		lesson.setTeacher(teacher);
		// Lesson create = lessonDaoImpl.create(lesson);

		Timetable timetable = new Timetable();
		timetable.addLesson(lesson);
		timetable.getTeachersTimetableForDay(teacher, date);

		// lessonDaoImpl.delete(create.getId());

	}
}
