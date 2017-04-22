package com.nikitachizhik91.university.domain;

import java.util.Date;
import java.util.List;

import com.nikitachizhik91.university.model.Lesson;

public interface LessonManager {

	public abstract Lesson create(Lesson lesson) throws DomainException;

	public abstract Lesson findById(int id) throws DomainException;

	public abstract List<Lesson> findAll() throws DomainException;

	public abstract Lesson update(Lesson lesson) throws DomainException;

	public abstract void delete(int id) throws DomainException;

	public abstract List<Lesson> getTeacherTimetableForDay(int teacherId, Date date) throws DomainException;

	public abstract List<Lesson> getTeacherTimetableForMonth(int teacherId, Date date) throws DomainException;

	public abstract List<Lesson> getStudentTimetableForDay(int studentId, Date date) throws DomainException;

	public abstract List<Lesson> getStudentTimetableForMonth(int studentId, Date date) throws DomainException;
}
