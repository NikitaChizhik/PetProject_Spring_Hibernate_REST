package com.nikitachizhik91.university.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nikitachizhik91.university.domain.Teacher;
import com.nikitachizhik91.university.domain.Timetable;

public class Main {
	public static void main(String[] args) throws ParseException {
		// proverku na null sdelat vezde.
		// create update vse polya vede dodelat
		// timetableImpl sdealt.

		// TimetableDAOImpl timetableDAOImpl = new TimetableDAOImpl();
		// Teacher teacher = new Teacher();
		// teacher.setId(9);
		// Date date = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2017");
		// Timetable result =
		// timetableDAOImpl.getTeachersTimetableForDay(teacher, date);
		// System.out.println(result.getLessons().get(0));

		// LessonDAOImpl lessonDAOImpl = new LessonDAOImpl();
		// Lesson lesson = new Lesson();
		// lesson.setNumber(5);
		// Date date = new SimpleDateFormat("dd-MM-yyyy
		// HH:mm").parse("01-01-2017 9:20");
		// lesson.setDate(date);
		// Group group = new Group();
		// group.setId(1);
		// lesson.setGroup(group);
		// Room room = new Room();
		// room.setId(1);
		// lesson.setRoom(room);
		// Teacher teacher = new Teacher();
		// teacher.setId(9);
		// lesson.setTeacher(teacher);
		// Subject subject = new Subject();
		// subject.setId(4);
		// lesson.setSubject(subject);
		// System.out.println(lessonDAOImpl.create(lesson));

		// TeacherDAO teacherDAO = new TeacherDAOImpl();
		// Teacher teacher = new Teacher();
		// teacher.setId(2);
		// teacher.setName("Masha Chizhik");
		// System.out.println(teacherDAO.create(teacher));
		// System.out.println(teacherDAO.findById(1));
		// System.out.println(teacherDAO.findAll());
		// System.out.println(teacherDAO.update(teacher));
		// teacherDAO.delete(4);

		// RoomDAOImpl roomDAOImpl = new RoomDAOImpl();
		// Room room = new Room();
		// room.setNumber("88");
		// System.out.println(roomDAOImpl.create(room));

		// List<Room> all = roomDAO.getAll();
		// for (Room room : all) {
		// System.out.println(room);
		// }

		// System.out.println(roomDAO.getById(2));

		// Room room = new Room();
		// room.setNumber("127");
		// System.out.println(roomDAOImpl.update(30, room));

		// roomDAO.delete(1);
		// ArrayList<Room> allRooms = roomDAO.getAllRooms();
		//
		// for (Room room : allRooms) {
		// System.out.println(room);
		// }

		// TeacherDAO teacherDAO = new TeacherDAO();
		// Subject subject = new Subject();
		// subject.setId(1);
		// teacherDAO.addTeacher(1, "Nikita Chizik Valerievich", subject);
	}
}
