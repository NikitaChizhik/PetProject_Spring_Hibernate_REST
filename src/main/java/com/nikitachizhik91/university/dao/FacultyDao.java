package com.nikitachizhik91.university.dao;

import java.util.List;

import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Faculty;
import com.nikitachizhik91.university.model.Group;

public interface FacultyDao extends Crud<Faculty> {

	public abstract void addDepartment(int facultyId, int departmentId) throws DaoException;

	public abstract void addGroup(int facultyId, int groupId) throws DaoException;

	public abstract List<Department> findDepartmentsByFacultyId(int facultyId) throws DaoException;

	public abstract List<Group> findGroupsByFacultyId(int facultyId) throws DaoException;

	public abstract void deleteDepartmentFromFaculty(int departmentId) throws DaoException;

	public abstract void deleteGroupFromFaculty(int groupId) throws DaoException;
}
