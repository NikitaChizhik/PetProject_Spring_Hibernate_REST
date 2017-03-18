package com.nikitachizhik91.university.domain;

import java.util.List;

import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;

public interface GroupManager {

	public abstract Group create(Group group) throws DomainException;

	public abstract Group findById(int id) throws DomainException;

	public abstract List<Group> findAll() throws DomainException;

	public abstract Group update(Group group) throws DomainException;

	public abstract void delete(int id) throws DomainException;

	public void addStudent(int studentId, int groupId) throws DomainException;

	public List<Student> findStudentsByGroupId(int groupId) throws DomainException;

	public void deleteAllStudentsFromGroup(int groupId) throws DomainException;

	public void deleteStudentFromGroup(int studentId) throws DomainException;

}
