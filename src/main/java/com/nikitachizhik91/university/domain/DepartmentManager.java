package com.nikitachizhik91.university.domain;

import java.util.List;

import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;

public interface DepartmentManager {

	public abstract Department create(Department department) throws DomainException;

	public abstract Department findById(int id) throws DomainException;

	public abstract List<Department> findAll() throws DomainException;

	public abstract Department update(Department department) throws DomainException;

	public abstract void delete(int id) throws DomainException;

	public abstract void addSubject(int departmentId, int subjectId) throws DomainException;

	public abstract void addTeacher(int departmentId, int teacherId) throws DomainException;

	public abstract List<Teacher> findTeachersByDepartmentId(int departmentId) throws DomainException;

	public abstract List<Subject> findSubjectsByDepartmentId(int departmentId) throws DomainException;

	public abstract void deleteTeacherFromDepartment(int teacherId) throws DomainException;

	public abstract void deleteSubjectFromDepartment(int subjectId) throws DomainException;

	public List<Department> findDepartmentsWithoutFaculty() throws DomainException;
}
