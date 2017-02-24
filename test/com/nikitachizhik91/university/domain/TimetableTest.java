package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

public class TimetableTest {
	// University university;
	// Set<Faculty> faculties;
	// Faculty faculty1;
	// ArrayList<Department> departments;
	// Department department1;
	// Set<Subject> subjects;
	// Set<Teacher> teachers;
	// Set<Group> groups;
	// Set<Room> rooms;
	// Room room1;
	// Group group1;
	// Subject subject1;
	Timetable timetable;

	// Teacher teacher1;
	// Teacher teacher2;
	// Lesson lesson1;
	// Lesson lesson2;
	// Student student1;
	// Student student3;
	// SimpleDateFormat dateFormat;
	// String dateString;
	// Date dateToCheck;
	// Timetable expectedTimetable;
	// List<Lesson> expectedLessons;

	@Before
	public void initialize() throws ParseException {

		// university = new University();
		// faculties = new HashSet<Faculty>();
		// faculty1 = new Faculty();
		// departments = new ArrayList<Department>();
		// department1 = new Department();
		// subjects = new HashSet<Subject>();
		// teachers = new HashSet<Teacher>();
		// groups = new HashSet<Group>();
		// rooms = new HashSet<Room>();
		timetable = new Timetable();
		//
		// department1.setId(1);
		// department1.setName("Phonetics");
		//
		// subject1 = new Subject();
		// subject1.setId(1);
		// subject1.setName("english speaking practice");
		// subjects.add(subject1);
		// Subject subject2 = new Subject();
		// subject2.setId(2);
		// subject2.setName("LISTENING");
		// subjects.add(subject2);
		// department1.setSubjects(subjects);
		//
		// teacher1 = new Teacher();
		// teacher1.setId(1);
		// teacher1.setName("Nikita Chizhik");
		// teacher1.setSubject(subject1);
		// teachers.add(teacher1);
		// teacher2 = new Teacher();
		// teacher2.setId(2);
		// teacher2.setName("Mikola brutski");
		// teachers.add(teacher2);
		// department1.setTeachers(teachers);
		//
		// departments.add(department1);
		// // faculty1.setDepartments(departments);
		//
		// Group group2 = new Group();
		// group2.setId(2);
		// group2.setName("37");
		// Set<Student> students = new HashSet<Student>();
		// student1 = new Student();
		// student1.setId(2);
		// student1.setName("Sasha");
		// students.add(student1);
		// Student student2 = new Student();
		// students.add(student2);
		// group2.setStudents(students);
		// groups.add(group2);
		//
		// group1 = new Group();
		// group1.setId(1);
		// group1.setName("36");
		// Set<Student> students2 = new HashSet<Student>();
		// student3 = new Student();
		// student3.setId(1);
		// student3.setName("David");
		// student3.setGroup(group1);
		// students2.add(student3);
		// Student student4 = new Student();
		// students2.add(student4);
		// group1.setStudents(students2);
		// groups.add(group1);
		//
		// faculty1.setId(4455);
		// // faculty1.setGroups(groups);
		// faculty1.setName("Faculty of English Language");
		//
		// faculties.add(faculty1);
		//
		// university.setFaculties(faculties);
		//
		// room1 = new Room();
		// room1.setNumber("11");
		// Room room2 = new Room();
		// room2.setNumber("12");
		// Room room3 = new Room();
		// room3.setNumber("13");
		// Room room4 = new Room();
		// room4.setNumber("13b");
		// rooms.add(room1);
		// rooms.add(room2);
		// rooms.add(room3);
		// rooms.add(room4);
		//
		// university.setRooms(rooms);
		//
		// lesson1 = new Lesson();
		//
		// SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		// String dateInString = "16-02-2017 10:20";
		// Date date = sdf.parse(dateInString);
		// lesson1.setDate(date);
		//
		// lesson1.setGroup(group1);
		// lesson1.setSubject(subject1);
		//
		// lesson1.setNumber(3);
		// lesson1.setRoom(room4);
		// lesson1.setTeacher(teacher1);
		//
		// timetable.addLesson(lesson1);
		//
		// lesson2 = new Lesson();
		//
		// String dateInString2 = "17-03-2017 08:20";
		// Date date2 = sdf.parse(dateInString2);
		// lesson2.setDate(date2);
		// lesson2.setGroup(group2);
		// lesson2.setSubject(subject2);
		//
		// lesson2.setNumber(3);
		// lesson2.setRoom(room2);
		// teacher2.setSubject(subject2);
		// lesson2.setTeacher(teacher2);
		//
		// timetable.addLesson(lesson2);
		//
		// university.setTimetable(timetable);
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
	public void addLesson_addNull_MustThrowException() {

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

	// ////////////////////////////////////
	// @Test(expected = IllegalArgumentException.class)
	// public void getTeachersTimetableForMonth_NullandNull() throws
	// ParseException {
	//
	// university.getTimetable().getStudentsTimetableForMonth(null, null);
	//
	// }
	//
	// @Test
	// public void getTeachersTimetableForMonth_general() throws ParseException
	// {
	//
	// dateFormat = new SimpleDateFormat("M-yyyy");
	// dateString = "02-2017";
	// dateToCheck = dateFormat.parse(dateString);
	// expectedLessons = new ArrayList<Lesson>();
	// expectedLessons.add(lesson1);
	// expectedTimetable = new Timetable();
	// expectedTimetable.setLessons(expectedLessons);
	//
	// assertEquals("two timetables are not equal.",
	// expectedTimetable.getLessons(), university.getTimetable()
	// .getTeachersTimetableForMonth(teacher1, dateToCheck).getLessons());
	//
	// }
	//
	// @Test
	// public void getTeachersTimetableForDay_general() throws ParseException {
	//
	// dateFormat = new SimpleDateFormat("dd-M-yyyy");
	// dateString = "17-03-2017";
	// dateToCheck = dateFormat.parse(dateString);
	//
	// expectedLessons = new ArrayList<Lesson>();
	// expectedLessons.add(lesson2);
	// expectedTimetable = new Timetable();
	// expectedTimetable.setLessons(expectedLessons);
	//
	// assertEquals("two timetables are not equal.",
	// expectedTimetable.getLessons(), university.getTimetable()
	// .getTeachersTimetableForMonth(teacher2, dateToCheck).getLessons());
	//
	// }
	//
	// @Test
	// public void getStudentsTimetableForMonth_general() throws ParseException
	// {
	// dateFormat = new SimpleDateFormat("M-yyyy");
	// dateString = "02-2017";
	// dateToCheck = dateFormat.parse(dateString);
	//
	// expectedLessons = new ArrayList<Lesson>();
	// expectedLessons.add(lesson1);
	// expectedTimetable = new Timetable();
	// expectedTimetable.setLessons(expectedLessons);
	//
	// assertEquals("two timetables are not equal.",
	// expectedTimetable.getLessons(), university.getTimetable()
	// .getStudentsTimetableForMonth(student3, dateToCheck).getLessons());
	//
	// }
	//
	// @Test
	// public void getStudentsTimetableForDay_general() throws ParseException {
	//
	// dateFormat = new SimpleDateFormat("dd-M-yyyy");
	// dateString = "16-02-2017";
	// dateToCheck = dateFormat.parse(dateString);
	//
	// expectedLessons = new ArrayList<Lesson>();
	// expectedLessons.add(lesson1);
	// expectedTimetable = new Timetable();
	// expectedTimetable.setLessons(expectedLessons);
	//
	// assertEquals("two timetables are not equal.",
	// expectedTimetable.getLessons(), university.getTimetable()
	// .getStudentsTimetableForDay(student3, dateToCheck).getLessons());
	//
	// }
	//
	// @Test
	// public void getTeachersTimetableForMonth_requiredDate() throws
	// ParseException {
	//
	// dateFormat = new SimpleDateFormat("M-yyyy");
	// dateString = "02-2017";
	// dateToCheck = dateFormat.parse(dateString);
	//
	// Date recivedDate =
	// university.getTimetable().getTeachersTimetableForMonth(teacher1,
	// dateToCheck).getLessons()
	// .get(0).getDate();
	//
	// Calendar inputDate = Calendar.getInstance();
	// inputDate.setTime(dateToCheck);
	//
	// Calendar expectedDate = Calendar.getInstance();
	// inputDate.setTime(recivedDate);
	//
	// assertEquals("Month is wrong.", expectedDate.get(Calendar.MONTH),
	// inputDate.get(Calendar.MONTH));
	// assertEquals("Year is wrong.", expectedDate.get(Calendar.YEAR),
	// inputDate.get(Calendar.YEAR));
	//
	// }
	//
	// @Test
	// public void getTeachersTimetableForMonth_requiredTeacher() throws
	// ParseException {
	// dateFormat = new SimpleDateFormat("M-yyyy");
	// dateString = "02-2017";
	// dateToCheck = dateFormat.parse(dateString);
	//
	// Teacher recivedTeacher =
	// university.getTimetable().getTeachersTimetableForMonth(teacher1,
	// dateToCheck)
	// .getLessons().get(0).getTeacher();
	//
	// assertEquals("teacher is wrong.", teacher1, recivedTeacher);
	//
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void getTeachersTimetableForMonth_NullandOK() throws
	// ParseException {
	// dateFormat = new SimpleDateFormat("M-yyyy");
	// dateString = "02-2017";
	// dateToCheck = dateFormat.parse(dateString);
	// university.getTimetable().getTeachersTimetableForMonth(null,
	// dateToCheck);
	//
	// }
	//
	// @Test(expected = IllegalArgumentException.class)
	// public void getTeachersTimetableForMonth_OkandNull() {
	// university.getTimetable().getTeachersTimetableForMonth(teacher1, null);
	//
	// }

}