package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class Facultytest {

	Faculty faculty;

	@Before
	public void init() throws ParseException {

		faculty = new Faculty();
	}

	//
	// @Test(expected = IllegalArgumentException.class)
	// public void deleteGroup_Null() {
	//
	// university.getFaculties().get(0).deleteGroup(null);
	//
	// }
	//
	// @Test
	// public void addDepartment() {
	// Department departmentTest = new Department();
	// departmentTest.setId(7788);
	// university.getFaculties().get(0).addDepartment(departmentTest);
	//
	// assertTrue("department is not added.",
	// university.getFaculties().get(0).getDepartments().contains(departmentTest));
	//
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void addDepartment_Null() {
	//
	// university.getFaculties().get(0).addDepartment(null);
	//
	// }
	//
	// @Test
	// public void deleteDepartment() {
	//
	// university.getFaculties().get(0).getDepartments().remove(department1);
	//
	// assertFalse("department is not deleted.",
	// university.getFaculties().get(0).getDepartments().contains(department1));
	//
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void deleteDepartment_Null() {
	//
	// university.getFaculties().get(0).deleteDepartment(null);
	//
	// }
	@Test
	public void facultyConstructorInit_Department() {

		assertNotNull("Departments is null.", faculty.getDepartments());
		assertTrue("Departments is not empty.", faculty.getDepartments().isEmpty());
	}

	@Test
	public void facultyConstructorInit_Groups() {

		assertNotNull("Groups is null.", faculty.getGroups());
		assertTrue("Groups is not empty.", faculty.getGroups().isEmpty());
	}

	@Test
	public void addGroup_ShouldAddGroupOnNull() {

		Group groupTest = new Group();
		faculty.setGroups(null);
		faculty.addGroup(groupTest);

		assertTrue("Fails to add." + groupTest + "to the " + faculty, faculty.getGroups().contains(groupTest));
	}

	@Test
	public void addGroup_ShouldAddGroup() {

		Group groupTest = new Group();
		groupTest.setId(2233);
		faculty.addGroup(groupTest);

		assertTrue("Fails to add." + groupTest + "to the " + faculty, faculty.getGroups().contains(groupTest));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addGroup_Null() {

		faculty.addGroup(null);
	}

	@Test
	public void deleteGroup_ShouldDeleteGroup() {

		Group groupTest = new Group();
		groupTest.setId(2233);
		faculty.addGroup(groupTest);
		faculty.deleteGroup(groupTest);

		assertFalse("Fails to delete." + groupTest + "from the " + faculty, faculty.getGroups().contains(groupTest));
	}

	@Test
	public void addGroup_ShouldDeleteGroupOnNull() {

		faculty.setGroups(null);
		faculty.deleteGroup(null);
	}
}
