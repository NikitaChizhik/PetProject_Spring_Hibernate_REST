package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TimetableTestAddDelete {

	Timetable timetable;

	@Before
	public void init() {
		timetable = new Timetable();
	}

	@Test
	public void constructor_ShouldInitLessonsWithEmptyCollection() {

		assertNotNull("Lessons is null.", timetable.getLessons());
		assertTrue("Lessons is not empty.", timetable.getLessons().isEmpty());
	}

	@Test
	public void addLesson_ShouldAddLessonOnNull() {

		Lesson lessonTest = new Lesson();
		timetable.setLessons(null);
		timetable.addLesson(lessonTest);

		assertTrue("Fails to add." + lessonTest + "to the " + timetable, timetable.getLessons().contains(lessonTest));
	}

	@Test
	public void addLesson_ShouldAddLesson() {

		Lesson lessonTest = new Lesson();
		lessonTest.setId(2233);
		timetable.addLesson(lessonTest);

		assertTrue("Fails to add." + lessonTest + "to the " + timetable, timetable.getLessons().contains(lessonTest));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addLesson_AddNull_MustThrowException() {

		timetable.addLesson(null);
	}

	@Test
	public void deleteLesson_ShouldDeleteLesson() {

		Lesson lessonTest = new Lesson();
		lessonTest.setId(2233);
		timetable.addLesson(lessonTest);
		timetable.deleteLesson(lessonTest);

		assertFalse("Fails to delete." + lessonTest + "from the " + timetable,
				timetable.getLessons().contains(lessonTest));
	}

	@Test
	public void deleteLesson_ShouldDeleteLessonOnNull() {

		timetable.setLessons(null);
		timetable.deleteLesson(null);
	}
}
