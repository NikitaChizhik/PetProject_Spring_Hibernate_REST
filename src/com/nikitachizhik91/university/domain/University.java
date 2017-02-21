package com.nikitachizhik91.university.domain;

import java.util.List;

public class University {
	private int id;
	private List<Faculty> faculties;
	private List<Room> rooms;
	private Timetable timetable;

	public void addFaculty(Faculty faculty) {
		if (faculty == null) {
			throw new IllegalArgumentException();
		}
		faculties.add(faculty);
	}

	public void deleteFaculty(Faculty faculty) {
		if (faculty == null) {
			throw new IllegalArgumentException();
		}
		faculties.remove(faculty);
	}

	public void addRoom(Room room) {
		if (room == null) {
			throw new IllegalArgumentException();
		}
		rooms.add(room);
	}

	public void deleteRoom(Room room) {
		if (room == null) {
			throw new IllegalArgumentException();
		}
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((faculties == null) ? 0 : faculties.hashCode());
		result = prime * result + id;
		result = prime * result + ((rooms == null) ? 0 : rooms.hashCode());
		result = prime * result + ((timetable == null) ? 0 : timetable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		University other = (University) obj;
		if (faculties == null) {
			if (other.faculties != null)
				return false;
		} else if (!faculties.equals(other.faculties))
			return false;
		if (id != other.id)
			return false;
		if (rooms == null) {
			if (other.rooms != null)
				return false;
		} else if (!rooms.equals(other.rooms))
			return false;
		if (timetable == null) {
			if (other.timetable != null)
				return false;
		} else if (!timetable.equals(other.timetable))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "University [id=" + id + ", faculties=" + faculties + ", rooms=" + rooms + ", timetable=" + timetable
				+ "]";
	}

}
