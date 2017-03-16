package com.nikitachizhik91.university.domain;

import java.util.List;

import com.nikitachizhik91.university.dao.Crud;
import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.StudentDao;
import com.nikitachizhik91.university.dao.impl.StudentDaoImpl;
import com.nikitachizhik91.university.model.Student;

public class StudentDomain implements Crud<Student> {

	private StudentDao studentDao;

	public StudentDomain() {
		studentDao = new StudentDaoImpl();
	}

	@Override
	public Student create(Student entity) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findById(int id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findAll() throws DaoException {

		List<Student> students = studentDao.findAll();

		return students;
	}

	@Override
	public Student update(Student entity) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) throws DaoException {
		// TODO Auto-generated method stub

	}

}
