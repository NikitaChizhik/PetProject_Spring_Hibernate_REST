package com.nikitachizhik91.university.service;

import java.util.List;

import com.nikitachizhik91.university.model.Teacher;

public interface TeacherService {

	Teacher create(Teacher teacher) throws DomainException;

	Teacher findById(int id) throws DomainException;

	List<Teacher> findAll() throws DomainException;

	Teacher update(Teacher teacher) throws DomainException;

	void delete(int id) throws DomainException;

	List<Teacher> findTeachersWithoutDepartment() throws DomainException;
}
