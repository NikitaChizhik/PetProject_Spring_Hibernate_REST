package com.nikitachizhik91.university.dao;

import java.util.List;

import com.nikitachizhik91.university.model.Department;

public interface DepartmentDao extends Crud<Department> {

	public abstract void addTeacher(int departmentId, int teacherId) throws DaoException;

	public abstract void addSubject(int departmentId, int subjectId) throws DaoException;

	public abstract void deleteTeacherFromDepartment(int teacherId) throws DaoException;

	public abstract void deleteSubjectFromDepartment(int subjectId) throws DaoException;

	public List<Department> findDepartmentsWithoutFaculty() throws DaoException;
}
