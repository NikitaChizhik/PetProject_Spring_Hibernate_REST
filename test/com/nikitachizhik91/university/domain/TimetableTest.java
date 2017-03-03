package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TimetableTest {
	private Timetable timetable;
	private final Date DATE_TO_CHECK = new SimpleDateFormat("dd-M-yyyy").parse("16-02-2017");
	private final Date DATE_TO_CHECK2 = new SimpleDateFormat("dd-M-yyyy").parse("17-03-2017");
	private Group group1;
	private Teacher teacher1;
	private Teacher teacher2;
	private Lesson lesson1;
	private Lesson lesson2;
	private Student student1;
	private Student student3;

	public TimetableTest() throws ParseException {

	}

	@Before
	public void init() throws ParseException {
		timetable = new Timetable();

		teacher1 = new Teacher();
		teacher1.setName("Nikita Chizhik");

		teacher2 = new Teacher();
		teacher2.setName("Mikola brutski");

		Set<Student> students1 = new HashSet<Student>();
		student1 = new Student();
		student1.setName("Sasha");
		students1.add(student1);

		group1 = new Group();

		Set<Student> students2 = new HashSet<Student>();
		student3 = new Student();
		student3.setName("David");
		// student3.setGroup(group1);
		students2.add(student3);
		group1.setStudents(students2);

		lesson1 = new Lesson();
		lesson1.setDate(DATE_TO_CHECK);
		lesson1.setGroup(group1);
		lesson1.setTeacher(teacher1);
		timetable.addLesson(lesson1);

		lesson2 = new Lesson();
		lesson2.setDate(DATE_TO_CHECK2);
		lesson2.setTeacher(teacher2);
		timetable.addLesson(lesson2);
	}

	@Test
	public void getTeachersTimetableForMonth_General() throws ParseException {

		List<Lesson> recivedLessons = timetable.getTeachersTimetableForMonth(teacher1, DATE_TO_CHECK).getLessons();
		assertTrue("Lessons are wrong", recivedLessons.contains(lesson1));
		assertTrue("Number of lessons is wrong", recivedLessons.size() == 1);
	}

	@Test
	public void getTeachersTimetableForDay_General() throws ParseException {

		List<Lesson> recivedLessons = timetable.getTeachersTimetableForMonth(teacher2, DATE_TO_CHECK2).getLessons();

		assertTrue("Lessons are wrong", recivedLessons.contains(lesson2));
		assertTrue("Number of lessons is wrong", recivedLessons.size() == 1);
	}

	@Test
	public void getStudentsTimetableForMonth_General() throws ParseException {

		List<Lesson> recivedLessons = timetable.getStudentsTimetableForMonth(student3, DATE_TO_CHECK).getLessons();

		assertTrue("Lessons are wrong", recivedLessons.contains(lesson1));
		assertTrue("Number of lessons is wrong", recivedLessons.size() == 1);
	}

	@Test
	public void getStudentsTimetableForDay_General() throws ParseException {

		List<Lesson> recivedLessons = timetable.getStudentsTimetableForDay(student3, DATE_TO_CHECK).getLessons();

		assertTrue("Lessons are wrong", recivedLessons.contains(lesson1));
		assertTrue("Number of lessons is wrong", recivedLessons.size() == 1);
	}

	@Test
	public void getTeachersTimetableForMonth_CheckMonth() throws ParseException {

		Date recivedDate = timetable.getTeachersTimetableForMonth(teacher1, DATE_TO_CHECK).getLessons().get(0)
				.getDate();

		Calendar expected = Calendar.getInstance();
		expected.setTime(DATE_TO_CHECK);

		Calendar recived = Calendar.getInstance();
		recived.setTime(recivedDate);

		assertEquals("Month is wrong.", expected.get(Calendar.MONTH), recived.get(Calendar.MONTH));
	}

	@Test
	public void getTeachersTimetableForMonth_CheckTeacher() throws ParseException {

		Teacher recivedTeacher = timetable.getTeachersTimetableForMonth(teacher1, DATE_TO_CHECK).getLessons().get(0)
				.getTeacher();

		assertEquals("Teacher is wrong.", teacher1, recivedTeacher);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForMonth_BothArgumentsNull_ShouldThrowException() {

		timetable.getStudentsTimetableForDay(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForMonth_ArgumentsNullandDate_ShouldThrowException() throws ParseException {

		timetable.getTeachersTimetableForMonth(null, DATE_TO_CHECK);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForMonth_ArgumentsTeacherandNull_ShouldThrowException() {
		timetable.getTeachersTimetableForMonth(teacher1, null);
	}

	@Test
	public void getTeachersTimetableForDay_CheckDay() throws ParseException {

		Date recivedDate = timetable.getTeachersTimetableForDay(teacher1, DATE_TO_CHECK).getLessons().get(0).getDate();

		Calendar expected = Calendar.getInstance();
		expected.setTime(DATE_TO_CHECK);

		Calendar recived = Calendar.getInstance();
		recived.setTime(recivedDate);

		assertEquals("Day is wrong.", expected.get(Calendar.DAY_OF_MONTH), recived.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void getTeachersTimetableForDay_CheckTeacher() throws ParseException {

		Teacher recivedTeacher = timetable.getTeachersTimetableForDay(teacher2, DATE_TO_CHECK2).getLessons().get(0)
				.getTeacher();

		assertEquals("Teacher is wrong.", teacher2, recivedTeacher);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForDay_BothArgumentsNull_ShouldThrowException() {

		timetable.getStudentsTimetableForDay(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForDay_ArgumentsNullandDate_ShouldThrowException() throws ParseException {

		timetable.getTeachersTimetableForDay(null, DATE_TO_CHECK);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForDay_ArgumentsTeacherandNull_ShouldThrowException() {
		timetable.getTeachersTimetableForDay(teacher1, null);
	}

	@Test
	public void getStudentsTimetableForDay_CheckDay() throws ParseException {

		Date recivedDate = timetable.getStudentsTimetableForDay(student3, DATE_TO_CHECK).getLessons().get(0).getDate();

		Calendar expected = Calendar.getInstance();
		expected.setTime(DATE_TO_CHECK);

		Calendar recived = Calendar.getInstance();
		recived.setTime(recivedDate);

		assertEquals("Day is wrong.", expected.get(Calendar.DAY_OF_MONTH), recived.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void getStudentsTimetableForDay_CheckGroup() throws ParseException {

		Group recivedGroup = timetable.getStudentsTimetableForDay(student3, DATE_TO_CHECK).getLessons().get(0)
				.getGroup();
		// Group expectedGroup = student3.getGroup();
		// assertEquals("Group is wrong.", expectedGroup, recivedGroup);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getStudentsTimetableForDay_BothArgumentsNull_ShouldThrowException() {

		timetable.getStudentsTimetableForDay(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getStudentsTimetableForDay_ArgumentsNullAndDate_ShouldThrowException() throws ParseException {

		timetable.getStudentsTimetableForDay(null, DATE_TO_CHECK2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getStudentsTimetableForDay_ArgumentsStudentAndNull_ShouldThrowException() {
		timetable.getStudentsTimetableForDay(student1, null);
	}

	@Test
	public void getStudentsTimetableForMonth_CheckMonth() throws ParseException {

		Date recivedDate = timetable.getStudentsTimetableForMonth(student3, DATE_TO_CHECK).getLessons().get(0)
				.getDate();

		Calendar expected = Calendar.getInstance();
		expected.setTime(DATE_TO_CHECK);

		Calendar recived = Calendar.getInstance();
		recived.setTime(recivedDate);

		assertEquals("Month is wrong.", expected.get(Calendar.MONTH), recived.get(Calendar.MONTH));
	}

	@Test
	public void getStudentsTimetableForMonth_CheckGroup() throws ParseException {

		Group recivedGroup = timetable.getStudentsTimetableForMonth(student3, DATE_TO_CHECK).getLessons().get(0)
				.getGroup();
		// Group expectedGroup = student3.getGroup();
		// assertEquals("Group is wrong.", expectedGroup, recivedGroup);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getStudentsTimetableForMonth_BothArgumentsNull_ShouldThrowException() {

		timetable.getStudentsTimetableForMonth(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getStudentsTimetableForMonth_ArgumentsNullAndDate_ShouldThrowException() throws ParseException {

		timetable.getStudentsTimetableForMonth(null, DATE_TO_CHECK2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getStudentsTimetableForMonth_ArgumentsStudentAndNull_ShouldThrowException() {
		timetable.getStudentsTimetableForMonth(student1, null);
	}

	@Test
	public void constructor_ShouldInitLessonsWithEmptyCollection() {
		timetable = new Timetable();
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