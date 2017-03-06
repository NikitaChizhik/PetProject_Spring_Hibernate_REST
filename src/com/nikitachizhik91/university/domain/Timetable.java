package com.nikitachizhik91.university.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nikitachizhik91.university.dao.LessonDAOImpl;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.model.Teacher;

/**
 * @author nikita
 *
 */
public class Timetable {
	private int id;
	private List<Lesson> lessons;

	public Timetable() {
		lessons = new ArrayList<Lesson>();
	}

	public void addLesson(Lesson lesson) {
		if (lesson == null) {
			throw new IllegalArgumentException();
		}
		if (lessons == null) {
			lessons = new ArrayList<Lesson>();
		}
		lessons.add(lesson);
	}

	public void deleteLesson(Lesson lesson) {
		if (lessons != null) {
			lessons.remove(lesson);
		}
	}

	public Timetable getTeachersTimetableForDay(Teacher teacher, Date date) {
		if (teacher == null || date == null) {
			throw new IllegalArgumentException();
		}

		lessons = new LessonDAOImpl().findAll();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
		Timetable requiredTimetable = new Timetable();
		System.out.println(date + "   - from Java");
		for (Lesson lesson : lessons) {
			System.out.println(lesson.getDate() + "  - out from BD");
			if (dateFormat.format(date).equals(dateFormat.format(lesson.getDate()))
					&& lesson.getTeacher().equals(teacher)) {

				System.out.println(lesson);
				requiredTimetable.addLesson(lesson);
			}
		}

		return requiredTimetable;

	}

	// public Timetable getTeachersTimetableForMonth(Teacher teacher, Date date)
	// {
	// if (teacher == null || date == null) {
	// throw new IllegalArgumentException();
	// }
	// SimpleDateFormat dateFormat = new SimpleDateFormat("M-yyyy");
	// Timetable requiredTimetable = new Timetable();
	//
	// for (Lesson lesson : lessons) {
	//
	// if (dateFormat.format(date).equals(dateFormat.format(lesson.getDate()))
	// && lesson.getTeacher().equals(teacher)) {
	// requiredTimetable.addLesson(lesson);
	// }
	// }
	// return requiredTimetable;
	//
	// }
	//
	// public Timetable getStudentsTimetableForDay(Student student, Date date) {
	// if (student == null || date == null) {
	// throw new IllegalArgumentException();
	// }
	// Timetable requiredTimetable = new Timetable();
	//
	// SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
	//
	// for (Lesson lesson : lessons) {
	//
	// // if
	// // (dateFormat.format(date).equals(dateFormat.format(lesson.getDate()))
	// // && lesson.getGroup().equals(student.getGroup())) {
	// //
	// // requiredTimetable.addLesson(lesson);
	// //
	// // }
	// }
	// return requiredTimetable;
	//
	// }
	//
	// public Timetable getStudentsTimetableForMonth(Student student, Date date)
	// {
	// if (student == null || date == null) {
	// throw new IllegalArgumentException();
	// }
	// Timetable requiredTimetable = new Timetable();
	//
	// SimpleDateFormat dateFormat = new SimpleDateFormat("M-yyyy");
	// for (Lesson lesson : lessons) {
	//
	// // if
	// // (dateFormat.format(date).equals(dateFormat.format(lesson.getDate()))
	// // && lesson.getGroup().equals(student.getGroup())) {
	// // requiredTimetable.addLesson(lesson);
	// // }
	// }
	// return requiredTimetable;
	//
	// }

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((lessons == null) ? 0 : lessons.hashCode());
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
		if (lessons == null) {
			if (other.lessons != null)
				return false;
		} else if (!lessons.equals(other.lessons))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Timetable [id=" + id + "]";
	}

}
