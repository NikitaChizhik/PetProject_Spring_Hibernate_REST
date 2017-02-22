package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DepartmentTest {

	Department department1;

	@Before
	public void initialize() throws ParseException {

		department1 = new Department();
	}

	@Test
	public void addTeacher() {
		List<Teacher> teachers = new ArrayList<Teacher>();
		department1.setTeachers(teachers);

		Teacher teacherTest = new Teacher();
		teacherTest.setId(9900);
		department1.addTeacher(teacherTest);

		assertTrue("teacher is not added.", department1.getTeachers().contains(teacherTest));

	}

	@Test(expected = IllegalArgumentException.class)
	public void addTeacher_Null() {

		department1.addTeacher(null);

	}

	@Test
	public void deleteTeacher() {

		List<Teacher> teachers = new ArrayList<Teacher>();
		department1.setTeachers(teachers);

		Teacher teacherTest = new Teacher();
		teacherTest.setId(9900);
		department1.addTeacher(teacherTest);

		department1.deleteTeacher(teacherTest);

		assertFalse("teacher is not deleted.", department1.getTeachers().contains(teacherTest));

	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteTeacher_Null() {

		department1.deleteTeacher(null);

	}

	// subjects = new ArrayList<Subject>();
	// subject1 = new Subject();
	// subjects.add(subject1);
	// department1.setSubjects(subjects);

	// @Test
	// public void addSubject() {
	// Subject subjectTest = new Subject();
	// department1.addSubject(subjectTest);
	//
	// assertTrue("Subject is not added.",
	// departments.get(0).getSubjects().contains(subjectTest));
	//
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void addSubject_Null() {
	//
	// departments.get(0).addSubject(null);
	//
	// }
	//
	// @Test
	// public void deleteSubject() {
	//
	// departments.get(0).deleteSubject(subject1);
	//
	// assertFalse("Subject is not deleted.",
	// departments.get(0).getSubjects().contains(subject1));
	//
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void deleteSubject_Null() {
	//
	// departments.get(0).deleteSubject(null);
	//
	// }

}
