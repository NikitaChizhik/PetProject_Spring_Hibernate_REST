package com.nikitachizhik91.university.dao;

import com.nikitachizhik91.university.domain.Room;
import com.nikitachizhik91.university.domain.Subject;

public class Main {
	public static void main(String[] args) {

		RoomDAO roomDAO = new RoomDAO();
		Room room = new Room();
		room.setNumber("3000");
		System.out.println(roomDAO.create(room));

		// List<Room> all = roomDAO.getAll();
		// for (Room room : all) {
		// System.out.println(room);
		// }

		// System.out.println(roomDAO.getById(2));

		// Room room = new Room();
		// room.setNumber("127");
		// System.out.println(roomDAO.update(8, room));

		// roomDAO.delete(1);
		// ArrayList<Room> allRooms = roomDAO.getAllRooms();
		//
		// for (Room room : allRooms) {
		// System.out.println(room);
		// }

		TeacherDAO teacherDAO = new TeacherDAO();
		Subject subject = new Subject();
		subject.setId(1);
		// teacherDAO.addTeacher(1, "Nikita Chizik Valerievich", subject);
	}
}
