package com.nikitachizhik91.university.domain.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.impl.DepartmentDaoImlp;
import com.nikitachizhik91.university.domain.DepartmentManager;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;

public class SimpleDepartmentManager implements DepartmentManager {
	private final static Logger log = LogManager.getLogger(SimpleDepartmentManager.class.getName());
	private DepartmentDaoImlp departmentDaoImpl;

	public SimpleDepartmentManager() {
		departmentDaoImpl = new DepartmentDaoImlp();
	}

	@Override
	public Department create(Department department) throws DomainException {
		return null;
	}

	@Override
	public Department findById(int id) throws DomainException {
		return null;
	}

	@Override
	public List<Department> findAll() throws DomainException {
		log.trace("Started findAll() method.");

		List<Department> departments = null;
		try {
			log.trace("Finding all departments");

			departments = departmentDaoImpl.findAll();

		} catch (DaoException e) {
			log.error("Cannot find all departments.", e);
			throw new DomainException("Cannot find all departments.", e);
		}
		log.trace("Finished findAll() method.");

		return departments;
	}

	@Override
	public Department update(Department department) throws DomainException {
		return null;
	}

	@Override
	public void delete(int id) throws DomainException {
	}

	@Override
	public void addSubject(int departmentId, int subjectId) throws DomainException {
	}

	@Override
	public void addTeacher(int departmentId, int teacherId) throws DomainException {
	}

	@Override
	public List<Teacher> findTeachersByDepartmentId(int departmentId) throws DomainException {
		return null;
	}

	@Override
	public List<Subject> findSubjectsByDepartmentId(int departmentId) throws DomainException {
		return null;
	}

	@Override
	public void deleteAllTeachersFromDepartment(int departmentId) throws DomainException {
	}

	@Override
	public void deleteAllSubjectsFromDepartment(int departmentId) throws DomainException {
	}

	@Override
	public void deleteTeacherFromDepartment(int teacherId) throws DomainException {
	}

	@Override
	public void deleteSubjectFromDepartment(int subjectId) throws DomainException {
	}

}
