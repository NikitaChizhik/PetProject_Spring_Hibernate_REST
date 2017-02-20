package com.nikitachizhik91.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TimetableTest {

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForMonth_Null() throws ParseException {
		University university = new University();

		List<Subject> subjects = new ArrayList<Subject>();
		List<Teacher> teachers = new ArrayList<Teacher>();
		List<Group> groups = new ArrayList<Group>();
		List<Room> rooms = new ArrayList<Room>();
		Timetable timetable = new Timetable();

		Subject subject1 = new Subject();
		subject1.setId(1);
		subject1.setName("english speaking practice");
		subjects.add(subject1);
		Subject subject2 = new Subject();
		subject2.setId(2);
		subject2.setName("LISTENING");
		subjects.add(subject2);

		Teacher teacher1 = new Teacher();
		teacher1.setId(2);
		teacher1.setName("Nikita Chizhik");
		teacher1.setSubject(subject1);
		teachers.add(teacher1);
		Teacher teacher2 = new Teacher();
		teacher2.setId(2);
		teacher2.setName("Mikola brutski");
		teachers.add(teacher2);

		Group group2 = new Group();
		group2.setId(2);
		group2.setName("37");
		List<Student> students = new ArrayList<Student>();
		Student student1 = new Student();
		student1.setId(2);
		student1.setName("Sasha");
		students.add(student1);
		Student student2 = new Student();
		students.add(student2);
		group2.setStudents(students);
		groups.add(group2);

		Group group1 = new Group();
		group1.setId(1);
		group1.setName("36");
		List<Student> students2 = new ArrayList<Student>();
		Student student3 = new Student();
		student3.setId(1);
		student3.setName("David");
		students2.add(student3);
		Student student4 = new Student();
		students2.add(student4);
		group1.setStudents(students2);
		groups.add(group1);

		Room room1 = new Room();
		room1.setNumber("11");
		Room room2 = new Room();
		room2.setNumber("12");
		Room room3 = new Room();
		room3.setNumber("13");
		Room room4 = new Room();
		room4.setNumber("13b");
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		rooms.add(room4);

		university.setRooms(rooms);

		Lesson lesson1 = new Lesson();

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

		Lesson lesson2 = new Lesson();

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
		university.setTimetable(timetable);

		university.getTimetable().getStudentsTimetableForMonth(null, null).getLessons().get(0);

	}

	@Test
	public void getTeachersTimetableForMonth() throws ParseException {

		University university = new University();
		List<Faculty> faculties = new ArrayList<Faculty>();
		Faculty faculty1 = new Faculty();
		ArrayList<Department> departments = new ArrayList<Department>();
		Department department1 = new Department();
		List<Subject> subjects = new ArrayList<Subject>();
		List<Teacher> teachers = new ArrayList<Teacher>();
		List<Group> groups = new ArrayList<Group>();
		List<Room> rooms = new ArrayList<Room>();
		Timetable timetable = new Timetable();

		department1.setId(1);
		department1.setName("Phonetics");

		Subject subject1 = new Subject();
		subject1.setId(1);
		subject1.setName("english speaking practice");
		subjects.add(subject1);
		Subject subject2 = new Subject();
		subject2.setId(2);
		subject2.setName("LISTENING");
		subjects.add(subject2);
		department1.setSubjects(subjects);

		Teacher teacher1 = new Teacher();
		teacher1.setId(2);
		teacher1.setName("Nikita Chizhik");
		teacher1.setSubject(subject1);
		teachers.add(teacher1);
		Teacher teacher2 = new Teacher();
		teacher2.setId(2);
		teacher2.setName("Mikola brutski");
		teachers.add(teacher2);
		department1.setTeachers(teachers);

		departments.add(department1);
		faculty1.setDepartments(departments);

		Group group2 = new Group();
		group2.setId(2);
		group2.setName("37");
		List<Student> students = new ArrayList<Student>();
		Student student1 = new Student();
		student1.setId(2);
		student1.setName("Sasha");
		students.add(student1);
		Student student2 = new Student();
		students.add(student2);
		group2.setStudents(students);
		groups.add(group2);

		Group group1 = new Group();
		group1.setId(1);
		group1.setName("36");
		List<Student> students2 = new ArrayList<Student>();
		Student student3 = new Student();
		student3.setId(1);
		student3.setName("David");
		students2.add(student3);
		Student student4 = new Student();
		students2.add(student4);
		group1.setStudents(students2);
		groups.add(group1);

		faculty1.setGroups(groups);
		faculty1.setName("Faculty of English Language");

		faculties.add(faculty1);

		university.setFaculties(faculties);

		Room room1 = new Room();
		room1.setNumber("11");
		Room room2 = new Room();
		room2.setNumber("12");
		Room room3 = new Room();
		room3.setNumber("13");
		Room room4 = new Room();
		room4.setNumber("13b");
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		rooms.add(room4);

		university.setRooms(rooms);

		Lesson lesson1 = new Lesson();

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

		Lesson lesson2 = new Lesson();

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

		university.setTimetable(timetable);

		SimpleDateFormat sdf5 = new SimpleDateFormat("M-yyyy");
		String dateInString5 = "02-2017";
		Date date5 = sdf5.parse(dateInString5);

		List<Lesson> expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson1);
		Timetable expected = new Timetable();
		expected.setLessons(expectedLessons);

		Assert.assertEquals(expected.getLessons().get(0), university.getTimetable().a(teacher1, date5).getLessons()
				.get(0));

	}

	@Test
	public void getTeachersTimetableForDay() throws ParseException {

		University university = new University();
		List<Faculty> faculties = new ArrayList<Faculty>();
		Faculty faculty1 = new Faculty();
		ArrayList<Department> departments = new ArrayList<Department>();
		Department department1 = new Department();
		List<Subject> subjects = new ArrayList<Subject>();
		List<Teacher> teachers = new ArrayList<Teacher>();
		List<Group> groups = new ArrayList<Group>();
		List<Room> rooms = new ArrayList<Room>();
		Timetable timetable = new Timetable();

		department1.setId(1);
		department1.setName("Phonetics");

		Subject subject1 = new Subject();
		subject1.setId(1);
		subject1.setName("english speaking practice");
		subjects.add(subject1);
		Subject subject2 = new Subject();
		subject2.setId(2);
		subject2.setName("LISTENING");
		subjects.add(subject2);
		department1.setSubjects(subjects);

		Teacher teacher1 = new Teacher();
		teacher1.setId(2);
		teacher1.setName("Nikita Chizhik");
		teacher1.setSubject(subject1);
		teachers.add(teacher1);
		Teacher teacher2 = new Teacher();
		teacher2.setId(2);
		teacher2.setName("Mikola brutski");
		teachers.add(teacher2);
		department1.setTeachers(teachers);

		departments.add(department1);
		faculty1.setDepartments(departments);

		Group group2 = new Group();
		group2.setId(2);
		group2.setName("37");
		List<Student> students = new ArrayList<Student>();
		Student student1 = new Student();
		student1.setId(2);
		student1.setName("Sasha");
		students.add(student1);
		Student student2 = new Student();
		students.add(student2);
		group2.setStudents(students);
		groups.add(group2);

		Group group1 = new Group();
		group1.setId(1);
		group1.setName("36");
		List<Student> students2 = new ArrayList<Student>();
		Student student3 = new Student();
		student3.setId(1);
		student3.setName("David");
		students2.add(student3);
		Student student4 = new Student();
		students2.add(student4);
		group1.setStudents(students2);
		groups.add(group1);

		faculty1.setGroups(groups);
		faculty1.setName("Faculty of English Language");

		faculties.add(faculty1);

		university.setFaculties(faculties);

		Room room1 = new Room();
		room1.setNumber("11");
		Room room2 = new Room();
		room2.setNumber("12");
		Room room3 = new Room();
		room3.setNumber("13");
		Room room4 = new Room();
		room4.setNumber("13b");
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		rooms.add(room4);

		university.setRooms(rooms);

		Lesson lesson1 = new Lesson();

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

		Lesson lesson2 = new Lesson();

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

		university.setTimetable(timetable);

		SimpleDateFormat sdf5 = new SimpleDateFormat("dd-M-yyyy");
		String dateInString5 = "17-03-2017";
		Date date5 = sdf5.parse(dateInString5);

		List<Lesson> expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson2);
		Timetable expected = new Timetable();
		expected.setLessons(expectedLessons);

		Assert.assertEquals(expected.getLessons().get(0), university.getTimetable().a(teacher2, date5).getLessons()
				.get(0));

	}

	@Test
	public void getStudentsTimetableForMonth() throws ParseException {

		University university = new University();
		List<Faculty> faculties = new ArrayList<Faculty>();
		Faculty faculty1 = new Faculty();
		ArrayList<Department> departments = new ArrayList<Department>();
		Department department1 = new Department();
		List<Subject> subjects = new ArrayList<Subject>();
		List<Teacher> teachers = new ArrayList<Teacher>();
		List<Group> groups = new ArrayList<Group>();
		List<Room> rooms = new ArrayList<Room>();
		Timetable timetable = new Timetable();

		department1.setId(1);
		department1.setName("Phonetics");

		Subject subject1 = new Subject();
		subject1.setId(1);
		subject1.setName("english speaking practice");
		subjects.add(subject1);
		Subject subject2 = new Subject();
		subject2.setId(2);
		subject2.setName("LISTENING");
		subjects.add(subject2);
		department1.setSubjects(subjects);

		Teacher teacher1 = new Teacher();
		teacher1.setId(2);
		teacher1.setName("Nikita Chizhik");
		teacher1.setSubject(subject1);
		teachers.add(teacher1);
		Teacher teacher2 = new Teacher();
		teacher2.setId(2);
		teacher2.setName("Mikola brutski");
		teachers.add(teacher2);
		department1.setTeachers(teachers);

		departments.add(department1);
		faculty1.setDepartments(departments);

		Group group2 = new Group();
		group2.setId(2);
		group2.setName("37");
		List<Student> students = new ArrayList<Student>();
		Student student1 = new Student();
		student1.setId(2);
		student1.setName("Sasha");
		students.add(student1);
		Student student2 = new Student();
		students.add(student2);
		group2.setStudents(students);
		groups.add(group2);

		Group group1 = new Group();
		group1.setId(1);
		group1.setName("36");
		List<Student> students2 = new ArrayList<Student>();
		Student student3 = new Student();
		student3.setId(1);
		student3.setName("David");
		students2.add(student3);
		Student student4 = new Student();
		students2.add(student4);
		group1.setStudents(students2);
		groups.add(group1);

		faculty1.setGroups(groups);
		faculty1.setName("Faculty of English Language");

		faculties.add(faculty1);

		university.setFaculties(faculties);

		Room room1 = new Room();
		room1.setNumber("11");
		Room room2 = new Room();
		room2.setNumber("12");
		Room room3 = new Room();
		room3.setNumber("13");
		Room room4 = new Room();
		room4.setNumber("13b");
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		rooms.add(room4);

		university.setRooms(rooms);

		Lesson lesson1 = new Lesson();

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

		Lesson lesson2 = new Lesson();

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

		university.setTimetable(timetable);

		SimpleDateFormat sdf5 = new SimpleDateFormat("M-yyyy");
		String dateInString5 = "02-2017";
		Date date5 = sdf5.parse(dateInString5);

		List<Lesson> expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson1);
		Timetable expected = new Timetable();
		expected.setLessons(expectedLessons);

		Assert.assertEquals(expected.getLessons().get(0),
				university.getTimetable().getStudentsTimetableForMonth(student3, date5).getLessons().get(0));

	}

	@Test
	public void getStudentsTimetableForDay() throws ParseException {

		University university = new University();
		List<Faculty> faculties = new ArrayList<Faculty>();
		Faculty faculty1 = new Faculty();
		ArrayList<Department> departments = new ArrayList<Department>();
		Department department1 = new Department();
		List<Subject> subjects = new ArrayList<Subject>();
		List<Teacher> teachers = new ArrayList<Teacher>();
		List<Group> groups = new ArrayList<Group>();
		List<Room> rooms = new ArrayList<Room>();
		Timetable timetable = new Timetable();

		department1.setId(1);
		department1.setName("Phonetics");

		Subject subject1 = new Subject();
		subject1.setId(1);
		subject1.setName("english speaking practice");
		subjects.add(subject1);
		Subject subject2 = new Subject();
		subject2.setId(2);
		subject2.setName("LISTENING");
		subjects.add(subject2);
		department1.setSubjects(subjects);

		Teacher teacher1 = new Teacher();
		teacher1.setId(2);
		teacher1.setName("Nikita Chizhik");
		teacher1.setSubject(subject1);
		teachers.add(teacher1);
		Teacher teacher2 = new Teacher();
		teacher2.setId(2);
		teacher2.setName("Mikola brutski");
		teachers.add(teacher2);
		department1.setTeachers(teachers);

		departments.add(department1);
		faculty1.setDepartments(departments);

		Group group2 = new Group();
		group2.setId(2);
		group2.setName("37");
		List<Student> students = new ArrayList<Student>();
		Student student1 = new Student();
		student1.setId(2);
		student1.setName("Sasha");
		students.add(student1);
		Student student2 = new Student();
		students.add(student2);
		group2.setStudents(students);
		groups.add(group2);

		Group group1 = new Group();
		group1.setId(1);
		group1.setName("36");
		List<Student> students2 = new ArrayList<Student>();
		Student student3 = new Student();
		student3.setId(1);
		student3.setName("David");
		students2.add(student3);
		Student student4 = new Student();
		students2.add(student4);
		group1.setStudents(students2);
		groups.add(group1);

		faculty1.setGroups(groups);
		faculty1.setName("Faculty of English Language");

		faculties.add(faculty1);

		university.setFaculties(faculties);

		Room room1 = new Room();
		room1.setNumber("11");
		Room room2 = new Room();
		room2.setNumber("12");
		Room room3 = new Room();
		room3.setNumber("13");
		Room room4 = new Room();
		room4.setNumber("13b");
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		rooms.add(room4);

		university.setRooms(rooms);

		Lesson lesson1 = new Lesson();

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

		Lesson lesson2 = new Lesson();

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

		university.setTimetable(timetable);

		SimpleDateFormat sdf5 = new SimpleDateFormat("dd-M-yyyy");
		String dateInString5 = "17-03-2017";
		Date date5 = sdf5.parse(dateInString5);

		List<Lesson> expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson2);
		Timetable expected = new Timetable();
		expected.setLessons(expectedLessons);

		Assert.assertEquals(expected.getLessons().get(0),
				university.getTimetable().getStudentsTimetableForDay(student1, date5).getLessons().get(0));

	}

}
