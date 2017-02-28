package com.nikitachizhik91.university.dao;

import java.util.List;

public interface Crud<T> {
	T create(T objectToCreate);

	T getById(int id);

	List<T> getAll();

	T update(int id, T objectToInsert);

	void delete(int id);

}
