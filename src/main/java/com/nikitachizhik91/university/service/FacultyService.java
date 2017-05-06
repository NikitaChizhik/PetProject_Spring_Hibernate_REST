package com.nikitachizhik91.university.service;

import java.util.List;

import com.nikitachizhik91.university.model.Faculty;

public interface FacultyService {

	Faculty create(Faculty faculty) throws DomainException;

	Faculty findById(int facultyId) throws DomainException;

	List<Faculty> findAll() throws DomainException;

	Faculty update(Faculty faculty) throws DomainException;

	void delete(int facultyId) throws DomainException;

	void addDepartment(int facultyId, int departmentId) throws DomainException;

	void addGroup(int facultyId, int groupId) throws DomainException;

	void deleteDepartmentFromFaculty(int departmentId, int facultyId) throws DomainException;

	void deleteGroupFromFaculty(int groupId, int facultyId) throws DomainException;
}
