package com.nikitachizhik91.university.web.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.service.DomainException;
import com.nikitachizhik91.university.service.LessonService;
import com.nikitachizhik91.university.service.StudentService;
import com.nikitachizhik91.university.web.WebException;

@Controller
@Path("/students")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class StudentRest {
	private final static Logger log = LogManager.getLogger(StudentRest.class.getName());

	@Autowired
	private StudentService studentService;
	@Autowired
	private LessonService lessonService;

	@GET
	public List<Student> findAll() throws WebException {
		log.trace("Get request to find all students");
		List<Student> students = null;
		try {
			students = studentService.findAll();

		} catch (DomainException e) {
			log.error("Cannot find all students.", e);
			throw new WebException("Cannot find all students.", e);
		}
		log.trace("Found all students");
		return students;
	}

	@GET
	@Path("/{studentId}")
	public Student findById(@PathParam("studentId") int studentId) throws WebException {
		log.trace("Get request to find student by id=" + studentId);
		Student student = null;
		try {
			student = studentService.findById(studentId);

		} catch (NumberFormatException e) {
			log.error("The id=" + studentId + " is wrong.", e);
			throw new WebException("The id=" + studentId + " is wrong.", e);
		} catch (DomainException e) {
			log.error("Cannot find student by id=" + studentId, e);
			throw new WebException("Cannot find student by id=" + studentId, e);
		}
		log.trace("Found student by id=" + studentId);
		return student;
	}

	@POST
	public Student create(Student student) throws WebException {
		log.trace("Post request to create student with name=" + student.getName());
		try {
			student = studentService.create(student);

		} catch (DomainException e) {
			log.error("Cannot create student=" + student, e);
			throw new WebException("Cannot create student=" + student, e);
		}
		log.trace("Created student with name=" + student.getName());
		return student;
	}

	@PUT
	public Student update(Student student) throws WebException {
		int studentId = student.getId();
		log.trace("Post request to update student wtih id=" + studentId + " on name=" + student.getName());
		try {
			student = studentService.update(student);

		} catch (DomainException e) {
			log.error("Cannot update student=" + student, e);
			throw new WebException("Cannot update student=" + student, e);
		} catch (NumberFormatException e) {
			log.error("The id=" + studentId + " is wrong.", e);
			throw new WebException("The id=" + studentId + " is wrong.", e);
		}
		log.trace("Updated student wtih id=" + studentId + " on name=" + student.getName());
		return student;
	}

	@DELETE
	@Path("/{studentId}")
	public void delete(@PathParam("studentId") int studentId) throws WebException {
		log.trace("Post request to delete student with id=" + studentId);
		try {
			studentService.delete(studentId);

		} catch (NumberFormatException e) {
			log.error("The id=" + studentId + " is wrong.", e);
			throw new WebException("The id=" + studentId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete student with id=" + studentId, e);
			throw new WebException("Cannot delete student with id=" + studentId, e);
		}
		log.trace("Deleted student with id=" + studentId);
	}
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
			log.error("Date=" + date + " is wrong.", e);
			throw new WebException("Date=" + date + " is wrong.", e);
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
			log.error("Date=" + date + " is wrong.", e);
			throw new WebException("Date=" + date + " is wrong.", e);
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
}
