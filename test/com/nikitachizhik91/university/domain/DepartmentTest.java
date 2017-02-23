package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

public class DepartmentTest {

	Department department;

	@Before
	public void init() throws ParseException {

		department = new Department();
	}

	@Test
	public void departmentConstructorInit_Teachers() {

		assertNotNull("Teachers is null.", department.getTeachers());
		assertTrue("Teachers is not empty.", department.getTeachers().isEmpty());
	}

	@Test
	public void departmentConstructorInit_SubjectsShouldBeNullandEmpty() {

		assertNotNull("Subjects is null.", department.getSubjects());
		assertTrue("Subjects is not empty.", department.getSubjects().isEmpty());
	}

	@Test
	public void addTeacher_ShouldAddTeacherOnNull() {

		Teacher teacherTest = new Teacher();
		department.setTeachers(null);
		department.addTeacher(teacherTest);
		assertTrue("Fails to add." + teacherTest + "to the " + department,
				department.getTeachers().contains(teacherTest));
	}

	@Test
	public void addTeacher_ShouldAddTeacher() {

		Teacher teacherTest = new Teacher();
		teacherTest.setId(9900);
		department.addTeacher(teacherTest);
		assertTrue("Fails to add." + teacherTest + "to the " + department,
				department.getTeachers().contains(teacherTest));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addTeacher_addNull_MustThrowException() {

		department.addTeacher(null);
	}

	@Test
	public void deleteTeacher_ShouldDeleteTeacher() {

		Teacher teacherTest = new Teacher();
		teacherTest.setId(9900);
		department.addTeacher(teacherTest);

		department.deleteTeacher(teacherTest);

		assertFalse("Fails to delete." + teacherTest + "from the " + department,
				department.getTeachers().contains(teacherTest));
	}

	@Test
	public void addTeacher_ShouldDeleteTeacherOnNull() {

		department.setTeachers(null);
		department.deleteTeacher(null);
	}

	// @Test
	// public void addSubject() {
	//
	// Subject subjectTest = new Subject();
	// subjectTest.setId(5544);
	// department.addSubject(subjectTest);
	//
	// assertTrue("Fails to add " + subjectTest + "to the " + department,
	// department.getSubjects().contains(subjectTest));
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void addSubject_Null() {
	//
	// department.addSubject(null);
	// }
	//
	// @Test
	// public void deleteSubject() {
	//
	// Subject subjectTest = new Subject();
	// department.deleteSubject(subjectTest);
	//
	// assertFalse("Fails to delete " + subjectTest + "to the " + department,
	// department.getSubjects().contains(subjectTest));
	// }

}
