package com.nikitachizhik91.university.domain;

import java.util.List;

import com.nikitachizhik91.university.model.Department;

public interface DepartmentService {

	public abstract Department create(Department department) throws DomainException;

	public abstract Department findById(int id) throws DomainException;

	public abstract List<Department> findAll() throws DomainException;

	public abstract Department update(Department department) throws DomainException;

	public abstract void delete(int id) throws DomainException;

	public abstract void addSubject(int departmentId, int subjectId) throws DomainException;

	public abstract void addTeacher(int departmentId, int teacherId) throws DomainException;

	public abstract void deleteTeacherFromDepartment(int teacherId) throws DomainException;

	public abstract void deleteSubjectFromDepartment(int subjectId) throws DomainException;

	public List<Department> findDepartmentsWithoutFaculty() throws DomainException;
}
