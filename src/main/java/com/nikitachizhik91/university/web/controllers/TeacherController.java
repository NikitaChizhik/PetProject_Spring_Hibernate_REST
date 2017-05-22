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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;
import com.nikitachizhik91.university.service.DomainException;
import com.nikitachizhik91.university.service.LessonService;
import com.nikitachizhik91.university.service.SubjectService;
import com.nikitachizhik91.university.service.TeacherService;
import com.nikitachizhik91.university.web.WebException;

@Controller
public class TeacherController {

	private final static Logger log = LogManager.getLogger(TeacherController.class.getName());

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private SubjectService subjectManager;

	@GetMapping(value = "/teachers")
	public ModelAndView findAll(ModelAndView model) throws WebException {

		log.trace("Get request to find all teachers");

		List<Teacher> teachers = null;
		List<Subject> subjects = null;

		try {

			teachers = teacherService.findAll();

			subjects = subjectManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all teachers.", e);
			throw new WebException("Cannot find all teachers.", e);
		}

		model.addObject("teachers", teachers);
		model.addObject("subjects", subjects);
		model.setViewName("teachers");

		log.trace("Found all teachers");

		return model;
	}

	@GetMapping(value = "/teacher/{id}")
	public ModelAndView findById(ModelAndView model, @PathVariable("id") int teacherId) throws WebException {

		log.trace("Get request to find teacher by id=" + teacherId);

		Teacher teacher = null;
		List<Subject> subjects = null;

		try {

			teacher = teacherService.findById(teacherId);

			subjects = subjectManager.findAll();

		} catch (NumberFormatException e) {

			log.error("The id=" + teacherId + " is wrong.", e);
			throw new WebException("The id=" + teacherId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot find teacher by id=" + teacherId, e);
			throw new WebException("Cannot find teacher by id=" + teacherId, e);
		}

		model.addObject("teacher", teacher);
		model.addObject("subjects", subjects);
		model.setViewName("teacher");

		log.trace("Found teacher by id=" + teacherId);

		return model;
	}

	@PostMapping(value = "/teacher/create")
	public String create(@ModelAttribute("teacher") Teacher teacher) throws WebException {

		log.trace("Post request to create teacher with name=" + teacher.getName() + " subjectId="
				+ teacher.getSubject().getId());

		try {

			teacher = teacherService.create(teacher);

		} catch (DomainException e) {

			log.error("Cannot create teacher=" + teacher, e);
			throw new WebException("Cannot create teacher=" + teacher, e);
		}

		log.trace("Created teacher with name=" + teacher.getName() + " subjectId=" + teacher.getSubject().getId());

		return "redirect:/teachers";
	}

	@PostMapping(value = "/teacher/update")
	public String update(@ModelAttribute("teacher") Teacher teacher) throws WebException {

		int teacherId = teacher.getId();

		log.trace("Post request to update teacher with id=" + teacherId + " on name=" + teacher.getName()
				+ ",subjectId=" + teacher.getSubject().getId());

		try {

			teacherService.update(teacher);

		} catch (DomainException e) {

			log.error("Cannot update teacher=" + teacher, e);
			throw new WebException("Cannot update teacher=" + teacher, e);

		} catch (NumberFormatException e) {

			log.error("The id=" + teacherId + " is wrong.", e);
			throw new WebException("The id=" + teacherId + " is wrong.", e);
		}

		log.trace("Updated teacher with id=" + teacherId + " on name=" + teacher.getName() + ",subjectId="
				+ teacher.getSubject().getId());

		return "redirect:/teacher/" + teacherId;
	}

	@PostMapping(value = "/teacher/delete/{id}")
	public String delete(@PathVariable("id") int teacherId) throws WebException {

		log.trace("Post request to delete teacher with id=" + teacherId);

		try {

			teacherService.delete(teacherId);

		} catch (NumberFormatException e) {

			log.error("The id=" + teacherId + " is wrong.", e);
			throw new WebException("The id=" + teacherId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot delete teacher with id=" + teacherId, e);
			throw new WebException("Cannot delete teacher with id=" + teacherId, e);
		}

		log.trace("Deleted teacher with id=" + teacherId);

		return "redirect:/teachers";
	}

	@GetMapping(value = "/teacherTimetableForDay")
	public ModelAndView showAllTeachers(ModelAndView model) throws WebException {

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
	public ModelAndView displayTeacherTimetableForDay(@RequestParam("teacherId") String teacherId,
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

	@GetMapping(value = "/teacherTimetableForMonth")
	public ModelAndView showTeachers(ModelAndView model) throws WebException {

		log.trace("Get request to find all teachers");

		List<Teacher> teachers = null;

		try {
			teachers = teacherService.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all teachers.", e);
			throw new WebException("Cannot find all teachers.", e);
		}

		model.addObject("teachers", teachers);
		model.setViewName("findTeacherTimetableForMonth");

		log.trace("Found all teachers");

		return model;
	}

	@PostMapping(value = "/displayTeacherTimetableForMonth")
	public ModelAndView displayTeacherTimetableForMonth(@RequestParam("teacherId") String teacherId,
			@RequestParam("date") String dateString) throws WebException {

		log.trace("Get request to find student timetable for month,with teacherId=" + teacherId + " and date="
				+ dateString);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");
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

			lessons = lessonService.getTeacherTimetableForMonth(Integer.parseInt(teacherId), date);

			teachers = teacherService.findAll();

		} catch (DomainException e) {

			log.error("Cannot getTeacherTimetableForMonth() for teacher with id=" + teacherId + " and Date :" + date,
					e);
			throw new WebException(
					"Cannot getTeacherTimetableForMonth() for teacher with id=" + teacherId + " and Date :" + date, e);
		} catch (NumberFormatException e) {

			log.error("The teacher id=" + teacherId + " is wrong.", e);
			throw new WebException("The teacher id=" + teacherId + " is wrong.", e);
		}

		ModelAndView model = new ModelAndView();

		model.addObject("teachers", teachers);
		model.addObject("lessons", lessons);
		model.addObject("teacher", teacher);
		model.setViewName("findTeacherTimetableForMonth");

		log.trace("Found " + lessons.size() + " lessons for teacher with id=" + teacherId + " and date=" + dateString);

		return model;
	}
}
