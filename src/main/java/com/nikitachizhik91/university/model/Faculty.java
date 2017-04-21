package com.nikitachizhik91.university.model;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
	
	private int id;
	private String name;
	private List<Department> departments;
	private List<Group> groups;

	public Faculty() {
		departments = new ArrayList<Department>();
		groups = new ArrayList<Group>();
	}

	public void addDepartment(Department department) {
		if (department == null) {
			throw new IllegalArgumentException();
		}
		if (departments == null) {
			departments = new ArrayList<Department>();
		}
		departments.add(department);
	}

	public void deleteDepartment(Department department) {
		if (departments != null) {
			departments.remove(department);
		}
	}

	public void addGroup(Group group) {
		if (group == null) {
			throw new IllegalArgumentException();
		}
		if (groups == null) {
			groups = new ArrayList<Group>();
		}
		groups.add(group);
	}

	public void deleteGroup(Group group) {
		if (groups != null) {
			groups.remove(group);
		}
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departments == null) ? 0 : departments.hashCode());
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (departments == null) {
			if (other.departments != null)
				return false;
		} else if (!departments.equals(other.departments))
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Faculty [id=" + id + ", name=" + name + "]";
	}

}
