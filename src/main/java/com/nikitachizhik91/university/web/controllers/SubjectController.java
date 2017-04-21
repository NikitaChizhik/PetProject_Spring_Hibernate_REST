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

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.SubjectManager;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.web.WebException;

@Controller
public class SubjectController {
	private final static Logger log = LogManager.getLogger(SubjectController.class.getName());

	@Autowired
	private SubjectManager subjectManager;

	@GetMapping(value = "/subjects")
	public ModelAndView findAll(ModelAndView model) throws WebException {

		log.trace("Get request to find all subjects");

		List<Subject> subjects = null;

		try {
			subjects = subjectManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all subjects.", e);
			throw new WebException("Cannot find all subjects.", e);
		}

		model.addObject("subjects", subjects);
		model.addObject("subject", new Subject());
		model.setViewName("subjects");

		log.trace("Found all subjects");

		return model;
	}

	@GetMapping(value = "/subject/{id}")
	public ModelAndView findById(ModelAndView model, @PathVariable("id") int subjectId) throws WebException {

		log.trace("Get request to find subject by id=" + subjectId);

		Subject subject = null;

		try {

			subject = subjectManager.findById(subjectId);

		} catch (NumberFormatException e) {

			log.error("The id=" + subjectId + " is wrong.", e);
			throw new WebException("The id=" + subjectId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot find subject by id=" + subjectId, e);
			throw new WebException("Cannot find subject by id=" + subjectId, e);
		}

		model.addObject("subject", subject);
		model.setViewName("subject");

		log.trace("Found subject by id=" + subjectId);

		return model;
	}

	@PostMapping(value = "/subject/create")
	public String create(@ModelAttribute("subject") Subject subject) throws WebException {

		log.trace("Post request to create subject with name=" + subject.getName());

		try {

			subject = subjectManager.create(subject);

		} catch (DomainException e) {

			log.error("Cannot create subject=" + subject, e);
			throw new WebException("Cannot create subject=" + subject, e);
		}

		log.trace("Created subject with name=" + subject.getName());

		return "redirect:/subjects";
	}

	@PostMapping(value = "/subject/update")
	public String update(@ModelAttribute("subject") Subject subject) throws WebException {

		int subjectId = subject.getId();

		log.trace("Post request to update subject wtih id=" + subjectId + " on name=" + subject.getName());

		try {

			subjectManager.update(subject);

		} catch (DomainException e) {

			log.error("Cannot update subject=" + subject, e);
			throw new WebException("Cannot update subject=" + subject, e);

		} catch (NumberFormatException e) {

			log.error("The id=" + subjectId + " is wrong.", e);
			throw new WebException("The id=" + subjectId + " is wrong.", e);
		}

		log.trace("Updated subject wtih id=" + subjectId + " on name=" + subject.getName());

		return "redirect:/subject/" + subjectId;
	}

	@PostMapping(value = "/subject/delete/{id}")
	public String delete(@PathVariable("id") int subjectId) throws WebException {

		log.trace("Post request to delete subject with id=" + subjectId);

		try {

			subjectManager.delete(subjectId);

		} catch (NumberFormatException e) {

			log.error("The id=" + subjectId + " is wrong.", e);
			throw new WebException("The id=" + subjectId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot delete subject with id=" + subjectId, e);
			throw new WebException("Cannot delete subject with id=" + subjectId, e);
		}

		log.trace("Deleted subject with id=" + subjectId);

		return "redirect:/subjects";
	}
}
