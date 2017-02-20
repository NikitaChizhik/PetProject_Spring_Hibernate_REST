package com.nikitachizhik91.university;

import java.util.List;



public class Department {
	private int id;
	private String name;
	private List<Teacher> teachers;
	private List<Subject> subjects;

	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
	}

	public void deleteTeacher(Teacher teacher) {
		teachers.remove(teacher);
	}

	public void addSubject(Subject subject) {
		subjects.add(subject);
	}

	public void deleteSubjectr(Subject subject) {
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



}
