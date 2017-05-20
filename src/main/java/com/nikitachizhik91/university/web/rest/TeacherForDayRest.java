package com.nikitachizhik91.university.web.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.service.DomainException;
import com.nikitachizhik91.university.service.LessonService;
import com.nikitachizhik91.university.web.WebException;

@Controller
@Path("/teacherForDay")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class TeacherForDayRest {
	private final static Logger log = LogManager.getLogger(TeacherForDayRest.class.getName());

	@Autowired
	private LessonService lessonService;

	@GET
	@Path("/{teacherId}/{date}")
	public List<Lesson> displayTeacherTimetableForDay(@PathParam("teacherId") String teacherId,
			@PathParam("date") String dateString) throws WebException {
		log.trace("Get request to find student timetable for day,with teacherId=" + teacherId + " and date="
				+ dateString);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			log.error("Date=" + date + " is wrong.", e);
			throw new WebException("Date=" + date + " is wrong.", e);
		}
		List<Lesson> lessons = null;
		try {
			lessons = lessonService.getTeacherTimetableForDay(Integer.parseInt(teacherId), date);

		} catch (DomainException e) {
			log.error("Cannot getTeacherTimetableForDay() for teacher with id=" + teacherId + " and Date :" + date, e);
			throw new WebException(
					"Cannot getTeacherTimetableForDay() for teacher with id=" + teacherId + " and Date :" + date, e);
		} catch (NumberFormatException e) {
			log.error("The teacher id=" + teacherId + " is wrong.", e);
			throw new WebException("The teacher id=" + teacherId + " is wrong.", e);
		}
		log.trace("Found " + lessons.size() + " lessons for teacher with id=" + teacherId + " and date=" + dateString);
		return lessons;
	}
}
