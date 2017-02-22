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
	public void addTeacherToNewDepartment() {

		Teacher teacher = new Teacher();
		department1.addTeacher(teacher);
		assertTrue("Fails to add " + teacher + "to the " + department1, department1.getTeachers().contains(teacher));
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
		List<Teacher> teachers = new ArrayList<Teacher>();
		department1.setTeachers(teachers);
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
		List<Teacher> teachers = new ArrayList<Teacher>();
		department1.setTeachers(teachers);
		department1.deleteTeacher(null);

	}

	// @Test
	// public void addSubject() {
	// ArrayList<Subject> subjects = new ArrayList<Subject>();
	// department1.setSubjects(subjects);
	//
	// Subject subjectTest = new Subject();
	// department1.addSubject(subjectTest);
	//
	// assertTrue("Subject is not added.", subjects.contains(subjectTest));
	//
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void addSubject_Null() {
	// ArrayList<Subject> subjects = new ArrayList<Subject>();
	// department1.setSubjects(subjects);
	// department1.addSubject(null);
	//
	// }
	//
	// @Test
	// public void deleteSubject() {
	//
	// ArrayList<Subject> subjects = new ArrayList<Subject>();
	// department1.setSubjects(subjects);
	//
	// Subject subjectTest = new Subject();
	// department1.addSubject(subjectTest);
	// department1.deleteSubject(subjectTest);
	//
	// assertFalse("Subject is not deleted.",
	// department1.getSubjects().contains(subjectTest));
	//
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void deleteSubject_Null() {
	// ArrayList<Subject> subjects = new ArrayList<Subject>();
	// department1.setSubjects(subjects);
	// department1.deleteSubject(null);
	//
	// }

}
