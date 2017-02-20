package com.nikitachizhik91.university;

import java.util.List;

public class Faculty {

	private String name;
	private List<Department> departments;
	private List<Group> groups;

	
	public void addGroup(Group group) {
		groups.add(group);
	}

	public void deleteGroup(Group group) {
		groups.remove(group);
	}

	public void addDepartment(Department department) {
		departments.add(department);
	}

	public void deleteDepartment(Department department) {
		departments.remove(department);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

}
