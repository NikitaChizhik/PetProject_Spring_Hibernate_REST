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

import com.nikitachizhik91.university.model.Faculty;
import com.nikitachizhik91.university.service.DomainException;
import com.nikitachizhik91.university.service.FacultyService;
import com.nikitachizhik91.university.web.WebException;

@Controller
@Path("/faculties")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class FacultyRest {
	private final static Logger log = LogManager.getLogger(FacultyRest.class.getName());

	@Autowired
	private FacultyService facultyService;

	@GET
	public List<Faculty> findAll() throws WebException {
		log.trace("Get request to find all faculties");
		List<Faculty> faculties = null;
		try {
			faculties = facultyService.findAll();

		} catch (DomainException e) {
			log.error("Cannot find all faculties.", e);
			throw new WebException("Cannot find all faculties.", e);
		}
		log.trace("Found all faculties");
		return faculties;
	}

	@GET
	@Path("/{facultyId}")
	public Faculty findById(@PathParam("facultyId") int facultyId) throws WebException {
		log.trace("Get request to find faculty by id=" + facultyId);
		Faculty faculty = null;
		try {
			faculty = facultyService.findById(facultyId);

		} catch (NumberFormatException e) {
			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);
		} catch (DomainException e) {
			log.error("Cannot find faculty by id=" + facultyId, e);
			throw new WebException("Cannot find faculty by id=" + facultyId, e);
		}
		log.trace("Found faculty by id=" + facultyId);
		return faculty;
	}

	@POST
	public Faculty create(Faculty faculty) throws WebException {
		log.trace("Post request to create faculty with name=" + faculty.getName());
		try {
			faculty = facultyService.create(faculty);

		} catch (DomainException e) {
			log.error("Cannot create faculty=" + faculty, e);
			throw new WebException("Cannot create faculty=" + faculty, e);
		}
		log.trace("Created faculty with name=" + faculty.getName());
		return faculty;
	}

	@PUT
	public Faculty update(Faculty faculty) throws WebException {
		int facultyId = faculty.getId();
		log.trace("Post request to update faculty with id=" + facultyId + " on name" + faculty.getName());
		try {
			faculty = facultyService.update(faculty);

		} catch (DomainException e) {
			log.error("Cannot update faculty=" + faculty, e);
			throw new WebException("Cannot update faculty=" + faculty, e);
		} catch (NumberFormatException e) {
			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);
		}
		log.trace("Updated faculty with id=" + facultyId + " on name" + faculty.getName());
		return faculty;
	}

	@DELETE
	@Path("/{facultyId}")
	public void delete(@PathParam("facultyId") int facultyId) throws WebException {
		log.trace("Post request to delete faculty with id=" + facultyId);
		try {
			facultyService.delete(facultyId);

		} catch (NumberFormatException e) {
			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);
		} catch (DomainException e) {
			log.error("Cannot delete faculty with id=" + facultyId, e);
			throw new WebException("Cannot delete faculty with id=" + facultyId, e);
		}
		log.trace("Deleted faculty with id=" + facultyId);
	}

	@POST
	@Path("/{facultyId}/addDepartment_{departmentId}")
	public void addTeacher(@PathParam("facultyId") int facultyId, @PathParam("departmentId") int departmentId)
			throws WebException {
		log.trace("Post request to add department with id=" + departmentId + " to faculty with id=" + facultyId);
		try {
			facultyService.addDepartment(facultyId, departmentId);

		} catch (DomainException e) {
			log.error("Cannot add department with id=" + departmentId + " to faculty with id=" + facultyId, e);
			throw new WebException("Cannot add department with id=" + departmentId + " to faculty with id=" + facultyId,
					e);
		} catch (NumberFormatException e) {
			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);
		}
		log.trace("Added department with id=" + departmentId + "to faculty with id=" + facultyId);
	}

	@DELETE
	@Path("/{facultyId}/deleteDepartment_{departmentId}")
	public void deleteTeacher(@PathParam("facultyId") int facultyId, @PathParam("departmentId") int departmentId)
			throws WebException {
		log.trace("Post request to delete department with id=" + departmentId + " from faculty with id=" + facultyId);
		try {
			facultyService.deleteDepartmentFromFaculty(departmentId, facultyId);

		} catch (DomainException e) {
			log.error("Cannot delete department with id=" + departmentId + " from department with id=" + facultyId, e);
			throw new WebException(
					"Cannot delete department with id=" + departmentId + " from department with id=" + facultyId, e);
		} catch (NumberFormatException e) {
			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);
		}
		log.trace("Deleted department with id=" + departmentId + " from faculty with id=" + facultyId);
	}

	@POST
	@Path("/{facultyId}/addGroup_{groupId}")
	public void addGroup(@PathParam("facultyId") int facultyId, @PathParam("groupId") int groupId) throws WebException {
		log.trace("Post request to add group with id=" + groupId + " to faculty with id=" + facultyId);
		try {
			facultyService.addGroup(facultyId, groupId);

		} catch (DomainException e) {
			log.error("Cannot add group with id=" + groupId + " to faculty with id=" + facultyId, e);
			throw new WebException("Cannot add group with id=" + groupId + " to faculty with id=" + facultyId, e);
		} catch (NumberFormatException e) {
			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);
		}
		log.trace("Added group with id=" + groupId + "to faculty with id=" + facultyId);
	}

	@DELETE
	@Path("/{facultyId}/deleteGroup_{groupId}")
	public void deleteGroup(@PathParam("facultyId") int facultyId, @PathParam("groupId") int groupId)
			throws WebException {
		log.trace("Post request to delete group with id=" + groupId + " from faculty with id=" + facultyId);
		try {
			facultyService.deleteGroupFromFaculty(groupId, facultyId);

		} catch (DomainException e) {
			log.error("Cannot delete group with id=" + groupId + " from department with id=" + facultyId, e);
			throw new WebException("Cannot delete group with id=" + groupId + " from department with id=" + facultyId,
					e);
		} catch (NumberFormatException e) {
			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);
		}
		log.trace("Deleted group with id=" + groupId + " from faculty with id=" + facultyId);
	}
}
