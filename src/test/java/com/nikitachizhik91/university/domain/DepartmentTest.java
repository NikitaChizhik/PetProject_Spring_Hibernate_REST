package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;

public class DepartmentTest {

	Department department;

	@Before
	public void init() {

		department = new Department();
	}

	@Test
	public void constructor_ShouldInitTeachersWithEmptyCollection() {

		assertNotNull("Teachers is null.", department.getTeachers());
		assertTrue("Teachers is not empty.", department.getTeachers().isEmpty());
	}

	@Test
	public void constructor_ShouldInitSubjectsWithEmptyCollection() {

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
	public void addTeacher_AddNull_MustThrowException() {

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
	public void deleteTeacher_ShouldDeleteTeacherOnNull() {

		department.setTeachers(null);
		department.deleteTeacher(null);
	}

	@Test
	public void addSubject_ShouldAddSubjectOnNull() {

		Subject subjectTest = new Subject();
		department.setSubjects(null);
		department.addSubject(subjectTest);
		assertTrue("Fails to add." + subjectTest + "to the " + department,
				department.getSubjects().contains(subjectTest));
	}

	@Test
	public void addSubject_ShouldAddSubject() {

		Subject subjectTest = new Subject();
		subjectTest.setId(5544);
		department.addSubject(subjectTest);

		assertTrue("Fails to add " + subjectTest + "to the " + department,
				department.getSubjects().contains(subjectTest));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addSubject_AddNull__MustThrowException() {

		department.addSubject(null);
	}

	@Test
	public void deleteSubject_ShouldDeleteSubject() {

		Subject subjectTest = new Subject();
		department.addSubject(subjectTest);
		department.deleteSubject(subjectTest);

		assertFalse("Fails to delete " + subjectTest + "to the " + department,
				department.getSubjects().contains(subjectTest));
	}

	@Test
	public void deleteSubject_ShouldDeleteSubjectOnNull() {

		department.setSubjects(null);
		department.deleteSubject(null);
	}

}
