package com.nikitachizhik91.university.web.controllers;

import java.util.ArrayList;
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

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.GroupManager;
import com.nikitachizhik91.university.domain.LessonManager;
import com.nikitachizhik91.university.domain.RoomManager;
import com.nikitachizhik91.university.domain.SubjectManager;
import com.nikitachizhik91.university.domain.TeacherManager;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Room;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;
import com.nikitachizhik91.university.web.WebException;

@Controller
public class LessonController {

	private final static Logger log = LogManager.getLogger(LessonController.class.getName());

	@Autowired
	private LessonManager lessonManager;
	@Autowired
	private SubjectManager subjectManager;
	@Autowired
	private GroupManager groupManager;
	@Autowired
	private TeacherManager teacherManager;
	@Autowired
	private RoomManager roomManager;

	@GetMapping(value = "/lessons")
	public ModelAndView findAll(ModelAndView model) throws WebException {

		log.trace("Get request to find all lessons");

		List<Lesson> lessons = null;

		List<Subject> subjects = new ArrayList<Subject>();

		List<Group> groups = null;

		List<Teacher> teachers = null;

		List<Room> rooms = null;

		try {

			lessons = lessonManager.findAll();

			subjects = subjectManager.findAll();

			groups = groupManager.findAll();

			teachers = teacherManager.findAll();

			rooms = roomManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all lessons.", e);
			throw new WebException("Cannot find all lessons.", e);
		}

		int[] numbers = { 1, 2, 3, 4, 5 };

		model.addObject("rooms", rooms);
		model.addObject("teachers", teachers);
		model.addObject("groups", groups);
		model.addObject("numbers", numbers);
		model.addObject("lessons", lessons);
		model.addObject("subjects", subjects);
		model.addObject("lesson", new Lesson());
		model.setViewName("lessons");

		log.trace("Found all lessons");

		return model;
	}

	@GetMapping(value = "/lesson/{id}")
	public ModelAndView findById(ModelAndView model, @PathVariable("id") int lessonId) throws WebException {

		log.trace("Get request to find lesson by id=" + lessonId);

		Lesson lesson = null;

		List<Subject> subjects = new ArrayList<Subject>();

		List<Group> groups = null;

		List<Teacher> teachers = null;

		List<Room> rooms = null;

		try {

			lesson = lessonManager.findById(lessonId);

			subjects = subjectManager.findAll();

			groups = groupManager.findAll();

			teachers = teacherManager.findAll();

			rooms = roomManager.findAll();

		} catch (NumberFormatException e) {

			log.error("The id=" + lessonId + " is wrong.", e);
			throw new WebException("The id=" + lessonId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot find lesson by id=" + lessonId, e);
			throw new WebException("Cannot find lesson by id=" + lessonId, e);
		}

		int[] numbers = { 1, 2, 3, 4, 5 };

		model.addObject("rooms", rooms);
		model.addObject("teachers", teachers);
		model.addObject("groups", groups);
		model.addObject("numbers", numbers);
		model.addObject("lesson", lesson);
		model.addObject("subjects", subjects);
		model.setViewName("lesson");

		log.trace("Found lesson by id=" + lessonId);

		return model;
	}

	@PostMapping(value = "/lesson/create")
	public String create(@ModelAttribute("lesson") Lesson lesson) throws WebException {

		log.trace("Post request to create lesson=" + lesson);

		try {

			lesson = lessonManager.create(lesson);

		} catch (DomainException e) {

			log.error("Cannot create lesson=" + lesson, e);
			throw new WebException("Cannot create lesson=" + lesson, e);
		}

		log.trace("Created lesson=" + lesson);

		return "redirect:/lessons";
	}

	@PostMapping(value = "/lesson/update")
	public String update(@ModelAttribute("lesson") Lesson lesson) throws WebException {

		log.trace("Post request to update lesson=" + lesson);

		try {

			lesson = lessonManager.update(lesson);

		} catch (DomainException e) {

			log.error("Cannot update lesson=" + lesson, e);
			throw new WebException("Cannot update lesson=" + lesson, e);

		}

		log.trace("Updated lesson=" + lesson);

		return "redirect:/lesson/" + lesson.getId();
	}

	@PostMapping(value = "/lesson/delete/{id}")
	public String delete(@PathVariable("id") int lessonId) throws WebException {

		log.trace("Post request to delete lesson with id=" + lessonId);

		try {

			lessonManager.delete(lessonId);

		} catch (NumberFormatException e) {

			log.error("The id=" + lessonId + " is wrong.", e);
			throw new WebException("The id=" + lessonId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot delete lesson with id=" + lessonId, e);
			throw new WebException("Cannot delete lesson with id=" + lessonId, e);
		}

		log.trace("Deleted lesson with id=" + lessonId);

		return "redirect:/lessons";
	}
}
