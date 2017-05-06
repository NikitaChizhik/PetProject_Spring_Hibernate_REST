package com.nikitachizhik91.university.service;

import java.util.List;

import com.nikitachizhik91.university.model.Student;

public interface StudentService {

	Student create(Student student) throws DomainException;

	Student findById(int id) throws DomainException;

	List<Student> findAll() throws DomainException;

	Student update(Student student) throws DomainException;

	void delete(int id) throws DomainException;

	List<Student> findStudentsWithoutGroup() throws DomainException;
}
