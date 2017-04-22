package com.nikitachizhik91.university.dao;

import java.util.List;

import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;

public interface DepartmentDao extends Crud<Department> {

	public abstract void addSubject(int departmentId, int subjectId) throws DaoException;

	public abstract void addTeacher(int departmentId, int teacherId) throws DaoException;

	public abstract List<Teacher> findTeachersByDepartmentId(int departmentId) throws DaoException;

	public abstract List<Subject> findSubjectsByDepartmentId(int departmentId) throws DaoException;

	public abstract void deleteTeacherFromDepartment(int teacherId) throws DaoException;

	public abstract void deleteSubjectFromDepartment(int subjectId) throws DaoException;

	public List<Department> findDepartmentsWithoutFaculty() throws DaoException;
}
