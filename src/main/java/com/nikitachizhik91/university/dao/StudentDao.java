package com.nikitachizhik91.university.dao;

import java.util.List;

import com.nikitachizhik91.university.model.Student;

public interface StudentDao extends Crud<Student> {

	public abstract List<Student> findStudentsWithoutGroup() throws DaoException;
}
