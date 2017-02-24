package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GroupTest {
	Group group;

	@Before
	public void init() {

		group = new Group();
	}

	@Test
	public void constructor_ShouldInitStudentsWithEmptyCollection() {

		assertNotNull("Students is null.", group.getStudents());
		assertTrue("Students is not empty.", group.getStudents().isEmpty());
	}

	@Test
	public void addStudent_ShouldAddStudentOnNull() {

		Student studentTest = new Student();
		group.setStudents(null);
		group.addStudent(studentTest);

		assertTrue("Fails to add." + studentTest + "to the " + group, group.getStudents().contains(studentTest));
	}

	@Test
	public void addStudent_ShouldAddStudent() {

		Student studentTest = new Student();
		studentTest.setId(2233);
		group.addStudent(studentTest);

		assertTrue("Fails to add." + studentTest + "to the " + group, group.getStudents().contains(studentTest));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addStudent_AddNull_MustThrowException() {

		group.addStudent(null);
	}

	@Test
	public void deleteStudent_ShouldDeleteStudent() {

		Student studentTest = new Student();
		studentTest.setId(2233);
		group.addStudent(studentTest);
		group.deleteStudent(studentTest);

		assertFalse("Fails to delete." + studentTest + "from the " + group, group.getStudents().contains(studentTest));
	}

	@Test
	public void deleteStudent_ShouldDeleteStudentOnNull() {

		group.setStudents(null);
		group.deleteStudent(null);
	}
}
