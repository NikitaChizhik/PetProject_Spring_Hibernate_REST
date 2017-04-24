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

import com.nikitachizhik91.university.domain.DepartmentManager;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.FacultyManager;
import com.nikitachizhik91.university.domain.GroupManager;
import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Faculty;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.web.WebException;

@Controller
public class FacultyController {

	private final static Logger log = LogManager.getLogger(FacultyController.class.getName());

	@Autowired
	private FacultyManager facultyManager;
	@Autowired
	private DepartmentManager departmentManager;
	@Autowired
	private GroupManager groupManager;

	@GetMapping(value = "/faculties")
	public ModelAndView findAll(ModelAndView model) throws WebException {

		log.trace("Get request to find all faculties");

		List<Faculty> faculties = null;

		try {

			faculties = facultyManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all faculties.", e);
			throw new WebException("Cannot find all faculties.", e);
		}

		model.addObject("faculties", faculties);
		model.setViewName("faculties");

		log.trace("Found all faculties");

		return model;
	}

	@GetMapping(value = "/faculty/{id}")
	public ModelAndView findById(ModelAndView model, @PathVariable("id") int facultyId) throws WebException {

		log.trace("Get request to find faculty by id=" + facultyId);

		Faculty faculty = null;

		List<Department> departmentsWithoutFaculty = null;

		List<Group> groupsWithoutFaculty = null;

		try {

			faculty = facultyManager.findById(facultyId);

			departmentsWithoutFaculty = departmentManager.findDepartmentsWithoutFaculty();

			groupsWithoutFaculty = groupManager.findGroupsWithoutFaculty();

		} catch (NumberFormatException e) {

			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot find faculty by id=" + facultyId, e);
			throw new WebException("Cannot find faculty by id=" + facultyId, e);
		}

		model.addObject("faculty", faculty);
		model.addObject("departmentsWithoutFaculty", departmentsWithoutFaculty);
		model.addObject("groupsWithoutFaculty", groupsWithoutFaculty);
		model.setViewName("faculty");

		log.trace("Found faculty by id=" + facultyId);

		return model;
	}

	@PostMapping(value = "/faculty/create")
	public String create(@ModelAttribute("faculty") Faculty faculty) throws WebException {

		log.trace("Post request to create faculty with name=" + faculty.getName());

		try {

			faculty = facultyManager.create(faculty);

		} catch (DomainException e) {

			log.error("Cannot create faculty=" + faculty, e);
			throw new WebException("Cannot create faculty=" + faculty, e);
		}

		log.trace("Created faculty with name=" + faculty.getName());

		return "redirect:/faculties";
	}

	@PostMapping(value = "/faculty/update")
	public String update(@ModelAttribute("faculty") Faculty faculty) throws WebException {

		int facultyId = faculty.getId();

		log.trace("Post request to update faculty with id=" + facultyId + " on name" + faculty.getName());

		try {

			facultyManager.update(faculty);

		} catch (DomainException e) {

			log.error("Cannot update faculty=" + faculty, e);
			throw new WebException("Cannot update faculty=" + faculty, e);

		} catch (NumberFormatException e) {

			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);
		}

		log.trace("Updated faculty with id=" + facultyId + " on name" + faculty.getName());

		return "redirect:/faculty/" + facultyId;
	}

	@PostMapping(value = "/faculty/delete/{id}")
	public String delete(@PathVariable("id") int facultyId) throws WebException {

		log.trace("Post request to delete faculty with id=" + facultyId);

		try {

			facultyManager.delete(facultyId);

		} catch (NumberFormatException e) {

			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot delete faculty with id=" + facultyId, e);
			throw new WebException("Cannot delete faculty with id=" + facultyId, e);
		}

		log.trace("Deleted faculty with id=" + facultyId);

		return "redirect:/faculties";
	}

	@PostMapping(value = "/faculty/addDepartment")
	public String addTeacher(@RequestParam("facultyId") int facultyId, @RequestParam("departmentId") int departmentId)
			throws WebException {

		log.trace("Post request to add department with id=" + departmentId + " to faculty with id=" + facultyId);

		try {

			facultyManager.addDepartment(facultyId, departmentId);

		} catch (DomainException e) {

			log.error("Cannot add department with id=" + departmentId + " to faculty with id=" + facultyId, e);
			throw new WebException("Cannot add department with id=" + departmentId + " to faculty with id=" + facultyId,
					e);

		} catch (NumberFormatException e) {

			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);
		}

		log.trace("Added department with id=" + departmentId + "to faculty with id=" + facultyId);

		return "redirect:/faculty/" + facultyId;
	}

	@PostMapping(value = "/faculty/deleteDepartment")
	public String deleteTeacher(@RequestParam("facultyId") int facultyId,
			@RequestParam("departmentId") int departmentId) throws WebException {

		log.trace("Post request to delete department with id=" + departmentId + " from faculty with id=" + facultyId);

		try {

			facultyManager.deleteDepartmentFromFaculty(departmentId);

		} catch (DomainException e) {

			log.error("Cannot delete department with id=" + departmentId + " from department with id=" + facultyId, e);
			throw new WebException(
					"Cannot delete department with id=" + departmentId + " from department with id=" + facultyId, e);

		} catch (NumberFormatException e) {

			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);
		}

		log.trace("Deleted department with id=" + departmentId + " from faculty with id=" + facultyId);

		return "redirect:/faculty/" + facultyId;
	}

	@PostMapping(value = "/faculty/addGroup")
	public String addGroup(@RequestParam("facultyId") int facultyId, @RequestParam("groupId") int groupId)
			throws WebException {

		log.trace("Post request to add group with id=" + groupId + " to faculty with id=" + facultyId);

		try {

			facultyManager.addGroup(facultyId, groupId);

		} catch (DomainException e) {

			log.error("Cannot add group with id=" + groupId + " to faculty with id=" + facultyId, e);
			throw new WebException("Cannot add group with id=" + groupId + " to faculty with id=" + facultyId, e);

		} catch (NumberFormatException e) {

			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);
		}

		log.trace("Added group with id=" + groupId + "to faculty with id=" + facultyId);

		return "redirect:/faculty/" + facultyId;
	}

	@PostMapping(value = "/faculty/deleteGroup")
	public String deleteGroup(@RequestParam("facultyId") int facultyId, @RequestParam("groupId") int groupId)
			throws WebException {

		log.trace("Post request to delete group with id=" + groupId + " from faculty with id=" + facultyId);

		try {

			facultyManager.deleteGroupFromFaculty(groupId);

		} catch (DomainException e) {

			log.error("Cannot delete group with id=" + groupId + " from department with id=" + facultyId, e);
			throw new WebException("Cannot delete group with id=" + groupId + " from department with id=" + facultyId,
					e);

		} catch (NumberFormatException e) {

			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);
		}

		log.trace("Deleted group with id=" + groupId + " from faculty with id=" + facultyId);

		return "redirect:/faculty/" + facultyId;
	}
}
