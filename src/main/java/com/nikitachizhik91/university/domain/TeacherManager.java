package com.nikitachizhik91.university.domain;

import java.util.List;

import com.nikitachizhik91.university.model.Teacher;

public interface TeacherManager {

	public abstract Teacher create(Teacher teacher) throws DomainException;

	public abstract Teacher findById(int id) throws DomainException;

	public abstract List<Teacher> findAll() throws DomainException;

	public abstract Teacher update(Teacher teacher) throws DomainException;

	public abstract void delete(int id) throws DomainException;
}
