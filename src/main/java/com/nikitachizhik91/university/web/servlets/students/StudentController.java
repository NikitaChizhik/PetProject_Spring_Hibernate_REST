package com.nikitachizhik91.university.web.servlets.students;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.StudentManager;
import com.nikitachizhik91.university.model.Room;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.web.WebException;

@Controller
public class StudentController {
	private final static Logger log = LogManager.getLogger(StudentController.class.getName());

	@Autowired
	private StudentManager studentManager;

	@RequestMapping(value = "students", method = RequestMethod.GET)
	public ModelAndView findAll(ModelAndView model) throws WebException {

		log.trace("Get request to find all students");

		List<Student> students = null;

		try {
			students = studentManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all students.", e);
			throw new WebException("Cannot find all students.", e);
		}

		model.addObject("students", students);
		model.addObject("student", new Student());
		model.setViewName("students");

		log.trace("Found all students");

		return model;
	}

	@RequestMapping(value = "student/{id}", method = RequestMethod.GET)
	public ModelAndView findById(ModelAndView model, @PathVariable("id") int studentId) throws WebException {

		log.trace("Get request to find student by id=" + studentId);

		Student student = null;

		try {

			student = studentManager.findById(studentId);

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

	@RequestMapping(value = "student/create", method = RequestMethod.POST)
	public String create(@ModelAttribute("student") Student student) throws WebException {

		log.trace("Post request to create student with name=" + student.getName());

		try {

			student = studentManager.create(student);

		} catch (DomainException e) {

			log.error("Cannot create student=" + student, e);
			throw new WebException("Cannot create student=" + student, e);
		}

		log.trace("Created student with name=" + student.getName());

		return "redirect:/students";
	}

	@RequestMapping(value = "student/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("student") Student student) throws WebException {

		int studentId = student.getId();
		String name = student.getName();

		log.trace("Post request to update student wtih id=" + studentId + " on name=" + name);

		try {

			student = studentManager.findById(studentId);
			student.setName(name);

			studentManager.update(student);

		} catch (DomainException e) {

			log.error("Cannot update student=" + student, e);
			throw new WebException("Cannot update student=" + student, e);

		} catch (NumberFormatException e) {

			log.error("The id=" + studentId + " is wrong.", e);
			throw new WebException("The id=" + studentId + " is wrong.", e);
		}

		log.trace("Updated student wtih id=" + studentId + " on name=" + name);

		return "redirect:/student/" + studentId;
	}

	@RequestMapping(value = "student/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") int studentId) throws WebException {

		log.trace("Post request to delete student with id=" + studentId);

		try {

			studentManager.delete(studentId);

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
}
