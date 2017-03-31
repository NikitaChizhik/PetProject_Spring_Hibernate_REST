package com.nikitachizhik91.university.domain;

import java.util.List;

import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.model.Subject;

public interface SubjectManager {

	public abstract Subject create(Subject subject) throws DomainException;

	public abstract Subject findById(int id) throws DomainException;

	public abstract List<Subject> findAll() throws DomainException;

	public abstract Subject update(Subject subject) throws DomainException;

	public abstract void delete(int id) throws DomainException;

	public abstract List<Subject> findSubjectsWithoutDepartment() throws DomainException;
}
