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
@Path("/timetable")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class TimetableRest {
	private final static Logger log = LogManager.getLogger(TimetableRest.class.getName());

	@Autowired
	private LessonService lessonService;

	@GET
	@Path("/studentForDay/{studentId}/{date}")
	public List<Lesson> displayStudentTimetableForDay(@PathParam("studentId") String studentId,
			@PathParam("date") String dateString) throws WebException {
		log.trace("Get request to find student timetable for day,with student id=" + studentId + " and date="
				+ dateString);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			log.error("Date=" + dateString + " is wrong.", e);
			throw new WebException("Date=" + dateString + " is wrong.", e);
		}
		List<Lesson> lessons = null;
		try {
			
			lessons = lessonService.getStudentTimetableForDay(Integer.parseInt(studentId), date);

		} catch (DomainException e) {
			log.error("Cannot getStudentTimetableForDay() for student with id=" + studentId + " and Date :" + date, e);
			throw new WebException(
					"Cannot getStudentTimetableForDay() for student with id=" + studentId + " and Date :" + date, e);
		} catch (NumberFormatException e) {
			log.error("The student id=" + studentId + " is wrong.", e);
			throw new WebException("The student id=" + studentId + " is wrong.", e);
		}
		log.trace("Found " + lessons.size() + " lessons for student with id=" + studentId + " and date=" + dateString);
		return lessons;
	}

	@GET
	@Path("/studentForMonth/{studentId}/{date}")
	public List<Lesson> displayStudentTimetableForMonth(@PathParam("studentId") String studentId,
			@PathParam("date") String dateString) throws WebException {
		log.trace("Get request to find student timetable for month,with student id=" + studentId + " and date="
				+ dateString);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = formatter.parse(dateString);

		} catch (ParseException e) {
			log.error("Date=" + dateString + " is wrong.", e);
			throw new WebException("Date=" + dateString + " is wrong.", e);
		}

		List<Lesson> lessons = null;
		try {
			lessons = lessonService.getStudentTimetableForMonth(Integer.parseInt(studentId), date);

		} catch (DomainException e) {
			log.error("Cannot getStudentTimetableForDay() for student with id=" + studentId + " and Date :" + date, e);
			throw new WebException(
					"Cannot getStudentTimetableForDay() for student with id=" + studentId + " and Date :" + date, e);
		} catch (NumberFormatException e) {
			log.error("The student id=" + studentId + " is wrong.", e);
			throw new WebException("The student id=" + studentId + " is wrong.", e);
		}
		log.trace("Found " + lessons.size() + " lessons for student with id=" + studentId + " and date=" + dateString);
		return lessons;
	}

	@GET
	@Path("/teacherForDay/{teacherId}/{date}")
	public List<Lesson> displayTeacherTimetableForDay(@PathParam("teacherId") String teacherId,
			@PathParam("date") String dateString) throws WebException {
		log.trace("Get request to find student timetable for day,with teacherId=" + teacherId + " and date="
				+ dateString);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			log.error("Date=" + dateString + " is wrong.", e);
			throw new WebException("Date=" + dateString + " is wrong.", e);
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

	@GET
	@Path("/teacherForMonth/{teacherId}/{date}")
	public List<Lesson> displayTeacherTimetableForMonth(@PathParam("teacherId") String teacherId,
			@PathParam("date") String dateString) throws WebException {
		log.trace("Get request to find student timetable for month,with teacherId=" + teacherId + " and date="
				+ dateString);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			log.error("Date=" + dateString + " is wrong.", e);
			throw new WebException("Date=" + dateString + " is wrong.", e);
		}
		List<Lesson> lessons = null;
		try {
			lessons = lessonService.getTeacherTimetableForMonth(Integer.parseInt(teacherId), date);

		} catch (DomainException e) {
			log.error("Cannot getTeacherTimetableForMonth() for teacher with id=" + teacherId + " and Date :" + date,
					e);
			throw new WebException(
					"Cannot getTeacherTimetableForMonth() for teacher with id=" + teacherId + " and Date :" + date, e);
		} catch (NumberFormatException e) {

			log.error("The teacher id=" + teacherId + " is wrong.", e);
			throw new WebException("The teacher id=" + teacherId + " is wrong.", e);
		}
		log.trace("Found " + lessons.size() + " lessons for teacher with id=" + teacherId + " and date=" + dateString);
		return lessons;
	}
}
