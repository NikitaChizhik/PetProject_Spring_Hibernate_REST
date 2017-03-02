package com.nikitachizhik91.university.dao;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.nikitachizhik91.university.domain.Lesson;

public class Main {
	public static void main(String[] args) throws ParseException {

		LessonDAOImpl lessonDAOImpl = new LessonDAOImpl();
		Lesson lesson = new Lesson();
		lesson.setNumber(5);
		Date DATE_TO_CHECK = (Date) new SimpleDateFormat("dd-M-yyyy").parse("16-02-2017");
		Date date = new Date(16 - 02 - 2017);

		lesson.setDate(DATE_TO_CHECK);
		System.out.println(lessonDAOImpl.create(lesson));

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
