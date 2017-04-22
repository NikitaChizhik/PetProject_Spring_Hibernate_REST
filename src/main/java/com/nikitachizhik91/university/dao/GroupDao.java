package com.nikitachizhik91.university.dao;

import java.util.List;

import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Student;

public interface GroupDao extends Crud<Group> {

	public abstract void addStudent(int studentId, int groupId) throws DaoException;

	public abstract List<Student> findStudentsByGroupId(int groupId) throws DaoException;

	public abstract List<Group> findGroupsWithoutFaculty() throws DaoException;

	public abstract void deleteAllStudentsFromGroup(int groupId) throws DaoException;

	public abstract void deleteStudentFromGroup(int studentId) throws DaoException;

}
