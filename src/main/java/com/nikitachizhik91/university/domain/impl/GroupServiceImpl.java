package com.nikitachizhik91.university.domain.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.GroupDao;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.GroupService;
import com.nikitachizhik91.university.model.Group;

@Service
public class GroupServiceImpl implements GroupService {
	private final static Logger log = LogManager.getLogger(GroupServiceImpl.class.getName());

	@Autowired
	private GroupDao groupDao;

	@Override
	public Group create(Group group) throws DomainException {
		log.trace("Started create() method.");

		Group groupTemp = null;
		try {
			log.trace("Creating group.");

			groupTemp = groupDao.create(group);

		} catch (DaoException e) {
			log.error("Cannot create group=" + group, e);
			throw new DomainException("Cannot create group=" + group, e);
		}
		log.trace("Finished create() method.");

		return groupTemp;
	}

	@Override
	public Group findById(int id) throws DomainException {
		log.trace("Started findById() method.");

		Group group = null;
		try {
			log.trace("Finding group by id.");

			group = groupDao.findById(id);

		} catch (DaoException e) {
			log.error("Cannot group by id=" + id, e);
			throw new DomainException("Cannot group by id=" + id, e);
		}
		log.trace("Finished findById() method.");

		return group;
	}

	@Override
	public List<Group> findAll() throws DomainException {

		log.trace("Started findAll() method.");

		List<Group> groups = null;

		try {

			log.trace("Finding all groups");

			groups = groupDao.findAll();

		} catch (DaoException e) {

			log.error("Cannot find all groups.", e);
			throw new DomainException("Cannot find all groups.", e);
		}

		log.trace("Finished findAll() method.");

		return groups;
	}

	@Override
	public Group update(Group group) throws DomainException {

		log.trace("Started update() method.");

		Group groupTemp = null;

		try {

			log.trace("Updating group.");

			groupTemp = groupDao.update(group);

		} catch (DaoException e) {

			log.error("Cannot update group=" + group, e);
			throw new DomainException("Cannot update group=" + group, e);
		}

		log.trace("Finished update() method.");

		return groupTemp;
	}

	@Override
	public void delete(int id) throws DomainException {
		log.trace("Started delete() method.");

		try {
			log.trace("Deleting group by id=" + id);

			groupDao.delete(id);

		} catch (DaoException e) {
			log.error("Cannot delete group by id=" + id, e);
			throw new DomainException("Cannot delete group by id=" + id, e);
		}
		log.trace("Finished delete() method.");
	}

	@Override
	public void addStudent(int studentId, int groupId) throws DomainException {

		log.trace("Started addStudent() method.");

		try {
			log.trace("Adding student.");

			groupDao.addStudent(studentId, groupId);

		} catch (DaoException e) {
			log.error("Cannot add student with id=" + studentId + " to group with id=" + groupId, e);
			throw new DomainException("Cannot add student with id=" + studentId + " to group with id=" + groupId, e);
		}
		log.trace("Finished addStudent() method.");
	}

	@Override
	public void deleteStudentFromGroup(int studentId) throws DomainException {

		log.trace("Started deleteStudentFromGroup() method.");

		try {
			log.trace("Deleting student from group.");

			groupDao.deleteStudentFromGroup(studentId);

		} catch (DaoException e) {
			log.error("Cannot delete student with id=" + studentId + " from groups_students table", e);
			throw new DomainException("Cannot delete student with id=" + studentId + " from groups_students table", e);
		}
		log.trace("Finished deleteStudentFromGroup() method.");
	}

	@Override
	public List<Group> findGroupsWithoutFaculty() throws DomainException {
		log.trace("Started findGroupsWithoutFaculty() method.");

		List<Group> groups = null;
		try {
			log.trace("Finding all groups which are without faculty.");

			groups = groupDao.findGroupsWithoutFaculty();

		} catch (DaoException e) {
			log.error("Cannot find all groups which are without faculty.", e);
			throw new DomainException("Cannot find all groups which are without faculty.", e);
		}
		log.trace("Finished findGroupsWithoutFaculty() method.");

		return groups;
	}
}
