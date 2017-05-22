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

import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.service.DepartmentService;
import com.nikitachizhik91.university.service.DomainException;
import com.nikitachizhik91.university.web.WebException;

@Controller
@Path("/departments")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class DepartmentRest {
	private final static Logger log = LogManager.getLogger(DepartmentRest.class.getName());

	@Autowired
	private DepartmentService departmentService;

	@GET
	public List<Department> findAll() throws WebException {
		log.trace("Get request to find all departments");
		List<Department> departments = null;
		try {
			departments = departmentService.findAll();
			

		} catch (DomainException e) {
			log.error("Cannot find all departments.", e);
			throw new WebException("Cannot find all departments.", e);
		}
		log.trace("Found all departments");
		return departments;
	}

	@GET
	@Path("/{departmentId}")
	public Department findById(@PathParam("departmentId") int departmentId) throws WebException {
		log.trace("Get request to find department by id=" + departmentId);
		Department department = null;
		try {
			department = departmentService.findById(departmentId);

		} catch (NumberFormatException e) {
			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);
		} catch (DomainException e) {
			log.error("Cannot find department by id=" + departmentId, e);
			throw new WebException("Cannot find department by id=" + departmentId, e);
		}
		log.trace("Found department by id=" + departmentId);
		return department;
	}

	@POST
	public Department create(Department department) throws WebException {
		log.trace("Post request to create department with name=" + department.getName());
		try {
			department = departmentService.create(department);

		} catch (DomainException e) {
			log.error("Cannot create department=" + department, e);
			throw new WebException("Cannot create department=" + department, e);
		}
		log.trace("Created department with name=" + department.getName());
		return department;
	}

	@PUT
	public Department update(Department department) throws WebException {
		int departmentId = department.getId();
		log.trace("Post request to update department with id=" + departmentId + " on name" + department.getName());
		try {
			department = departmentService.update(department);

		} catch (DomainException e) {
			log.error("Cannot update department=" + department, e);
			throw new WebException("Cannot update department=" + department, e);
		} catch (NumberFormatException e) {
			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);
		}
		log.trace("Updated department with id=" + departmentId + " on name" + department.getName());
		return department;
	}

	@DELETE
	public void delete(@PathParam("departmentId") int departmentId) throws WebException {
		log.trace("Post request to delete department with id=" + departmentId);
		try {
			departmentService.delete(departmentId);

		} catch (NumberFormatException e) {
			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);
		} catch (DomainException e) {
			log.error("Cannot delete department with id=" + departmentId, e);
			throw new WebException("Cannot delete department with id=" + departmentId, e);
		}
		log.trace("Deleted departmentd with id=" + departmentId);
	}

	@POST
	@Path("/{departmentId}/addTeacher_{teacherId}")
	public void addTeacher(@PathParam("departmentId") int departmentId, @PathParam("teacherId") int teacherId)
			throws WebException {
		log.trace("Post request to add teacher with id=" + teacherId + " to department with id=" + departmentId);
		try {
			departmentService.addTeacher(departmentId, teacherId);

		} catch (DomainException e) {
			log.error("Cannot add teacher with id=" + teacherId + " to department with id=" + departmentId, e);
			throw new WebException("Cannot add teacher with id=" + teacherId + " to department with id=" + departmentId,
					e);
		} catch (NumberFormatException e) {
			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);
		}
		log.trace("Added teacher with id=" + teacherId + "to department with id=" + departmentId);
	}

	@DELETE
	@Path("/{departmentId}/deleteTeacher_{teacherId}")
	public void deleteTeacher(@PathParam("departmentId") int departmentId, @PathParam("teacherId") int teacherId)
			throws WebException {
		log.trace("Post request to delete teacher with id=" + teacherId + " from department with id=" + departmentId);
		try {
			departmentService.deleteTeacherFromDepartment(teacherId, departmentId);

		} catch (DomainException e) {
			log.error("Cannot delete teacher with id=" + teacherId + " from department with id=" + departmentId, e);
			throw new WebException(
					"Cannot delete teacher with id=" + teacherId + " from department with id=" + departmentId, e);
		} catch (NumberFormatException e) {
			log.error("The id=" + teacherId + " is wrong.", e);
			throw new WebException("The id=" + teacherId + " is wrong.", e);
		}
		log.trace("Deleted teacher with id=" + teacherId + " from department with id=" + departmentId);
	}

	@POST
	@Path("/{departmentId}/addSubject_{subjectId}")
	public void addSubject(@PathParam("departmentId") int departmentId, @PathParam("subjectId") int subjectId)
			throws WebException {
		log.trace("Post request to add subject with id=" + subjectId + " to department with id=" + departmentId);
		try {
			departmentService.addSubject(departmentId, subjectId);

		} catch (DomainException e) {
			log.error("Cannot add subject with id=" + subjectId + " to department with id=" + departmentId, e);
			throw new WebException("Cannot add subject with id=" + subjectId + " to department with id=" + departmentId,
					e);
		} catch (NumberFormatException e) {
			log.error("The id=" + departmentId + " is wrong.", e);
			throw new WebException("The id=" + departmentId + " is wrong.", e);
		}
		log.trace("Added subject with id=" + subjectId + "to department with id=" + departmentId);
	}

	@DELETE
	@Path("/{departmentId}/deleteSubject_{subjectId}")
	public String deleteSubject(@PathParam("departmentId") int departmentId, @PathParam("subjectId") int subjectId)
			throws WebException {
		log.trace("Post request to delete subject with id=" + subjectId + " from department with id=" + departmentId);
		try {
			departmentService.deleteSubjectFromDepartment(subjectId, departmentId);

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
