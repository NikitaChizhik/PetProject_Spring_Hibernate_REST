package com.nikitachizhik91.university.dao;

import java.util.List;

import com.nikitachizhik91.university.domain.DaoException;

public interface Crud<T> {
	T create(T entity) throws DaoException;

	T findById(int id);

	List<T> findAll() throws DaoException;

	T update(T entity);

	void delete(int id);

}
