package com.nikitachizhik91.university.domain;

import java.util.ArrayList;
import java.util.List;

public class Department {
	private int id;
	private String name;
	private List<Teacher> teachers;
	private List<Subject> subjects;

	public Department() {
		teachers = new ArrayList<Teacher>();
		subjects = new ArrayList<Subject>();
	}

	public void addTeacher(Teacher teacher) {
		if (teacher == null) {
			throw new IllegalArgumentException();
		}
		if (teachers == null) {
			teachers = new ArrayList<Teacher>();
		}
		teachers.add(teacher);
	}

	public void deleteTeacher(Teacher teacher) {
		if (teacher == null) {
			throw new IllegalArgumentException();
		}
		if (teachers == null) {
			teachers = new ArrayList<Teacher>();
		}
		teachers.remove(teacher);
	}

	public void addSubject(Subject subject) {
		if (subject == null) {
			throw new IllegalArgumentException();
		}
		if (subjects == null) {
			subjects = new ArrayList<Subject>();
		}
		subjects.add(subject);
	}

	public void deleteSubject(Subject subject) {
		if (subject == null) {
			throw new IllegalArgumentException();
		}
		subjects.remove(subject);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subjects == null) ? 0 : subjects.hashCode());
		result = prime * result + ((teachers == null) ? 0 : teachers.hashCode());
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
		Department other = (Department) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subjects == null) {
			if (other.subjects != null)
				return false;
		} else if (!subjects.equals(other.subjects))
			return false;
		if (teachers == null) {
			if (other.teachers != null)
				return false;
		} else if (!teachers.equals(other.teachers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}

}
