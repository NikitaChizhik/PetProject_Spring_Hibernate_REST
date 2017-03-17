package com.nikitachizhik91.university.domain;

import java.util.List;

import com.nikitachizhik91.university.model.Student;

public interface StudentController {

	public abstract Student create(Student entity) throws DomainException;

	public abstract Student findById(int id) throws DomainException;

	public abstract List<Student> findAll() throws DomainException;

	public abstract Student update(Student entity) throws DomainException;

	public abstract void delete(int id) throws DomainException;
}
