package com.nikitachizhik91.university.dao;

import java.util.Date;
import java.util.List;

import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.model.Teacher;

public interface LessonDao extends Crud<Lesson> {

	public abstract List<Lesson> getTeacherTimetableForDay(Teacher teacher, Date date) throws DaoException;

	public abstract List<Lesson> getTeacherTimetableForMonth(Teacher teacher, Date date) throws DaoException;

	public abstract List<Lesson> getStudentTimetableForDay(Student student, Date date) throws DaoException;

	public abstract List<Lesson> getStudentTimetableForMonth(Student student, Date date) throws DaoException;
}
