package com.nikitachizhik91.university.domain.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.impl.FacultyDaoImpl;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.FacultyManager;
import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Faculty;
import com.nikitachizhik91.university.model.Group;

@Service
public class FacultyManagerImpl implements FacultyManager {

	private final static Logger log = LogManager.getLogger(FacultyManagerImpl.class.getName());

	@Autowired
	private FacultyDaoImpl facultyDaoImpl;

	@Override
	public Faculty create(Faculty faculty) throws DomainException {
		log.trace("Started create() method.");

		Faculty facultyTemp = null;
		try {
			log.trace("Creating student.");

			facultyTemp = facultyDaoImpl.create(faculty);

		} catch (DaoException e) {
			log.error("Cannot create faculty=" + faculty, e);
			throw new DomainException("Cannot create faculty=" + faculty, e);
		}
		log.trace("Finished create() method.");

		return facultyTemp;
	}

	@Override
	public Faculty findById(int facultyId) throws DomainException {
		log.trace("Started findById() method.");

		Faculty faculty = null;
		try {
			log.trace("Finding faculty by id.");

			faculty = facultyDaoImpl.findById(facultyId);

		} catch (DaoException e) {
			log.error("Cannot find faculty by id=" + facultyId, e);
			throw new DomainException("Cannot find faculty by id=" + facultyId, e);
		}
		log.trace("Finished findById() method.");

		return faculty;
	}

	@Override
	public List<Faculty> findAll() throws DomainException {
		log.trace("Started findAll() method.");

		List<Faculty> faculties = null;
		try {
			log.trace("Finding all faculties");

			faculties = facultyDaoImpl.findAll();

		} catch (DaoException e) {
			log.error("Cannot find all faculties.", e);
			throw new DomainException("Cannot find all faculties.", e);
		}
		log.trace("Finished findAll() method.");

		return faculties;
	}

	@Override
	public Faculty update(Faculty faculty) throws DomainException {
		log.trace("Started update() method.");

		Faculty facultyTemp = null;
		try {
			log.trace("Updating faculty.");

			facultyTemp = facultyDaoImpl.update(faculty);

		} catch (DaoException e) {
			log.error("Cannot update faculty=" + faculty, e);
			throw new DomainException("Cannot update faculty=" + faculty, e);
		}
		log.trace("Finished update() method.");

		return facultyTemp;
	}

	@Override
	public void delete(int facultyId) throws DomainException {
		log.trace("Started delete() method.");

		try {
			log.trace("Deleting faculty by id=" + facultyId);

			facultyDaoImpl.delete(facultyId);

		} catch (DaoException e) {
			log.error("Cannot delete faculty with id=" + facultyId, e);
			throw new DomainException("Cannot delete faculty with id=" + facultyId, e);
		}
		log.trace("Finished delete() method.");
	}

	@Override
	public void addDepartment(int facultyId, int departmentId) throws DomainException {

		log.trace("Started addDepartment() method.");

		try {
			log.trace("Adding department.");

			facultyDaoImpl.addDepartment(facultyId, departmentId);

		} catch (DaoException e) {
			log.error("Cannot add department with id=" + departmentId + " to faculty with id=" + facultyId, e);
			throw new DomainException(
					"Cannot add department with id=" + departmentId + " to faculty with id=" + facultyId, e);
		}
		log.trace("Finished addDepartment() method.");
	}

	@Override
	public void addGroup(int facultyId, int groupId) throws DomainException {

		log.trace("Started addGroup() method.");

		try {
			log.trace("Adding group.");

			facultyDaoImpl.addGroup(facultyId, groupId);

		} catch (DaoException e) {
			log.error("Cannot add group with id=" + groupId + " to faculty with id=" + facultyId, e);
			throw new DomainException("Cannot add group with id=" + groupId + " to faculty with id=" + facultyId, e);
		}
		log.trace("Finished addGroup() method.");

	}

	@Override
	public List<Department> findDepartmentsByFacultyId(int facultyId) throws DomainException {

		log.trace("Started findDepartmentsByFacultyId() method.");

		List<Department> departments = null;
		try {
			log.trace("Finding departments by faculty id.");

			departments = facultyDaoImpl.findDepartmentsByFacultyId(facultyId);

		} catch (DaoException e) {
			log.error("Cannot find departments by faculty id=" + facultyId, e);
			throw new DomainException("Cannot find departments by faculty id=" + facultyId, e);
		}
		log.trace("Finished findDepartmentsByFacultyId() method.");

		return departments;
	}

	@Override
	public List<Group> findGroupsByFacultyId(int facultyId) throws DomainException {

		log.trace("Started findGroupsByFacultyId() method.");

		List<Group> groups = null;
		try {
			log.trace("Finding groups by faculty id.");

			groups = facultyDaoImpl.findGroupsByFacultyId(facultyId);

		} catch (DaoException e) {
			log.error("Cannot find groups by faculty id=" + facultyId, e);
			throw new DomainException("Cannot find groups by faculty id=" + facultyId, e);
		}
		log.trace("Finished findGroupsByFacultyId() method.");

		return groups;
	}

	@Override
	public void deleteAllDepartmentsFromFaculty(int facultyId) throws DomainException {
	}

	@Override
	public void deleteAllGroupsFromFaculty(int facultyId) throws DomainException {
	}

	@Override
	public void deleteDepartmentFromFaculty(int departmentId) throws DomainException {

		log.trace("Started deleteDepartmentFromFaculty() method.");

		try {
			log.trace("Deleting department from faculty.");

			facultyDaoImpl.deleteDepartmentFromFaculty(departmentId);

		} catch (DaoException e) {
			log.error("Cannot delete department with id=" + departmentId + " from faculties_departments table", e);
			throw new DomainException(
					"Cannot delete department with id=" + departmentId + " from faculties_departments table", e);
		}
		log.trace("Finished deleteDepartmentFromFaculty() method.");
	}

	@Override
	public void deleteGroupFromFaculty(int groupId) throws DomainException {

		log.trace("Started deleteGroupFromFaculty() method.");

		try {
			log.trace("Deleting group from faculty.");

			facultyDaoImpl.deleteGroupFromFaculty(groupId);

		} catch (DaoException e) {
			log.error("Cannot delete department with id=" + groupId + " from faculties_groups table", e);
			throw new DomainException("Cannot delete department with id=" + groupId + " from faculties_groupss table",
					e);
		}
		log.trace("Finished deleteGroupFromFaculty() method.");
	}
}
