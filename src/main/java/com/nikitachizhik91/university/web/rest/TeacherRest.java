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

import com.nikitachizhik91.university.model.Teacher;
import com.nikitachizhik91.university.service.DomainException;
import com.nikitachizhik91.university.service.TeacherService;
import com.nikitachizhik91.university.web.WebException;

@Controller
@Path("/teachers")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class TeacherRest {
	private final static Logger log = LogManager.getLogger(TeacherRest.class.getName());

	@Autowired
	private TeacherService teacherService;

	@GET
	public List<Teacher> findAll() throws WebException {
		log.trace("Get request to find all teachers");
		List<Teacher> teachers = null;
		try {
			teachers = teacherService.findAll();

		} catch (DomainException e) {
			log.error("Cannot find all teachers.", e);
			throw new WebException("Cannot find all teachers.", e);
		}
		log.trace("Found all teachers");
		return teachers;
	}

	@GET
	@Path("/{teacherId}")
	public Teacher findById(@PathParam("teacherId") int teacherId) throws WebException {
		log.trace("Get request to find teacher by id=" + teacherId);
		Teacher teacher = null;
		try {
			teacher = teacherService.findById(teacherId);

		} catch (NumberFormatException e) {

			log.error("The id=" + teacherId + " is wrong.", e);
			throw new WebException("The id=" + teacherId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot find teacher by id=" + teacherId, e);
			throw new WebException("Cannot find teacher by id=" + teacherId, e);
		}
		log.trace("Found teacher by id=" + teacherId);
		return teacher;
	}

	@POST
	public Teacher create(Teacher teacher) throws WebException {
		log.trace("Post request to create teacher with name=" + teacher.getName() + " subjectId="
				+ teacher.getSubject().getId());
		try {
			teacher = teacherService.create(teacher);

		} catch (DomainException e) {
			log.error("Cannot create teacher=" + teacher, e);
			throw new WebException("Cannot create teacher=" + teacher, e);
		}
		log.trace("Created teacher with name=" + teacher.getName() + " subjectId=" + teacher.getSubject().getId());
		return teacher;
	}

	@PUT
	public Teacher update(Teacher teacher) throws WebException {
		int teacherId = teacher.getId();
		log.trace("Post request to update teacher with id=" + teacherId + " on name=" + teacher.getName()
				+ ",subjectId=" + teacher.getSubject().getId());
		try {
			teacher = teacherService.update(teacher);

		} catch (DomainException e) {
			log.error("Cannot update teacher=" + teacher, e);
			throw new WebException("Cannot update teacher=" + teacher, e);
		} catch (NumberFormatException e) {
			log.error("The id=" + teacherId + " is wrong.", e);
			throw new WebException("The id=" + teacherId + " is wrong.", e);
		}
		log.trace("Updated teacher with id=" + teacherId + " on name=" + teacher.getName() + ",subjectId="
				+ teacher.getSubject().getId());
		return teacher;
	}

	@DELETE
	@Path("/{teacherId}")
	public void delete(@PathParam("teacherId") int teacherId) throws WebException {
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
	}
}
