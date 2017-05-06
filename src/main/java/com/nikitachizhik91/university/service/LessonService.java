package com.nikitachizhik91.university.service;

import java.util.Date;
import java.util.List;

import com.nikitachizhik91.university.model.Lesson;

public interface LessonService {

	Lesson create(Lesson lesson) throws DomainException;

	Lesson findById(int id) throws DomainException;

	List<Lesson> findAll() throws DomainException;

	Lesson update(Lesson lesson) throws DomainException;

	void delete(int id) throws DomainException;

	List<Lesson> getTeacherTimetableForDay(int teacherId, Date date) throws DomainException;

	List<Lesson> getTeacherTimetableForMonth(int teacherId, Date date) throws DomainException;

	List<Lesson> getStudentTimetableForDay(int studentId, Date date) throws DomainException;

	List<Lesson> getStudentTimetableForMonth(int studentId, Date date) throws DomainException;
}
