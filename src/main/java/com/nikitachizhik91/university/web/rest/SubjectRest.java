package com.nikitachizhik91.university.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.service.DomainException;
import com.nikitachizhik91.university.service.SubjectService;
import com.nikitachizhik91.university.web.WebException;

@Controller
@Path("/subjects")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class SubjectRest {
	private final static Logger log = LogManager.getLogger(SubjectRest.class.getName());

	@Autowired
	private SubjectService subjectService;

	@GET
	public List<Subject> findAll() throws WebException {
		log.trace("Get request to find all subjects");
		List<Subject> subjects = null;
		try {
			subjects = subjectService.findAll();

		} catch (DomainException e) {
			log.error("Cannot find all subjects.", e);
			throw new WebException("Cannot find all subjects.", e);
		}
		log.trace("Found all subjects");
		return subjects;
	}

	@GET
	@Path("/subjectId")
	public Subject findById(@PathParam("subjectId") int subjectId) throws WebException {
		log.trace("Get request to find subject by id=" + subjectId);
		Subject subject = null;
		try {
			subject = subjectService.findById(subjectId);

		} catch (NumberFormatException e) {
			log.error("The id=" + subjectId + " is wrong.", e);
			throw new WebException("The id=" + subjectId + " is wrong.", e);
		} catch (DomainException e) {
			log.error("Cannot find subject by id=" + subjectId, e);
			throw new WebException("Cannot find subject by id=" + subjectId, e);
		}
		log.trace("Found subject by id=" + subjectId);
		return subject;
	}

	@POST
	public Subject create(Subject subject) throws WebException {
		log.trace("Post request to create subject with name=" + subject.getName());
		try {
			subject = subjectService.create(subject);

		} catch (DomainException e) {
			log.error("Cannot create subject=" + subject, e);
			throw new WebException("Cannot create subject=" + subject, e);
		}
		log.trace("Created subject with name=" + subject.getName());
		return subject;
	}

	@PUT
	public Subject update(Subject subject) throws WebException {
		int subjectId = subject.getId();
		log.trace("Post request to update subject wtih id=" + subjectId + " on name=" + subject.getName());
		try {
			subject = subjectService.update(subject);

		} catch (DomainException e) {
			log.error("Cannot update subject=" + subject, e);
			throw new WebException("Cannot update subject=" + subject, e);
		} catch (NumberFormatException e) {
			log.error("The id=" + subjectId + " is wrong.", e);
			throw new WebException("The id=" + subjectId + " is wrong.", e);
		}
		log.trace("Updated subject wtih id=" + subjectId + " on name=" + subject.getName());
		return subject;
	}

	@DELETE
	@Path("/{subjectId}")
	public void delete(@PathParam("subjectId") int subjectId) throws WebException {
		log.trace("Post request to delete subject with id=" + subjectId);
		try {
			subjectService.delete(subjectId);

		} catch (NumberFormatException e) {
			log.error("The id=" + subjectId + " is wrong.", e);
			throw new WebException("The id=" + subjectId + " is wrong.", e);
		} catch (DomainException e) {
			log.error("Cannot delete subject with id=" + subjectId, e);
			throw new WebException("Cannot delete subject with id=" + subjectId, e);
		}
		log.trace("Deleted subject with id=" + subjectId);
	}
}
