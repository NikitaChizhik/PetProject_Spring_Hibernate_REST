package com.nikitachizhik91.university.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Teacher;

public class Main {
	public static void main(String[] args) throws ParseException {

		Teacher teacher = new Teacher();
		teacher.setId(1);
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse("05-05-2017");
		Timetable timetable = new Timetable().getTeachersTimetableForDay(teacher, date);

		for (Lesson lesson : timetable.getLessons()) {
			System.out.println(lesson);

		}

	}
}
