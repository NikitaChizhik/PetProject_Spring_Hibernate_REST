package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TimetableTest {
	Timetable timetable;

	Room room1;
	Group group1;
	Subject subject1;
	Teacher teacher1;
	Teacher teacher2;
	Lesson lesson1;
	Lesson lesson2;
	Student student1;
	Student student3;
	SimpleDateFormat dateFormat;
	String dateString;
	Date dateToCheck;
	Timetable expectedTimetable;
	List<Lesson> expectedLessons;

	@Before
	public void init() throws ParseException {
		timetable = new Timetable();

		subject1 = new Subject();
		subject1.setId(1);
		subject1.setName("english speaking practice");
		Subject subject2 = new Subject();
		subject2.setId(2);
		subject2.setName("LISTENING");

		teacher1 = new Teacher();
		teacher1.setId(1);
		teacher1.setName("Nikita Chizhik");
		teacher1.setSubject(subject1);
		teacher2 = new Teacher();
		teacher2.setId(2);
		teacher2.setName("Mikola brutski");

		Group group2 = new Group();
		group2.setId(2);
		group2.setName("37");
		Set<Student> students = new HashSet<Student>();
		student1 = new Student();
		student1.setId(2);
		student1.setName("Sasha");
		students.add(student1);
		Student student2 = new Student();
		students.add(student2);
		group2.setStudents(students);

		group1 = new Group();
		group1.setId(1);
		group1.setName("36");
		Set<Student> students2 = new HashSet<Student>();
		student3 = new Student();
		student3.setId(1);
		student3.setName("David");
		student3.setGroup(group1);
		students2.add(student3);
		Student student4 = new Student();
		students2.add(student4);
		group1.setStudents(students2);

		Room room2 = new Room();
		room2.setNumber("12");
		Room room4 = new Room();
		room4.setNumber("13b");

		lesson1 = new Lesson();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		String dateInString = "16-02-2017 10:20";
		Date date = sdf.parse(dateInString);
		lesson1.setDate(date);

		lesson1.setGroup(group1);
		lesson1.setSubject(subject1);

		lesson1.setNumber(3);
		lesson1.setRoom(room4);
		lesson1.setTeacher(teacher1);

		timetable.addLesson(lesson1);

		lesson2 = new Lesson();

		String dateInString2 = "17-03-2017 08:20";
		Date date2 = sdf.parse(dateInString2);
		lesson2.setDate(date2);
		lesson2.setGroup(group2);
		lesson2.setSubject(subject2);

		lesson2.setNumber(3);
		lesson2.setRoom(room2);
		teacher2.setSubject(subject2);
		lesson2.setTeacher(teacher2);

		timetable.addLesson(lesson2);

	}

	@Test
	public void getTeachersTimetableForMonth_General() throws ParseException {

		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);

		expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson1);

		List<Lesson> recivedLessons = timetable.getTeachersTimetableForMonth(teacher1, dateToCheck).getLessons();

		assertEquals("two timetables are not equal.", expectedLessons, recivedLessons);
	}

	@Test
	public void getTeachersTimetableForDay_General() throws ParseException {

		dateFormat = new SimpleDateFormat("dd-M-yyyy");
		dateString = "17-03-2017";
		dateToCheck = dateFormat.parse(dateString);

		expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson2);

		List<Lesson> recivedLessons = timetable.getTeachersTimetableForMonth(teacher2, dateToCheck).getLessons();

		assertEquals("two timetables are not equal.", expectedLessons, recivedLessons);
	}

	@Test
	public void getStudentsTimetableForMonth_General() throws ParseException {
		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);

		expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson1);

		List<Lesson> recivedLessons = timetable.getStudentsTimetableForMonth(student3, dateToCheck).getLessons();

		assertEquals("two timetables are not equal.", expectedLessons, recivedLessons);
	}

	@Test
	public void getStudentsTimetableForDay_General() throws ParseException {

		dateFormat = new SimpleDateFormat("dd-M-yyyy");
		dateString = "16-02-2017";
		dateToCheck = dateFormat.parse(dateString);

		expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson1);

		List<Lesson> recivedLessons = timetable.getStudentsTimetableForDay(student3, dateToCheck).getLessons();

		assertEquals("two timetables are not equal.", expectedLessons, recivedLessons);
	}

	@Test
	public void getTeachersTimetableForMonth_CheckDate() throws ParseException {

		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);

		Date recivedDate = timetable.getTeachersTimetableForMonth(teacher1, dateToCheck).getLessons().get(0).getDate();

		Calendar expected = Calendar.getInstance();
		expected.setTime(dateToCheck);

		Calendar recived = Calendar.getInstance();
		recived.setTime(recivedDate);

		assertEquals("Month is wrong.", expected.get(Calendar.MONTH), recived.get(Calendar.MONTH));
		assertEquals("Year is wrong.", expected.get(Calendar.YEAR), recived.get(Calendar.YEAR));
	}

	@Test
	public void getTeachersTimetableForMonth_CheckTeacher() throws ParseException {
		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);

		Teacher recivedTeacher = timetable.getTeachersTimetableForMonth(teacher1, dateToCheck).getLessons().get(0)
				.getTeacher();

		assertEquals("Teacher is wrong.", teacher1, recivedTeacher);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForMonth_BothArgumentsNull_ShouldThrowException() {

		timetable.getStudentsTimetableForDay(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForMonth_ArgumentsNullandDate_ShouldThrowException() throws ParseException {
		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);
		timetable.getTeachersTimetableForMonth(null, dateToCheck);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForMonth_ArgumentsTeacherandNull_ShouldThrowException() {
		timetable.getTeachersTimetableForMonth(teacher1, null);
	}

	@Test
	public void getTeachersTimetableForDay_CheckDate() throws ParseException {

		dateFormat = new SimpleDateFormat("dd-M-yyyy");
		dateString = "16-02-2017";
		dateToCheck = dateFormat.parse(dateString);

		Date recivedDate = timetable.getTeachersTimetableForDay(teacher1, dateToCheck).getLessons().get(0).getDate();

		Calendar expected = Calendar.getInstance();
		expected.setTime(dateToCheck);

		Calendar recived = Calendar.getInstance();
		recived.setTime(recivedDate);

		assertEquals("Year is wrong.", expected.get(Calendar.YEAR), recived.get(Calendar.YEAR));
		assertEquals("Month is wrong.", expected.get(Calendar.MONTH), recived.get(Calendar.MONTH));
		assertEquals("Day is wrong.", expected.get(Calendar.DAY_OF_MONTH), recived.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void getTeachersTimetableForDay_CheckTeacher() throws ParseException {
		dateFormat = new SimpleDateFormat("dd-M-yyyy");
		dateString = "17-03-2017";
		dateToCheck = dateFormat.parse(dateString);

		Teacher recivedTeacher = timetable.getTeachersTimetableForDay(teacher2, dateToCheck).getLessons().get(0)
				.getTeacher();

		assertEquals("Teacher is wrong.", teacher2, recivedTeacher);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForDay_BothArgumentsNull_ShouldThrowException() {

		timetable.getStudentsTimetableForDay(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForDay_ArgumentsNullandDate_ShouldThrowException() throws ParseException {
		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);
		timetable.getTeachersTimetableForDay(null, dateToCheck);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForDay_ArgumentsTeacherandNull_ShouldThrowException() {
		timetable.getTeachersTimetableForDay(teacher1, null);
	}

	@Test
	public void getStudentsTimetableForDay_CheckDate() throws ParseException {

		dateFormat = new SimpleDateFormat("dd-M-yyyy");
		dateString = "16-02-2017";
		dateToCheck = dateFormat.parse(dateString);

		Date recivedDate = timetable.getStudentsTimetableForDay(student3, dateToCheck).getLessons().get(0).getDate();

		Calendar expected = Calendar.getInstance();
		expected.setTime(dateToCheck);

		Calendar recived = Calendar.getInstance();
		recived.setTime(recivedDate);

		assertEquals("Year is wrong.", expected.get(Calendar.YEAR), recived.get(Calendar.YEAR));
		assertEquals("Month is wrong.", expected.get(Calendar.MONTH), recived.get(Calendar.MONTH));
		assertEquals("Day is wrong.", expected.get(Calendar.DAY_OF_MONTH), recived.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void getStudentsTimetableForDay_CheckGroup() throws ParseException {
		dateFormat = new SimpleDateFormat("dd-M-yyyy");
		dateString = "16-02-2017";
		dateToCheck = dateFormat.parse(dateString);

		Group recivedGroup = timetable.getStudentsTimetableForDay(student3, dateToCheck).getLessons().get(0).getGroup();
		Group expectedGroup = student3.getGroup();
		assertEquals("Group is wrong.", expectedGroup, recivedGroup);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getStudentsTimetableForDay_BothArgumentsNull_ShouldThrowException() {

		timetable.getStudentsTimetableForDay(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getStudentsTimetableForDay_ArgumentsNullAndDate_ShouldThrowException() throws ParseException {
		dateFormat = new SimpleDateFormat("dd-M-yyyy");
		dateString = "17-03-2017";
		dateToCheck = dateFormat.parse(dateString);
		timetable.getStudentsTimetableForDay(null, dateToCheck);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getStudentsTimetableForDay_ArgumentsStudentAndNull_ShouldThrowException() {
		timetable.getStudentsTimetableForDay(student1, null);
	}

	@Test
	public void getStudentsTimetableForMonth_CheckDate() throws ParseException {

		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);

		Date recivedDate = timetable.getStudentsTimetableForMonth(student3, dateToCheck).getLessons().get(0).getDate();

		Calendar expected = Calendar.getInstance();
		expected.setTime(dateToCheck);

		Calendar recived = Calendar.getInstance();
		recived.setTime(recivedDate);

		assertEquals("Year is wrong.", expected.get(Calendar.YEAR), recived.get(Calendar.YEAR));
		assertEquals("Month is wrong.", expected.get(Calendar.MONTH), recived.get(Calendar.MONTH));
	}

	@Test
	public void getStudentsTimetableForMonth_CheckGroup() throws ParseException {
		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);

		Group recivedGroup = timetable.getStudentsTimetableForMonth(student3, dateToCheck).getLessons().get(0)
				.getGroup();
		Group expectedGroup = student3.getGroup();
		assertEquals("Group is wrong.", expectedGroup, recivedGroup);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getStudentsTimetableForMonth_BothArgumentsNull_ShouldThrowException() {

		timetable.getStudentsTimetableForMonth(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getStudentsTimetableForMonth_ArgumentsNullAndDate_ShouldThrowException() throws ParseException {
		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "03-2017";
		dateToCheck = dateFormat.parse(dateString);
		timetable.getStudentsTimetableForMonth(null, dateToCheck);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getStudentsTimetableForMonth_ArgumentsStudentAndNull_ShouldThrowException() {
		timetable.getStudentsTimetableForMonth(student1, null);
	}
}