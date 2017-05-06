package com.nikitachizhik91.university.service;

import java.util.List;

import com.nikitachizhik91.university.model.Department;

public interface DepartmentService {

	Department create(Department department) throws DomainException;

	Department findById(int id) throws DomainException;

	List<Department> findAll() throws DomainException;

	Department update(Department department) throws DomainException;

	void delete(int id) throws DomainException;

	void addSubject(int departmentId, int subjectId) throws DomainException;

	void addTeacher(int departmentId, int teacherId) throws DomainException;

	void deleteTeacherFromDepartment(int teacherId, int departmentId) throws DomainException;

	void deleteSubjectFromDepartment(int subjectId, int departmentId) throws DomainException;

	List<Department> findDepartmentsWithoutFaculty() throws DomainException;
}
