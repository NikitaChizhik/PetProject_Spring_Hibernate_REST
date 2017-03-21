package com.nikitachizhik91.university.dao;

import java.util.List;

public interface Crud<T> {

	public abstract T create(T entity) throws DaoException;

	public abstract T findById(int id) throws DaoException;

	public abstract List<T> findAll() throws DaoException;

	public abstract T update(T entity) throws DaoException;

	public abstract void delete(int id) throws DaoException;
}
