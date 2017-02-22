package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Facultytest {
	University university;
	List<Faculty> faculties;
	Faculty faculty1;
	ArrayList<Department> departments;
	Department department1;
	List<Group> groups;

	Group group1;

	@Before
	public void initialize() throws ParseException {

		university = new University();
		faculties = new ArrayList<Faculty>();
		faculty1 = new Faculty();
		departments = new ArrayList<Department>();
		department1 = new Department();

		groups = new ArrayList<Group>();

		department1.setId(1);

		departments.add(department1);
		faculty1.setDepartments(departments);

		Group group2 = new Group();
		group2.setId(2);

		groups.add(group2);

		group1 = new Group();

		groups.add(group1);

		faculty1.setGroups(groups);

		faculties.add(faculty1);

		university.setFaculties(faculties);

	}

	@Test
	public void addGroup() {
		Group groupTest = new Group();
		groupTest.setId(2233);
		university.getFaculties().get(0).getGroups().add(groupTest);

		assertTrue("group is not added.", university.getFaculties().get(0).getGroups().contains(groupTest));

	}

	@Test(expected = IllegalArgumentException.class)
	public void addGroup_Null() {

		university.getFaculties().get(0).addGroup(null);

	}

	@Test
	public void deleteGroup() {

		university.getFaculties().get(0).getGroups().remove(group1);

		assertFalse("group is not deleted.", university.getFaculties().get(0).getGroups().contains(group1));

	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteGroup_Null() {

		university.getFaculties().get(0).deleteGroup(null);

	}

	@Test
	public void addDepartment() {
		Department departmentTest = new Department();
		departmentTest.setId(7788);
		university.getFaculties().get(0).addDepartment(departmentTest);

		assertTrue("department is not added.",
				university.getFaculties().get(0).getDepartments().contains(departmentTest));

	}

	@Test(expected = IllegalArgumentException.class)
	public void addDepartment_Null() {

		university.getFaculties().get(0).addDepartment(null);

	}

	@Test
	public void deleteDepartment() {

		university.getFaculties().get(0).getDepartments().remove(department1);

		assertFalse("department is not deleted.",
				university.getFaculties().get(0).getDepartments().contains(department1));

	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteDepartment_Null() {

		university.getFaculties().get(0).deleteDepartment(null);

	}

}
