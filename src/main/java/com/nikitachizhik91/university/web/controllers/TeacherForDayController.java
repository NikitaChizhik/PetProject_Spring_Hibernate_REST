package com.nikitachizhik91.university.web.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.LessonService;
import com.nikitachizhik91.university.domain.TeacherService;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Teacher;
import com.nikitachizhik91.university.web.WebException;

@Controller
public class TeacherForDayController {

	private final static Logger log = LogManager.getLogger(TeacherForDayController.class.getName());

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private LessonService lessonService;

	@GetMapping(value = "/teacherTimetableForDay")
	public ModelAndView showAllStudents(ModelAndView model) throws WebException {

		log.trace("Get request to find all teachers");

		List<Teacher> teachers = null;

		try {
			teachers = teacherService.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all teachers.", e);
			throw new WebException("Cannot find all teachers.", e);
		}

		model.addObject("teachers", teachers);
		model.setViewName("findTeacherTimetableForDay");

		log.trace("Found all teachers");

		return model;
	}

	@PostMapping(value = "/displayTeacherTimetableForDay")
	public ModelAndView displayStudentTimetableForDay(@RequestParam("teacherId") String teacherId,
			@RequestParam("date") String dateString) throws WebException {

		log.trace("Get request to find student timetable for day,with teacherId=" + teacherId + " and date="
				+ dateString);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date date = null;

		try {

			date = formatter.parse(dateString);
		} catch (ParseException e) {

			log.error("Date=" + date + " is wrong.", e);
			throw new WebException("Date=" + date + " is wrong.", e);
		}

		List<Lesson> lessons = null;
		List<Teacher> teachers = null;
		Teacher teacher = null;

		try {

			teacher = teacherService.findById(Integer.parseInt(teacherId));

			lessons = lessonService.getTeacherTimetableForDay(Integer.parseInt(teacherId), date);

			teachers = teacherService.findAll();

		} catch (DomainException e) {

			log.error("Cannot getTeacherTimetableForDay() for teacher with id=" + teacherId + " and Date :" + date, e);
			throw new WebException(
					"Cannot getTeacherTimetableForDay() for teacher with id=" + teacherId + " and Date :" + date, e);
		} catch (NumberFormatException e) {

			log.error("The teacher id=" + teacherId + " is wrong.", e);
			throw new WebException("The teacher id=" + teacherId + " is wrong.", e);
		}

		ModelAndView model = new ModelAndView();

		model.addObject("teachers", teachers);
		model.addObject("lessons", lessons);
		model.addObject("teacher", teacher);
		model.setViewName("findTeacherTimetableForDay");

		log.trace("Found " + lessons.size() + " lessons for teacher with id=" + teacherId + " and date=" + dateString);

		return model;
	}
}
