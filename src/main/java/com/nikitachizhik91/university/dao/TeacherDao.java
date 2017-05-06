package com.nikitachizhik91.university.dao;

import java.util.List;

import com.nikitachizhik91.university.model.Teacher;

public interface TeacherDao extends Crud<Teacher> {

	List<Teacher> findTeachersWithoutDepartment() throws DaoException;
}
