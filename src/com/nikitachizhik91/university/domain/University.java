package com.nikitachizhik91.university.domain;

import java.util.HashSet;
import java.util.Set;

public class University {
	private String name;
	private Set<Faculty> faculties;
	private Set<Room> rooms;
	private Timetable timetable;

	public University() {
		faculties = new HashSet<Faculty>();
		rooms = new HashSet<Room>();
	}

	public void addFaculty(Faculty faculty) {
		if (faculty == null) {
			throw new IllegalArgumentException();
		}
		if (faculties == null) {
			faculties = new HashSet<Faculty>();
		}
		faculties.add(faculty);
	}

	public void deleteFaculty(Faculty faculty) {
		if (faculties != null) {
			faculties.remove(faculty);
		}
	}

	public void addRoom(Room room) {
		if (room == null) {
			throw new IllegalArgumentException();
		}
		if (rooms == null) {
			rooms = new HashSet<Room>();
		}
		rooms.add(room);
	}

	public void deleteRoom(Room room) {
		if (rooms != null) {
			rooms.remove(room);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(Set<Faculty> faculties) {
		this.faculties = faculties;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		return "University [name=" + name + "]";
	}

}
