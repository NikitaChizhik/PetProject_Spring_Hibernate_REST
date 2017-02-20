package com.nikitachizhik91.university;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TimetableTest {
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
	public void initialize() throws ParseException {

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

		teacher1 = new Teacher();
		teacher1.setId(2);
		teacher1.setName("Nikita Chizhik");
		teacher1.setSubject(subject1);
		teachers.add(teacher1);
		teacher2 = new Teacher();
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
		student1 = new Student();
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
		student3 = new Student();
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

		university.setTimetable(timetable);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForMonth_Null() throws ParseException {

		university.getTimetable().getStudentsTimetableForMonth(null, null).getLessons().get(0);

	}

	@Test
	public void getTeachersTimetableForMonth() throws ParseException {

		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);

		expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson1);
		expectedTimetable = new Timetable();
		expectedTimetable.setLessons(expectedLessons);

		Assert.assertEquals(expectedTimetable.getLessons().get(0), university.getTimetable()
				.getTeachersTimetableForMonth(teacher1, dateToCheck).getLessons().get(0));

	}

	@Test
	public void getTeachersTimetableForDay() throws ParseException {

		dateFormat = new SimpleDateFormat("dd-M-yyyy");
		dateString = "17-03-2017";
		dateToCheck = dateFormat.parse(dateString);

		expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson2);
		expectedTimetable = new Timetable();
		expectedTimetable.setLessons(expectedLessons);

		Assert.assertEquals(expectedTimetable.getLessons().get(0), university.getTimetable()
				.getTeachersTimetableForMonth(teacher2, dateToCheck).getLessons().get(0));

	}

	@Test
	public void getStudentsTimetableForMonth() throws ParseException {
		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);

		expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson1);
		expectedTimetable = new Timetable();
		expectedTimetable.setLessons(expectedLessons);

		Assert.assertEquals(expectedTimetable.getLessons().get(0), university.getTimetable()
				.getStudentsTimetableForMonth(student3, dateToCheck).getLessons().get(0));

	}

	@Test
	public void getStudentsTimetableForDay() throws ParseException {

		dateFormat = new SimpleDateFormat("dd-M-yyyy");
		dateString = "17-03-2017";
		dateToCheck = dateFormat.parse(dateString);

		expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson2);
		expectedTimetable = new Timetable();
		expectedTimetable.setLessons(expectedLessons);

		Assert.assertEquals(expectedTimetable.getLessons().get(0), university.getTimetable()
				.getStudentsTimetableForDay(student1, dateToCheck).getLessons().get(0));

	}

	@Test
	public void getTeachersTimetableForMonth_Teacher() throws ParseException {

		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);
		expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson1);
		expectedTimetable = new Timetable();
		expectedTimetable.setLessons(expectedLessons);

		Assert.assertEquals(expectedTimetable.getLessons().get(0), university.getTimetable()
				.getTeachersTimetableForMonth(teacher1, dateToCheck).getLessons().get(0));

	}

}
