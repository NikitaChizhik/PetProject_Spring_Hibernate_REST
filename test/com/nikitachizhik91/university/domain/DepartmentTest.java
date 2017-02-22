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
	List<Subject> subjects;
	List<Teacher> teachers;
	Subject subject1;
	Teacher teacher1;
	Teacher teacher2;

	@Before
	public void initialize() throws ParseException {

		department1 = new Department();
		subjects = new ArrayList<Subject>();
		teachers = new ArrayList<Teacher>();

		department1.setId(1);

		subject1 = new Subject();
		subject1.setId(1);

		subjects.add(subject1);
		Subject subject2 = new Subject();
		subject2.setId(2);
		subjects.add(subject2);
		department1.setSubjects(subjects);

		teacher1 = new Teacher();
		teacher1.setId(1);

		teachers.add(teacher1);
		teacher2 = new Teacher();
		teacher2.setId(2);

		teachers.add(teacher2);
		department1.setTeachers(teachers);

	}

	@Test
	public void addTeacher() {
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

		department1.deleteTeacher(teacher1);

		assertFalse("teacher is not deleted.", department1.getTeachers().contains(teacher1));

	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteTeacher_Null() {

		department1.deleteTeacher(null);

	}

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
