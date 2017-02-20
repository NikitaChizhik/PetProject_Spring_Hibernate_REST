package com.nikitachizhik91.university.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//зачем вот эта колбаса ? lesson.getGroup().getStudents().contains(student)

/**
 * @author nikita
 *
 */
public class Timetable {
	private int id;
	private List<Lesson> lessons;
	private Timetable requiredTimetable;
	private Calendar requiredDate;
	private Calendar lessonDate;

	public Timetable() {
		lessons = new ArrayList<Lesson>();
	}

	public Timetable getTeachersTimetableForDay(Teacher teacher, Date date) {
		if (teacher == null || date == null) {
			throw new IllegalArgumentException();
		}

		requiredTimetable = new Timetable();
		requiredDate = Calendar.getInstance();
		requiredDate.setTime(date);

		lessonDate = Calendar.getInstance();

		for (Lesson lesson : lessons) {

			lessonDate.setTime(lesson.getDate());

			if (lessonDate.get(Calendar.YEAR) == requiredDate.get(Calendar.YEAR)
					&& lessonDate.get(Calendar.MONTH) == requiredDate.get(Calendar.MONTH)
					&& lessonDate.get(Calendar.DAY_OF_MONTH) == requiredDate.get(Calendar.DAY_OF_MONTH)
					&& lesson.getTeacher().equals(teacher)) {

				requiredTimetable.addLesson(lesson);
			}
		}

		return requiredTimetable;

	}

	public Timetable getTeachersTimetableForMonth(Teacher teacher, Date date) {
		if (teacher == null || date == null) {
			throw new IllegalArgumentException();
		}
		requiredTimetable = new Timetable();
		requiredDate = Calendar.getInstance();
		requiredDate.setTime(date);

		lessonDate = Calendar.getInstance();

		for (Lesson lesson : lessons) {
			lessonDate.setTime(lesson.getDate());

			if (lessonDate.get(Calendar.YEAR) == requiredDate.get(Calendar.YEAR)
					&& lessonDate.get(Calendar.MONTH) == requiredDate.get(Calendar.MONTH)
					&& lesson.getTeacher().equals(teacher)) {
				requiredTimetable.addLesson(lesson);
			}
		}
		return requiredTimetable;

	}

	public Timetable getStudentsTimetableForMonth(Student student, Date date) {
		if (student == null || date == null) {
			throw new IllegalArgumentException();
		}
		requiredTimetable = new Timetable();
		requiredDate = Calendar.getInstance();
		requiredDate.setTime(date);

		lessonDate = Calendar.getInstance();
		for (Lesson lesson : lessons) {
			lessonDate.setTime(lesson.getDate());

			if (lessonDate.get(Calendar.YEAR) == requiredDate.get(Calendar.YEAR)
					&& lessonDate.get(Calendar.MONTH) == requiredDate.get(Calendar.MONTH)
					&& lesson.getGroup().getStudents().contains(student)) {
				requiredTimetable.addLesson(lesson);
			}
		}
		return requiredTimetable;

	}

	public Timetable getStudentsTimetableForDay(Student student, Date date) {
		if (student == null || date == null) {
			throw new IllegalArgumentException();
		}
		requiredTimetable = new Timetable();
		requiredDate = Calendar.getInstance();
		requiredDate.setTime(date);

		lessonDate = Calendar.getInstance();
		for (Lesson lesson : lessons) {
			lessonDate.setTime(lesson.getDate());

			if (lessonDate.get(Calendar.YEAR) == requiredDate.get(Calendar.YEAR)
					&& lessonDate.get(Calendar.MONTH) == requiredDate.get(Calendar.MONTH)
					&& lessonDate.get(Calendar.DAY_OF_MONTH) == requiredDate.get(Calendar.DAY_OF_MONTH)
					&& lesson.getGroup().getStudents().contains(student)) {
				requiredTimetable.addLesson(lesson);
			}
		}
		return requiredTimetable;

	}

	public void addLesson(Lesson lesson) {
		lessons.add(lesson);
	}

	public void deleteLesson(Lesson lesson) {
		lessons.remove(lesson);
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timetable getRequiredTimetable() {
		return requiredTimetable;
	}

	public void setRequiredTimetable(Timetable requiredTimetable) {
		this.requiredTimetable = requiredTimetable;
	}

	public Calendar getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Calendar requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Calendar getLessonDate() {
		return lessonDate;
	}

	public void setLessonDate(Calendar lessonDate) {
		this.lessonDate = lessonDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((lessonDate == null) ? 0 : lessonDate.hashCode());
		result = prime * result + ((lessons == null) ? 0 : lessons.hashCode());
		result = prime * result + ((requiredDate == null) ? 0 : requiredDate.hashCode());
		result = prime * result + ((requiredTimetable == null) ? 0 : requiredTimetable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timetable other = (Timetable) obj;
		if (id != other.id)
			return false;
		if (lessonDate == null) {
			if (other.lessonDate != null)
				return false;
		} else if (!lessonDate.equals(other.lessonDate))
			return false;
		if (lessons == null) {
			if (other.lessons != null)
				return false;
		} else if (!lessons.equals(other.lessons))
			return false;
		if (requiredDate == null) {
			if (other.requiredDate != null)
				return false;
		} else if (!requiredDate.equals(other.requiredDate))
			return false;
		if (requiredTimetable == null) {
			if (other.requiredTimetable != null)
				return false;
		} else if (!requiredTimetable.equals(other.requiredTimetable))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Timetable [id=" + id + ", lessons=" + lessons + ", requiredTimetable=" + requiredTimetable
				+ ", requiredDate=" + requiredDate + ", lessonDate=" + lessonDate + "]";
	}

}
