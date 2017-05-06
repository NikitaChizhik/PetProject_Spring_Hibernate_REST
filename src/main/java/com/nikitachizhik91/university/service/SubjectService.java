package com.nikitachizhik91.university.service;

import java.util.List;

import com.nikitachizhik91.university.model.Subject;

public interface SubjectService {

	Subject create(Subject subject) throws DomainException;

	Subject findById(int id) throws DomainException;

	List<Subject> findAll() throws DomainException;

	Subject update(Subject subject) throws DomainException;

	void delete(int id) throws DomainException;

	List<Subject> findSubjectsWithoutDepartment() throws DomainException;
}
