package com.nikitachizhik91.university.domain;

import java.util.List;

public interface CrudDomain<T> {
	T create(T entity) throws DomainException;

	T findById(int id) throws DomainException;

	List<T> findAll() throws DomainException;

	T update(T entity) throws DomainException;

	void delete(int id) throws DomainException;
}
