package com.nikitachizhik91.university.service;

import java.util.List;

import com.nikitachizhik91.university.model.Group;

public interface GroupService {

	Group create(Group group) throws DomainException;

	Group findById(int id) throws DomainException;

	List<Group> findAll() throws DomainException;

	Group update(Group group) throws DomainException;

	void delete(int id) throws DomainException;

	void addStudent(int studentId, int groupId) throws DomainException;

	void deleteStudentFromGroup(int studentId, int groupId) throws DomainException;

	List<Group> findGroupsWithoutFaculty() throws DomainException;

}
