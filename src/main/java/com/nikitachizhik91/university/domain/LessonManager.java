package com.nikitachizhik91.university.domain;

import java.util.Date;
import java.util.List;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.model.Teacher;

public interface LessonManager {

	public abstract Lesson create(Lesson lesson) throws DomainException;

	public abstract Lesson findById(int id) throws DomainException;

	public abstract List<Lesson> findAll() throws DomainException;

	public abstract Lesson update(Lesson lesson) throws DomainException;

	public abstract void delete(int id) throws DomainException;

	public abstract List<Lesson> getTeacherTimetableForDay(Teacher teacher, Date date) throws DaoException;

	public abstract List<Lesson> getTeacherTimetableForMonth(Teacher teacher, Date date) throws DaoException;

	public abstract List<Lesson> getStudentTimetableForDay(Student student, Date date) throws DaoException;

	public abstract List<Lesson> getStudentTimetableFoMonth(Student student, Date date) throws DaoException;
}
