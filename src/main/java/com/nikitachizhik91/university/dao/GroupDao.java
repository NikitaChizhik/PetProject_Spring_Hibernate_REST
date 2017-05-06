package com.nikitachizhik91.university.dao;

import java.util.List;

import com.nikitachizhik91.university.model.Group;

public interface GroupDao extends Crud<Group> {

	List<Group> findGroupsWithoutFaculty() throws DaoException;
}
