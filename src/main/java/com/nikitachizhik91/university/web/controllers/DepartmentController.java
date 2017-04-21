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
import com.nikitachizhik91.university.domain.SubjectManager;
import com.nikitachizhik91.university.domain.TeacherManager;
import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;
import com.nikitachizhik91.university.web.WebException;

@Controller
public class DepartmentController {

	private final static Logger log = LogManager.getLogger(DepartmentController.class.getName());

	@Autowired
	private DepartmentManager departmentManager;
	@Autowired
	private SubjectManager subjectManager;
	@Autowired
	private TeacherManager teacherManager;

	@GetMapping(value = "/departments")
	public ModelAndView findAll(ModelAndView model) throws WebException {

		log.trace("Get request to find all departments");

		List<Department> departments = null;

		try {

			departments = departmentManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all departments.", e);
			throw new WebException("Cannot find all departments.", e);
		}

		model.addObject("departments", departments);
		model.addObject("department", new Department());
		model.setViewName("departments");

		log.trace("Found all departments");

		return model;
	}

	@GetMapping(value = "/department/{id}")
	public ModelAndView findById(ModelAndView model, @PathVariable("id") int departmentId) throws WebException {

		log.trace("Get request to find department by id=" + departmentId);

		Department department = null;

		List<Subject> subjectsWithoutDepartment = null;

		List<Teacher> teachersWithoutDepartment = null;

		try {

			department = departmentManager.findById(departmentId);

			subjectsWithoutDepartment = subjectManager.findSubjectsWithoutDepartment();

			teachersWithoutDepartment = teacherManager.findTeachersWithoutDepartment();

		} catch (NumberFormatException e) {

			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot find department by id=" + departmentId, e);
			throw new WebException("Cannot find department by id=" + departmentId, e);
		}

		model.addObject("department", department);
		model.addObject("subjectsWithoutDepartment", subjectsWithoutDepartment);
		model.addObject("teachersWithoutDepartment", teachersWithoutDepartment);
		model.setViewName("department");

		log.trace("Found department by id=" + departmentId);

		return model;
	}

	@PostMapping(value = "/department/create")
	public String create(@ModelAttribute("department") Department department) throws WebException {

		log.trace("Post request to create department with name=" + department.getName());

		try {

			department = departmentManager.create(department);

		} catch (DomainException e) {

			log.error("Cannot create department=" + department, e);
			throw new WebException("Cannot create department=" + department, e);
		}

		log.trace("Created department with name=" + department.getName());

		return "redirect:/departments";
	}

	@PostMapping(value = "/department/update")
	public String update(@ModelAttribute("department") Department department) throws WebException {

		int departmentId = department.getId();

		log.trace("Post request to update department with id=" + departmentId + " on name" + department.getName());

		try {

			departmentManager.update(department);

		} catch (DomainException e) {

			log.error("Cannot update department=" + department, e);
			throw new WebException("Cannot update department=" + department, e);

		} catch (NumberFormatException e) {

			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);
		}

		log.trace("Updated department with id=" + departmentId + " on name" + department.getName());

		return "redirect:/department/" + departmentId;
	}

	@PostMapping(value = "/department/delete/{id}")
	public String delete(@PathVariable("id") int departmentId) throws WebException {

		log.trace("Post request to delete department with id=" + departmentId);

		try {

			departmentManager.delete(departmentId);

		} catch (NumberFormatException e) {

			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot delete department with id=" + departmentId, e);
			throw new WebException("Cannot delete department with id=" + departmentId, e);
		}

		log.trace("Deleted departmentd with id=" + departmentId);

		return "redirect:/departments";
	}

	@PostMapping(value = "/department/addTeacher")
	public String addTeacher(@RequestParam("departmentId") int departmentId, @RequestParam("teacherId") int teacherId)
			throws WebException {

		log.trace("Post request to add teacher with id=" + teacherId + " to department with id=" + departmentId);

		try {

			departmentManager.addTeacher(departmentId, teacherId);

		} catch (DomainException e) {

			log.error("Cannot add teacher with id=" + teacherId + " to department with id=" + departmentId, e);
			throw new WebException("Cannot add teacher with id=" + teacherId + " to department with id=" + departmentId,
					e);

		} catch (NumberFormatException e) {

			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);
		}

		log.trace("Added teacher with id=" + teacherId + "to department with id=" + departmentId);

		return "redirect:/department/" + departmentId;
	}

	@PostMapping(value = "/department/deleteTeacher")
	public String deleteTeacher(@RequestParam("departmentId") int departmentId,
			@RequestParam("teacherId") int teacherId) throws WebException {

		log.trace("Post request to delete teacher with id=" + teacherId + " from department with id=" + departmentId);

		try {

			departmentManager.deleteTeacherFromDepartment(teacherId);

		} catch (DomainException e) {

			log.error("Cannot delete teacher with id=" + teacherId + " from department with id=" + departmentId, e);
			throw new WebException(
					"Cannot delete teacher with id=" + teacherId + " from department with id=" + departmentId, e);
		} catch (NumberFormatException e) {

			log.error("The id=" + teacherId + " is wrong.", e);
			throw new WebException("The id=" + teacherId + " is wrong.", e);
		}

		log.trace("Deleted teacher with id=" + teacherId + " from department with id=" + departmentId);

		return "redirect:/department/" + departmentId;
	}

	@PostMapping(value = "/department/addSubject")
	public String addSubject(@RequestParam("departmentId") int departmentId, @RequestParam("subjectId") int subjectId)
			throws WebException {

		log.trace("Post request to add subject with id=" + subjectId + " to department with id=" + departmentId);

		try {

			departmentManager.addSubject(departmentId, subjectId);

		} catch (DomainException e) {

			log.error("Cannot add subject with id=" + subjectId + " to department with id=" + departmentId, e);
			throw new WebException("Cannot add subject with id=" + subjectId + " to department with id=" + departmentId,
					e);

		} catch (NumberFormatException e) {

			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);
		}

		log.trace("Added subject with id=" + subjectId + "to department with id=" + departmentId);

		return "redirect:/department/" + departmentId;
	}

	@PostMapping(value = "/department/deleteSubject")
	public String deleteSubject(@RequestParam("departmentId") int departmentId,
			@RequestParam("subjectId") int subjectId) throws WebException {

		log.trace("Post request to delete subject with id=" + subjectId + " from department with id=" + departmentId);

		try {

			departmentManager.deleteSubjectFromDepartment(subjectId);

		} catch (DomainException e) {

			log.error("Cannot delete subject with id=" + subjectId + " from department with id=" + departmentId, e);
			throw new WebException(
					"Cannot delete subject with id=" + subjectId + " from department with id=" + departmentId, e);
		} catch (NumberFormatException e) {

			log.error("The id=" + subjectId + " is wrong.", e);
			throw new WebException("The id=" + subjectId + " is wrong.", e);
		}

		log.trace("Deleted subject with id=" + subjectId + " from department with id=" + departmentId);

		return "redirect:/department/" + departmentId;
	}
}
