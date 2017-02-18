package com.nikitachizhik91.controllers;

import java.util.List;

public class University {
	private List<Faculty> faculties;
	private List<Room> rooms;
	private Timetable timetable;

	public void addFaculty(Faculty faculty) {
		faculties.add(faculty);
	}

	public void deleteFaculty(Faculty faculty) {
		faculties.remove(faculty);
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}

	public void deleteRoom(Room room) {
		rooms.remove(room);
	}

	public List<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Timetable getTimetable() {
		return timetable;
	}

	public void setTimetable(Timetable timetable) {
		this.timetable = timetable;
	}

}
