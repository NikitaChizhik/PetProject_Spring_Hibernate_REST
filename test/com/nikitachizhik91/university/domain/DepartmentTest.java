package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

public class DepartmentTest {

	Department department;

	@Before
	public void initialize() throws ParseException {

		department = new Department();
	}

	@Test
	public void addTeacherSetNullBefore() {

		Teacher teacher = new Teacher();
		department.setTeachers(null);
		department.addTeacher(teacher);
		assertTrue("Fails to add " + teacher + "to the " + department, department.getTeachers().contains(teacher));
	}

	@Test
	public void addTeacher() {

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
	public void deleteTeacher() {
		Teacher teacher = new Teacher();
		// Teacher teacherTest = new Teacher();
		// teacherTest.setId(9900);
		// department.addTeacher(teacherTest);

		department.deleteTeacher(teacher);

		assertFalse("Fails to delete " + teacher + "from the " + department, department.getTeachers().contains(teacher));
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
