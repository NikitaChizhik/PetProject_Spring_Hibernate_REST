package com.nikitachizhik91.university.dao;

import java.util.List;

import com.nikitachizhik91.university.model.Subject;

public interface SubjectDao extends Crud<Subject> {

	public abstract List<Subject> findSubjectsWithoutDepartment() throws DaoException;
}
