package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.nikitachizhik91.university.model.Faculty;
import com.nikitachizhik91.university.model.Room;
import com.nikitachizhik91.university.model.University;

public class UniversityTest {

	University university;

	@Before
	public void init() {

		university = new University();
	}

	@Test
	public void constructor_ShouldInitFacultiesWithEmptyCollection() {

		assertNotNull("Faculties is null.", university.getFaculties());
		assertTrue("Faculties is not empty.", university.getFaculties().isEmpty());
	}

	@Test
	public void constructor_ShouldInitRoomsWithEmptyCollection() {

		assertNotNull("Rooms is null.", university.getRooms());
		assertTrue("Rooms is not empty.", university.getRooms().isEmpty());
	}

	@Test
	public void addFaculty_ShouldAddFacultyOnNull() {

		Faculty facultyTest = new Faculty();
		university.setFaculties(null);
		university.addFaculty(facultyTest);

		assertTrue("Fails to add." + facultyTest + "to the " + university,
				university.getFaculties().contains(facultyTest));
	}

	@Test
	public void addFaculty_ShouldAddFaculty() {

		Faculty facultyTest = new Faculty();
		facultyTest.setId(2233);
		university.addFaculty(facultyTest);

		assertTrue("Fails to add." + facultyTest + "to the " + university,
				university.getFaculties().contains(facultyTest));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addFaculty_AddNull_MustThrowException() {

		university.addFaculty(null);
	}

	@Test
	public void deleteFaculty_ShouldDeleteFaculty() {

		Faculty facultyTest = new Faculty();
		facultyTest.setId(2233);
		university.addFaculty(facultyTest);
		university.deleteFaculty(facultyTest);

		assertFalse("Fails to delete." + facultyTest + "from the " + university,
				university.getFaculties().contains(facultyTest));
	}

	@Test
	public void deleteFaculty_ShouldDeleteFacultyOnNull() {

		university.setFaculties(null);
		university.deleteFaculty(null);
	}

	@Test
	public void addRoom_ShouldAddRoomOnNull() {

		Room roomTest = new Room();
		university.setRooms(null);
		university.addRoom(roomTest);

		assertTrue("Fails to add." + roomTest + "to the " + university, university.getRooms().contains(roomTest));
	}

	@Test
	public void addRoom_ShouldAddRoom() {

		Room roomTest = new Room();
		roomTest.setId(2233);
		university.addRoom(roomTest);

		assertTrue("Fails to add." + roomTest + "to the " + university, university.getRooms().contains(roomTest));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addRoom_AddNull_MustThrowException() {

		university.addRoom(null);
	}

	@Test
	public void deleteRoom_ShouldDeleteRoom() {

		Room roomTest = new Room();
		roomTest.setId(2233);
		university.addRoom(roomTest);
		university.deleteRoom(roomTest);

		assertFalse("Fails to delete." + roomTest + "from the " + university, university.getRooms().contains(roomTest));
	}

	@Test
	public void deleteRoom_ShouldDeleteRoomOnNull() {

		university.setRooms(null);
		university.deleteRoom(null);
	}

}
