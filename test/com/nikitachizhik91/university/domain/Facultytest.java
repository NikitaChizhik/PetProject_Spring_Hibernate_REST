package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class Facultytest {

	Faculty faculty1;

	@Before
	public void initialize() throws ParseException {

		faculty1 = new Faculty();
	}

	
	
	@Test
	public void addGroup() {
		ArrayList<Group> groups = new ArrayList<Group>();
		faculty1.setGroups(groups);

		Group groupTest = new Group();
		groupTest.setId(2233);
		faculty1.addGroup(groupTest);

		assertTrue("group is not added.", faculty1.getGroups().contains(groupTest));

	}

	@Test(expected = IllegalArgumentException.class)
	public void addGroup_Null() {
		ArrayList<Group> groups = new ArrayList<Group>();
		faculty1.setGroups(groups);
		faculty1.deleteGroup(null);

	}

	@Test
	public void deleteGroup() {
		ArrayList<Group> groups = new ArrayList<Group>();
		faculty1.setGroups(groups);

		Group groupTest = new Group();
		groupTest.setId(2233);
		faculty1.deleteGroup(groupTest);

		assertFalse("group is not deleted.", faculty1.getGroups().contains(groupTest));

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

}
