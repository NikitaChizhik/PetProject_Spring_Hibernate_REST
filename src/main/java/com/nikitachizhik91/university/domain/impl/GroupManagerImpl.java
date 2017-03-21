package com.nikitachizhik91.university.domain.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.GroupDao;
import com.nikitachizhik91.university.dao.impl.GroupDaoImpl;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.GroupManager;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Student;

public class GroupManagerImpl implements GroupManager {
	private final static Logger log = LogManager.getLogger(GroupManagerImpl.class.getName());
	private GroupDao groupDao;

	public GroupManagerImpl() {
		groupDao = new GroupDaoImpl();
	}

	@Override
	public Group create(Group group) throws DomainException {
		return null;
	}

	@Override
	public Group findById(int id) throws DomainException {
		log.trace("Started findById() method.");

		Group group = null;
		try {
			log.trace("Finding group by id.");

			group = groupDao.findById(id);

		} catch (DaoException e) {
			log.error("Cannot group by id.", e);
			throw new DomainException("Cannot group by id.", e);
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
		return null;
	}

	@Override
	public void delete(int id) throws DomainException {
	}

	@Override
	public void addStudent(int studentId, int groupId) throws DomainException {
	}

	@Override
	public List<Student> findStudentsByGroupId(int groupId) throws DomainException {
		return null;
	}

	@Override
	public void deleteAllStudentsFromGroup(int groupId) throws DomainException {
	}

	@Override
	public void deleteStudentFromGroup(int studentId) throws DomainException {
	}

}
