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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.GroupService;
import com.nikitachizhik91.university.domain.StudentService;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.web.WebException;

@Controller
public class GroupController {

	private final static Logger log = LogManager.getLogger(GroupController.class.getName());

	@Autowired
	private GroupService groupService;
	@Autowired
	private StudentService studentService;

	@GetMapping(value = "/groups")
	public ModelAndView findAll(ModelAndView model) throws WebException {

		log.trace("Get request to find all groups");

		List<Group> groups = null;

		try {

			groups = groupService.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all groups.", e);
			throw new WebException("Cannot find all groups.", e);
		}

		model.addObject("groups", groups);
		model.setViewName("groups");

		log.trace("Found all groups");

		return model;
	}

	@GetMapping(value = "/group/{id}")
	public ModelAndView findById(ModelAndView model, @PathVariable("id") int groupId) throws WebException {

		log.trace("Get request to find group by id=" + groupId);

		Group group = null;
		List<Student> students = null;

		try {

			group = groupService.findById(groupId);

			students = studentService.findStudentsWithoutGroup();

		} catch (NumberFormatException e) {

			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot find group by id=" + groupId, e);
			throw new WebException("Cannot find group by id=" + groupId, e);
		}

		model.addObject("group", group);
		model.addObject("students", students);
		model.setViewName("group");

		log.trace("Found group by id=" + groupId);

		return model;
	}

	@PostMapping(value = "/group/create")
	public String create(@ModelAttribute("group") Group group) throws WebException {

		log.trace("Post request to create group with name=" + group.getName());

		try {

			group = groupService.create(group);

		} catch (DomainException e) {

			log.error("Cannot create group=" + group, e);
			throw new WebException("Cannot create group=" + group, e);
		}

		log.trace("Created group with name=" + group.getName());

		return "redirect:/groups";
	}

	@PostMapping(value = "/group/update")
	public String update(@ModelAttribute("group") Group group) throws WebException {

		int groupId = group.getId();

		log.trace("Post request to update group with id=" + groupId + " on name" + group.getName());

		try {

			groupService.update(group);

		} catch (DomainException e) {

			log.error("Cannot update group=" + group, e);
			throw new WebException("Cannot update group=" + group, e);

		} catch (NumberFormatException e) {

			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);
		}

		log.trace("Updated group with id=" + groupId + " on name" + group.getName());

		return "redirect:/group/" + groupId;
	}

	@PostMapping(value = "/group/delete/{id}")
	public String delete(@PathVariable("id") int groupId) throws WebException {

		log.trace("Post request to delete group with id=" + groupId);

		try {

			groupService.delete(groupId);

		} catch (NumberFormatException e) {

			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot delete group with id=" + groupId, e);
			throw new WebException("Cannot delete group with id=" + groupId, e);
		}

		log.trace("Deleted group with id=" + groupId);

		return "redirect:/groups";
	}

	@PostMapping(value = "/group/addStudent")
	public String addStudent(@RequestParam("groupId") int groupId, @RequestParam("studentId") int studentId)
			throws WebException {

		log.trace("Post request to add student with id=" + studentId + "to group with id=" + groupId);

		try {

			groupService.addStudent(studentId, groupId);

		} catch (DomainException e) {

			log.error("Cannot add student with id=" + studentId + " to group with id=" + groupId, e);
			throw new WebException("Cannot add student with id=" + studentId + " to group with id=" + groupId, e);

		} catch (NumberFormatException e) {

			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);
		}

		log.trace("Added student with id=" + studentId + "to group with id=" + groupId);

		return "redirect:/group/" + groupId;
	}

	@PostMapping(value = "/group/deleteStudent")
	public String deleteStudent(@RequestParam("groupId") int groupId, @RequestParam("studentId") int studentId)
			throws WebException {

		log.trace("Post request to delete student with id=" + studentId + "from group.");

		try {

			groupService.deleteStudentFromGroup(studentId);

		} catch (DomainException e) {

			log.error("Cannot delete student with id=" + studentId + " from group with id=" + groupId, e);
			throw new WebException("Cannot add student with id=" + studentId + " from group with id=" + groupId, e);

		} catch (NumberFormatException e) {

			log.error("The id=" + studentId + " is wrong.", e);
			throw new WebException("The id=" + studentId + " is wrong.", e);
		}

		log.trace("Deleted student with id=" + studentId + "from group.");

		return "redirect:/group/" + groupId;
	}
}
