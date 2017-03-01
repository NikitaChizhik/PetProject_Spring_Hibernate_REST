package com.nikitachizhik91.university.dao;

import java.util.List;

public interface Crud<T> {
	T create(T entity);

	T findById(int id);

	List<T> findAll();

	T update(T entity);

	void delete(int id);

}
