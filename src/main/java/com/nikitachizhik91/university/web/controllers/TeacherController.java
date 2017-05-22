package com.nikitachizhik91.university.web.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;
import com.nikitachizhik91.university.service.DomainException;
import com.nikitachizhik91.university.service.SubjectService;
import com.nikitachizhik91.university.service.TeacherService;
import com.nikitachizhik91.university.web.WebException;

@Controller
public class TeacherController {
	private final static Logger log = LogManager.getLogger(TeacherController.class.getName());

	@Autowired
	private TeacherService teacherService;
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
}
