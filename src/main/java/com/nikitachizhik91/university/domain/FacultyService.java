package com.nikitachizhik91.university.domain;

import java.util.List;

import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Faculty;
import com.nikitachizhik91.university.model.Group;

public interface FacultyService {

	public abstract Faculty create(Faculty faculty) throws DomainException;

	public abstract Faculty findById(int facultyId) throws DomainException;

	public abstract List<Faculty> findAll() throws DomainException;

	public abstract Faculty update(Faculty faculty) throws DomainException;

	public abstract void delete(int facultyId) throws DomainException;

	public abstract void addDepartment(int facultyId, int departmentId) throws DomainException;

	public abstract void addGroup(int facultyId, int groupId) throws DomainException;

	public abstract List<Department> findDepartmentsByFacultyId(int facultyId) throws DomainException;

	public abstract List<Group> findGroupsByFacultyId(int facultyId) throws DomainException;

	public abstract void deleteDepartmentFromFaculty(int departmentId) throws DomainException;

	public abstract void deleteGroupFromFaculty(int groupId) throws DomainException;
}
