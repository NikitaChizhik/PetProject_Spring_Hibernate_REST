package com.nikitachizhik91.university.dao;

import java.util.List;


public interface Crud {
	Object create(Object object);

	Object getById(int id);

	List<Object> getAll();

	Object update(int id, Object objectToInsert);

	void delete(int id);

}
