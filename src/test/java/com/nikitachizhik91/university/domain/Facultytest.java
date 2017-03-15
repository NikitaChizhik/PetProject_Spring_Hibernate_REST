package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Faculty;
import com.nikitachizhik91.university.model.Group;

public class Facultytest {

	Faculty faculty;

	@Before
	public void init() {

		faculty = new Faculty();
	}

	@Test
	public void constructor_ShouldInitDepartmentsWithEmptyCollection() {

		assertNotNull("Departments is null.", faculty.getDepartments());
		assertTrue("Departments is not empty.", faculty.getDepartments().isEmpty());
	}

	@Test
	public void constructor_ShouldInitGroupsWithEmptyCollection() {

		assertNotNull("Groups is null.", faculty.getGroups());
		assertTrue("Groups is not empty.", faculty.getGroups().isEmpty());
	}

	@Test
	public void addDepartment_ShouldAddDepartmentOnNull() {

		Department departmentTest = new Department();
		faculty.setDepartments(null);
		faculty.addDepartment(departmentTest);

		assertTrue("Fails to add." + departmentTest + "to the " + faculty,
				faculty.getDepartments().contains(departmentTest));
	}

	@Test
	public void addDepartment_ShouldAddDepartment() {

		Department departmentTest = new Department();
		departmentTest.setId(2233);
		faculty.addDepartment(departmentTest);

		assertTrue("Fails to add." + departmentTest + "to the " + faculty,
				faculty.getDepartments().contains(departmentTest));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addDepartment_AddNull_MustThrowException() {

		faculty.addDepartment(null);
	}

	@Test
	public void deleteDepartment_ShouldDeleteDepartment() {

		Department departmentTest = new Department();
		departmentTest.setId(2233);
		faculty.addDepartment(departmentTest);
		faculty.deleteDepartment(departmentTest);

		assertFalse("Fails to delete." + departmentTest + "from the " + faculty,
				faculty.getDepartments().contains(departmentTest));
	}

	@Test
	public void deleteDepartment_ShouldDeleteDepartmentOnNull() {

		faculty.setDepartments(null);
		faculty.deleteDepartment(null);
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
	public void addGroup_AddNull_MustThrowException() {

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
	public void deleteGroup_ShouldDeleteGroupOnNull() {

		faculty.setGroups(null);
		faculty.deleteGroup(null);
	}
}
