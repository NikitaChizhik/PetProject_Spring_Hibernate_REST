package com.nikitachizhik91.university.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.impl.LessonDaoImpl;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Room;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;

public class Main {
	public static void main(String[] args) throws DaoException, ParseException, DomainException {

		// LessonDaoImpl lessonDaoImpl = new LessonDaoImpl();
		// Lesson lesson = new Lesson();
		// Date date = new SimpleDateFormat("dd-MM-yyyy").parse("20-08-2017");
		// lesson.setDate(date);
		// Group group = new Group();
		// group.setId(3);
		// lesson.setGroup(group);
		// lesson.setNumber(56);
		// Room room = new Room();
		// room.setId(1);
		// room.setNumber("124bbb");
		// lesson.setRoom(room);
		// Subject subject = new Subject();
		// subject.setId(1);
		// lesson.setSubject(subject);
		// Teacher teacher = new Teacher();
		// teacher.setId(2);
		// lesson.setTeacher(teacher);
		// Lesson create = lessonDaoImpl.create(lesson);
		// lessonDaoImpl.findById(1);
		// lessonDaoImpl.update(lesson);
		// lessonDaoImpl.delete(102);
		//
		// Timetable timetable = new Timetable();
		// timetable.addLesson(lesson);
		// timetable.getTeacherTimetableForDay(teacher, date);

		// Student student = new Student();
		// student.setId(2);
		// System.out.println(timetable.getStudentTimetableFoMonth(student,
		// date));
		// System.out.println("\n\n\n");
		// System.out.println(timetable.getStudentTimetableForDay(student,
		// date));

		// System.out.println(timetable.getTeacherTimetableForDay(teacher,
		// date));
		// System.out.println("\n\n\n");
		// System.out.println(timetable.getTeacherTimetableForMonth(teacher,
		// date));
		//
		// Student student = new Student();
		// student.setId(2);
		// System.out.println(timetable.getStudentTimetableForDay(student,
		// date));
		// System.out.println(timetable.getStudentTimetableFoMonth(student,
		// date));
		// lessonDaoImpl.delete(create.getId());

	}
}
