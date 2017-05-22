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
import org.springframework.web.bind.annotation.PathVariable;

import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.service.DomainException;
import com.nikitachizhik91.university.service.LessonService;
import com.nikitachizhik91.university.web.WebException;

@Controller
@Path("/lessons")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class LessonRest {
	private final static Logger log = LogManager.getLogger(LessonRest.class.getName());

	@Autowired
	private LessonService lessonService;

	@GET
	public List<Lesson> findAll() throws WebException {
		log.trace("Get request to find all lessons");
		List<Lesson> lessons = null;
		try {
			lessons = lessonService.findAll();

		} catch (DomainException e) {
			log.error("Cannot find all lessons.", e);
			throw new WebException("Cannot find all lessons.", e);
		}
		log.trace("Found all lessons");
		return lessons;
	}

	@GET
	@Path("/lessonId")
	public Lesson findById(@PathParam("lessonId") int lessonId) throws WebException {
		log.trace("Get request to find lesson by id=" + lessonId);
		Lesson lesson = null;
		try {
			lesson = lessonService.findById(lessonId);

		} catch (NumberFormatException e) {
			log.error("The id=" + lessonId + " is wrong.", e);
			throw new WebException("The id=" + lessonId + " is wrong.", e);
		} catch (DomainException e) {
			log.error("Cannot find lesson by id=" + lessonId, e);
			throw new WebException("Cannot find lesson by id=" + lessonId, e);
		}
		log.trace("Found lesson by id=" + lessonId);
		return lesson;
	}

	@POST
	public Lesson create(Lesson lesson) throws WebException {
		log.trace("Post request to create lesson=" + lesson);
		try {
			lesson = lessonService.create(lesson);

		} catch (DomainException e) {
			log.error("Cannot create lesson=" + lesson, e);
			throw new WebException("Cannot create lesson=" + lesson, e);
		}
		log.trace("Created lesson=" + lesson);
		return lesson;
	}

	@PUT
	public Lesson update(Lesson lesson) throws WebException {
		log.trace("Post request to update lesson=" + lesson);
		try {
			lesson = lessonService.update(lesson);

		} catch (DomainException e) {
			log.error("Cannot update lesson=" + lesson, e);
			throw new WebException("Cannot update lesson=" + lesson, e);
		}
		log.trace("Updated lesson=" + lesson);
		return lesson;
	}

	@DELETE
	@Path("/lessonId")
	public void delete(@PathParam("lessonId") int lessonId) throws WebException {
		log.trace("Post request to delete lesson with id=" + lessonId);
		try {
			lessonService.delete(lessonId);

		} catch (NumberFormatException e) {
			log.error("The id=" + lessonId + " is wrong.", e);
			throw new WebException("The id=" + lessonId + " is wrong.", e);
		} catch (DomainException e) {
			log.error("Cannot delete lesson with id=" + lessonId, e);
			throw new WebException("Cannot delete lesson with id=" + lessonId, e);
		}
		log.trace("Deleted lesson with id=" + lessonId);
	}
}
