package com.nikitachizhik91.university.domain;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DepartmentTest {

	Department department;

	@Before
	public void initializeDepartment() throws ParseException {

		department = new Department();
	}

	@Test
	public void departmentConstructorInitialize() {
		List<Teacher> expected = new ArrayList<Teacher>();

		assertEquals("Fails to initialize " + expected + "in " + department, expected, department.getTeachers());
	}

	@Test
	public void addTeacher_Teacher() {

		Teacher teacherTest = new Teacher();
		teacherTest.setId(9900);
		department.addTeacher(teacherTest);
		assertTrue("Fails to add " + teacherTest + "to the " + department,
				department.getTeachers().contains(teacherTest));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addTeacher_Null() {

		department.addTeacher(null);
	}

	@Test
	public void deleteTeacher_Teacher() {

		Teacher teacherTest = new Teacher();
		teacherTest.setId(9900);
		department.addTeacher(teacherTest);

		department.deleteTeacher(teacherTest);

		assertFalse("Fails to delete " + teacherTest + "from the " + department,
				department.getTeachers().contains(teacherTest));
	}

	// @Test
	// public void addSubject() {
	// ArrayList<Subject> subjects = new ArrayList<Subject>();
	// department.setSubjects(subjects);
	//
	// Subject subjectTest = new Subject();
	// department.addSubject(subjectTest);
	//
	// assertTrue("Subject is not added.", subjects.contains(subjectTest));
	//
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void addSubject_Null() {
	// ArrayList<Subject> subjects = new ArrayList<Subject>();
	// department.setSubjects(subjects);
	// department.addSubject(null);
	//
	// }

	// @Test
	// public void deleteSubject() {
	//
	// ArrayList<Subject> subjects = new ArrayList<Subject>();
	// department.setSubjects(subjects);
	//
	// Subject subjectTest = new Subject();
	// department.addSubject(subjectTest);
	// department.deleteTeacher(subjectTest);
	//
	// assertFalse("Subject is not deleted.",
	// department.getSubjects().contains(subjectTest));
	//
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void deleteSubject_Null() {
	// ArrayList<Subject> subjects = new ArrayList<Subject>();
	// department.setSubjects(subjects);
	// department.deleteSubject(null);
	//
	// }

}
