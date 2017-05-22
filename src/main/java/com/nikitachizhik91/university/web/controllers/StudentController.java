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
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.service.DomainException;
import com.nikitachizhik91.university.service.LessonService;
import com.nikitachizhik91.university.service.StudentService;
import com.nikitachizhik91.university.web.WebException;

@Controller
public class StudentController {
	private final static Logger log = LogManager.getLogger(StudentController.class.getName());

	@Autowired
	private StudentService studentService;
	@Autowired
	private LessonService lessonService;

	@GetMapping(value = "/students")
	public ModelAndView findAll(ModelAndView model) throws WebException {

		log.trace("Get request to find all students");

		List<Student> students = null;

		try {
			students = studentService.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all students.", e);
			throw new WebException("Cannot find all students.", e);
		}

		model.addObject("students", students);
		model.setViewName("students");

		log.trace("Found all students");

		return model;
	}

	@GetMapping(value = "/student/{id}")
	public ModelAndView findById(ModelAndView model, @PathVariable("id") int studentId) throws WebException {

		log.trace("Get request to find student by id=" + studentId);

		Student student = null;

		try {

			student = studentService.findById(studentId);

		} catch (NumberFormatException e) {

			log.error("The id=" + studentId + " is wrong.", e);
			throw new WebException("The id=" + studentId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot find student by id=" + studentId, e);
			throw new WebException("Cannot find student by id=" + studentId, e);
		}

		model.addObject("student", student);
		model.setViewName("student");

		log.trace("Found student by id=" + studentId);

		return model;
	}

	@PostMapping(value = "/student/create")
	public String create(@ModelAttribute("student") Student student) throws WebException {

		log.trace("Post request to create student with name=" + student.getName());

		try {

			student = studentService.create(student);

		} catch (DomainException e) {

			log.error("Cannot create student=" + student, e);
			throw new WebException("Cannot create student=" + student, e);
		}

		log.trace("Created student with name=" + student.getName());

		return "redirect:/students";
	}

	@PostMapping(value = "/student/update")
	public String update(@ModelAttribute("student") Student student) throws WebException {

		int studentId = student.getId();

		log.trace("Post request to update student wtih id=" + studentId + " on name=" + student.getName());

		try {

			studentService.update(student);

		} catch (DomainException e) {

			log.error("Cannot update student=" + student, e);
			throw new WebException("Cannot update student=" + student, e);

		} catch (NumberFormatException e) {

			log.error("The id=" + studentId + " is wrong.", e);
			throw new WebException("The id=" + studentId + " is wrong.", e);
		}

		log.trace("Updated student wtih id=" + studentId + " on name=" + student.getName());

		return "redirect:/student/" + studentId;
	}

	@PostMapping(value = "/student/delete/{id}")
	public String delete(@PathVariable("id") int studentId) throws WebException {

		log.trace("Post request to delete student with id=" + studentId);

		try {

			studentService.delete(studentId);

		} catch (NumberFormatException e) {

			log.error("The id=" + studentId + " is wrong.", e);
			throw new WebException("The id=" + studentId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot delete student with id=" + studentId, e);
			throw new WebException("Cannot delete student with id=" + studentId, e);
		}

		log.trace("Deleted student with id=" + studentId);

		return "redirect:/students";
	}

	@GetMapping(value = "/studentTimetableForDay")
	public ModelAndView showAllStudents(ModelAndView model) throws WebException {

		log.trace("Get request to find all students");

		List<Student> students = null;

		try {
			students = studentService.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all students.", e);
			throw new WebException("Cannot find all students.", e);
		}

		model.addObject("students", students);
		model.setViewName("findStudentTimetableForDay");

		log.trace("Found all students");

		return model;
	}

	@GetMapping(value = "/studentTimetableForMonth")
	public ModelAndView showStudents(ModelAndView model) throws WebException {

		log.trace("Get request to find all students");

		List<Student> students = null;

		try {
			students = studentService.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all students.", e);
			throw new WebException("Cannot find all students.", e);
		}

		model.addObject("students", students);
		model.setViewName("findStudentTimetableForMonth");

		log.trace("Found all students");

		return model;
	}

	@PostMapping(value = "/displayStudentTimetableForDay")
	public ModelAndView displayStudentTimetableForDay(@RequestParam("studentId") String studentId,
			@RequestParam("date") String dateString) throws WebException {

		log.trace("Get request to find student timetable for day,with student id=" + studentId + " and date="
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
		List<Student> students = null;
		Student student = null;

		try {

			student = studentService.findById(Integer.parseInt(studentId));

			lessons = lessonService.getStudentTimetableForDay(Integer.parseInt(studentId), date);

			students = studentService.findAll();

		} catch (DomainException e) {

			log.error("Cannot getStudentTimetableForDay() for student with id=" + studentId + " and Date :" + date, e);
			throw new WebException(
					"Cannot getStudentTimetableForDay() for student with id=" + studentId + " and Date :" + date, e);
		} catch (NumberFormatException e) {

			log.error("The student id=" + studentId + " is wrong.", e);
			throw new WebException("The student id=" + studentId + " is wrong.", e);
		}

		ModelAndView model = new ModelAndView();

		model.addObject("students", students);
		model.addObject("lessons", lessons);
		model.addObject("student", student);
		model.setViewName("findStudentTimetableForDay");

		log.trace("Found " + lessons.size() + " lessons for student with id=" + studentId + " and date=" + dateString);

		return model;
	}

	@PostMapping(value = "/displayStudentTimetableForMonth")
	public ModelAndView displayStudentTimetableForMonth(@RequestParam("studentId") String studentId,
			@RequestParam("date") String dateString) throws WebException {
		log.trace("Get request to find student timetable for month,with student id=" + studentId + " and date="
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
		List<Student> students = null;
		Student student = null;
		try {

			student = studentService.findById(Integer.parseInt(studentId));
			lessons = lessonService.getStudentTimetableForMonth(Integer.parseInt(studentId), date);
			students = studentService.findAll();

		} catch (DomainException e) {
			log.error("Cannot getStudentTimetableForDay() for student with id=" + studentId + " and Date :" + date, e);
			throw new WebException(
					"Cannot getStudentTimetableForDay() for student with id=" + studentId + " and Date :" + date, e);
		} catch (NumberFormatException e) {
			log.error("The student id=" + studentId + " is wrong.", e);
			throw new WebException("The student id=" + studentId + " is wrong.", e);
		}
		ModelAndView model = new ModelAndView();
		model.addObject("students", students);
		model.addObject("lessons", lessons);
		model.addObject("student", student);
		model.setViewName("findStudentTimetableForMonth");
		log.trace("Found " + lessons.size() + " lessons for student with id=" + studentId + " and date=" + dateString);
		return model;
	}
}
