package com.nikitachizhik91.university.domain;

import java.util.List;

import com.nikitachizhik91.university.model.Student;

public interface StudentService {

	public abstract Student create(Student student) throws DomainException;

	public abstract Student findById(int id) throws DomainException;

	public abstract List<Student> findAll() throws DomainException;

	public abstract Student update(Student student) throws DomainException;

	public abstract void delete(int id) throws DomainException;

	public abstract List<Student> findStudentsWithoutGroup() throws DomainException;
}
