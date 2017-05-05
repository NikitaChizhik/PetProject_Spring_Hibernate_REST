package com.nikitachizhik91.university.domain.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.DepartmentDao;
import com.nikitachizhik91.university.dao.SubjectDao;
import com.nikitachizhik91.university.dao.TeacherDao;
import com.nikitachizhik91.university.domain.DepartmentService;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final static Logger log = LogManager.getLogger(DepartmentServiceImpl.class.getName());

	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private TeacherDao teacherDao;

	@Override
	public Department create(Department department) throws DomainException {
		log.trace("Started create() method.");

		Department departmentTemp = null;
		try {
			log.trace("Creating student.");

			departmentTemp = departmentDao.create(department);

		} catch (DaoException e) {
			log.error("Cannot create department=" + department, e);
			throw new DomainException("Cannot create department=" + department, e);
		}
		log.trace("Finished create() method.");

		return departmentTemp;
	}

	@Override
	public Department findById(int id) throws DomainException {
		log.trace("Started findById() method.");

		Department department = null;
		try {
			log.trace("Finding department by id.");

			department = departmentDao.findById(id);

		} catch (DaoException e) {
			log.error("Cannot find department by id=" + id, e);
			throw new DomainException("Cannot find department by id=" + id, e);
		}
		log.trace("Finished findById() method.");

		return department;
	}

	@Override
	public List<Department> findAll() throws DomainException {
		log.trace("Started findAll() method.");

		List<Department> departments = null;
		try {
			log.trace("Finding all departments");

			departments = departmentDao.findAll();

		} catch (DaoException e) {
			log.error("Cannot find all departments.", e);
			throw new DomainException("Cannot find all departments.", e);
		}
		log.trace("Finished findAll() method.");

		return departments;
	}

	@Override
	public Department update(Department department) throws DomainException {
		log.trace("Started update() method.");

		Department departmentTemp = null;
		try {
			log.trace("Updating department.");

			departmentTemp = departmentDao.update(department);

		} catch (DaoException e) {
			log.error("Cannot update department=" + department, e);
			throw new DomainException("Cannot update department=" + department, e);
		}
		log.trace("Finished update() method.");

		return departmentTemp;
	}

	@Override
	public void delete(int id) throws DomainException {
		log.trace("Started delete() method.");

		try {
			log.trace("Deleting department by id=" + id);

			departmentDao.delete(id);

		} catch (DaoException e) {
			log.error("Cannot delete department with id=" + id, e);
			throw new DomainException("Cannot delete department with id=" + id, e);
		}
		log.trace("Finished delete() method.");
	}

	@Override
	public void addTeacher(int departmentId, int teacherId) throws DomainException {

		log.trace("Started addTeacher() method.");

		try {
			log.trace("Adding teacher.");

			Department department = departmentDao.findById(departmentId);
			Teacher teacher = teacherDao.findById(teacherId);
			department.addTeacher(teacher);
			departmentDao.update(department);

		} catch (DaoException e) {
			log.error("Cannot add teacher with id=" + teacherId + " to department with id=" + departmentId, e);
			throw new DomainException(
					"Cannot add teacher with id=" + teacherId + " to department with id=" + departmentId, e);
		}
		log.trace("Finished addTeacher() method.");
	}

	@Override
	public void addSubject(int departmentId, int subjectId) throws DomainException {

		log.trace("Started addSubject() method.");

		try {
			log.trace("Adding subject.");

			Department department = departmentDao.findById(departmentId);
			Subject subject = subjectDao.findById(subjectId);
			department.addSubject(subject);
			departmentDao.update(department);

		} catch (DaoException e) {
			log.error("Cannot add subject with id=" + subjectId + " to department with id=" + departmentId, e);
			throw new DomainException(
					"Cannot add subject with id=" + subjectId + " to department with id=" + departmentId, e);
		}
		log.trace("Finished addSubject() method.");
	}

	@Override
	public void deleteTeacherFromDepartment(int teacherId, int departmentId) throws DomainException {

		log.trace("Started deleteTeacherFromDepartment() method.");

		try {
			log.trace("Deleting teacher from department.");

			Department department = departmentDao.findById(departmentId);
			Teacher teacher = teacherDao.findById(teacherId);
			department.deleteTeacher(teacher);
			departmentDao.update(department);

		} catch (DaoException e) {
			log.error("Cannot delete teacher with id=" + teacherId + " from departments_teachers table", e);
			throw new DomainException("Cannot delete teacher with id=" + teacherId + " from departments_teachers table",
					e);
		}
		log.trace("Finished deleteTeacherFromDepartment() method.");
	}

	@Override
	public void deleteSubjectFromDepartment(int subjectId, int departmentId) throws DomainException {
		log.trace("Started deleteSubjectFromDepartment() method.");
		try {
			log.trace("Deleting subject from department.");

			Department department = departmentDao.findById(departmentId);
			Subject subject = subjectDao.findById(subjectId);
			department.deleteSubject(subject);
			departmentDao.update(department);

		} catch (DaoException e) {
			log.error("Cannot delete subject with id=" + subjectId + " from departments_subjects table", e);
			throw new DomainException("Cannot delete subject with id=" + subjectId + " from departments_subjects table",
					e);
		}
		log.trace("Finished deleteSubjectFromDepartment() method.");
	}

	@Override
	public List<Department> findDepartmentsWithoutFaculty() throws DomainException {
		log.trace("Started findDepartmentsWithoutFaculty() method.");

		List<Department> departments = null;
		try {
			log.trace("Finding all departments which are without faculty.");

			departments = departmentDao.findDepartmentsWithoutFaculty();

		} catch (DaoException e) {
			log.error("Cannot find all departments which are without faculty.", e);
			throw new DomainException("Cannot find all departments which are without faculty.", e);
		}
		log.trace("Finished findDepartmentsWithoutFaculty() method.");
		return departments;
	}
}
