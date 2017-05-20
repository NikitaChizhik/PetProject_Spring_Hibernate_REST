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

import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.service.DomainException;
import com.nikitachizhik91.university.service.GroupService;
import com.nikitachizhik91.university.web.WebException;

@Controller
@Path("/groups")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class GroupRest {
	private final static Logger log = LogManager.getLogger(GroupRest.class.getName());

	@Autowired
	private GroupService groupService;

	@GET
	public List<Group> findAll() throws WebException {
		log.trace("Get request to find all groups");
		List<Group> groups = null;
		try {
			groups = groupService.findAll();

		} catch (DomainException e) {
			log.error("Cannot find all groups.", e);
			throw new WebException("Cannot find all groups.", e);
		}
		log.trace("Found all groups");
		return groups;
	}

	@GET
	@Path("/{groupId}")
	public Group findById(@PathParam("groupId") int groupId) throws WebException {
		log.trace("Get request to find group by id=" + groupId);
		Group group = null;
		try {
			group = groupService.findById(groupId);

		} catch (NumberFormatException e) {
			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);
		} catch (DomainException e) {
			log.error("Cannot find group by id=" + groupId, e);
			throw new WebException("Cannot find group by id=" + groupId, e);
		}
		log.trace("Found group by id=" + groupId);
		return group;
	}

	@POST
	public Group create(Group group) throws WebException {
		log.trace("Post request to create group with name=" + group.getName());
		try {
			group = groupService.create(group);

		} catch (DomainException e) {
			log.error("Cannot create group=" + group, e);
			throw new WebException("Cannot create group=" + group, e);
		}
		log.trace("Created group with name=" + group.getName());
		return group;
	}

	@PUT
	public Group update(Group group) throws WebException {
		int groupId = group.getId();
		log.trace("Post request to update group with id=" + groupId + " on name" + group.getName());
		try {
			group = groupService.update(group);

		} catch (DomainException e) {
			log.error("Cannot update group=" + group, e);
			throw new WebException("Cannot update group=" + group, e);
		} catch (NumberFormatException e) {
			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);
		}
		log.trace("Updated group with id=" + groupId + " on name" + group.getName());
		return group;
	}

	@DELETE
	@Path("/{groupId}")
	public void delete(@PathParam("groupId") int groupId) throws WebException {
		log.trace("Post request to delete group with id=" + groupId);
		try {
			groupService.delete(groupId);

		} catch (NumberFormatException e) {
			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);
		} catch (DomainException e) {
			log.error("Cannot delete group with id=" + groupId, e);
			throw new WebException("Cannot delete group with id=" + groupId, e);
		}
		log.trace("Deleted group with id=" + groupId);
	}

	@POST
	@Path("/{groupId}/{studentId}")
	public void addStudent(@PathParam("groupId") int groupId, @PathParam("studentId") int studentId)
			throws WebException {
		log.trace("Post request to add student with id=" + studentId + "to group with id=" + groupId);
		try {
			groupService.addStudent(studentId, groupId);

		} catch (DomainException e) {
			log.error("Cannot add student with id=" + studentId + " to group with id=" + groupId, e);
			throw new WebException("Cannot add student with id=" + studentId + " to group with id=" + groupId, e);
		} catch (NumberFormatException e) {
			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);
		}
		log.trace("Added student with id=" + studentId + "to group with id=" + groupId);
	}

	@DELETE
	@Path("/{groupId}/{studentId}")
	public void deleteStudent(@PathParam("groupId") int groupId, @PathParam("studentId") int studentId)
			throws WebException {
		log.trace("Post request to delete student with id=" + studentId + "from group.");
		try {
			groupService.deleteStudentFromGroup(studentId, groupId);

		} catch (DomainException e) {
			log.error("Cannot delete student with id=" + studentId + " from group with id=" + groupId, e);
			throw new WebException("Cannot add student with id=" + studentId + " from group with id=" + groupId, e);
		} catch (NumberFormatException e) {
			log.error("The id=" + studentId + " is wrong.", e);
			throw new WebException("The id=" + studentId + " is wrong.", e);
		}
		log.trace("Deleted student with id=" + studentId + "from group.");
	}
}
