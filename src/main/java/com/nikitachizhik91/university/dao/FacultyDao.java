package com.nikitachizhik91.university.dao;

import com.nikitachizhik91.university.model.Faculty;

public interface FacultyDao extends Crud<Faculty> {

	public abstract void addDepartment(int facultyId, int departmentId) throws DaoException;

	public abstract void addGroup(int facultyId, int groupId) throws DaoException;

	public abstract void deleteDepartmentFromFaculty(int departmentId) throws DaoException;

	public abstract void deleteGroupFromFaculty(int groupId) throws DaoException;
}
