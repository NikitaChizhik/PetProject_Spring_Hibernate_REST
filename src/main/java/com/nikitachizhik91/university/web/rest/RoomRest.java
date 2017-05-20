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

import com.nikitachizhik91.university.model.Room;
import com.nikitachizhik91.university.service.DomainException;
import com.nikitachizhik91.university.service.RoomService;
import com.nikitachizhik91.university.web.WebException;

@Controller
@Path("/rooms")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class RoomRest {
	private final static Logger log = LogManager.getLogger(RoomRest.class.getName());

	@Autowired
	private RoomService roomService;

	@GET
	public List<Room> findAll() throws WebException {
		log.trace("Get request to find all rooms");
		List<Room> rooms = null;
		try {
			rooms = roomService.findAll();

		} catch (DomainException e) {
			log.error("Cannot find all rooms.", e);
			throw new WebException("Cannot find all rooms.", e);
		}
		log.trace("Found all rooms");
		return rooms;
	}

	@GET
	@Path("/{roomId}")
	public Room findById(@PathParam("roomId") int roomId) throws WebException {
		log.trace("Get request to find room by id=" + roomId);
		Room room = null;
		try {
			room = roomService.findById(roomId);

		} catch (NumberFormatException e) {
			log.error("The id=" + roomId + " is wrong.", e);
			throw new WebException("The id=" + roomId + " is wrong.", e);
		} catch (DomainException e) {
			log.error("Cannot find room by id=" + roomId, e);
			throw new WebException("Cannot find room by id=" + roomId, e);
		}
		log.trace("Found room by id=" + roomId);
		return room;
	}

	@POST
	public Room create(Room room) throws WebException {
		log.trace("Post request to create room with number=" + room.getNumber());
		try {
			room = roomService.create(room);

		} catch (DomainException e) {
			log.error("Cannot add room=" + room, e);
			throw new WebException("Cannot add room=" + room, e);
		}
		log.trace("Created room with number=" + room.getNumber());
		return room;
	}

	@PUT
	public Room update(Room room) throws WebException {
		int roomId = room.getId();
		log.trace("Post request to update room wtih id=" + roomId + " on number=" + room.getNumber());
		try {
			room = roomService.update(room);

		} catch (DomainException e) {
			log.error("Cannot update room=" + room, e);
			throw new WebException("Cannot update room=" + room, e);
		} catch (NumberFormatException e) {
			log.error("The id=" + roomId + " is wrong.", e);
			throw new WebException("The id=" + roomId + " is wrong.", e);
		}
		log.trace("Updated room wtih id=" + roomId + " on number=" + room.getNumber());
		return room;
	}

	@DELETE
	@Path("/{roomId}")
	public void delete(@PathParam("roomId") int roomId) throws WebException {
		log.trace("Post request to delete room with id=" + roomId);
		try {
			roomService.delete(roomId);

		} catch (NumberFormatException e) {
			log.error("The id=" + roomId + " is wrong.", e);
			throw new WebException("The id=" + roomId + " is wrong.", e);
		} catch (DomainException e) {
			log.error("Cannot delete room with id=" + roomId, e);
			throw new WebException("Cannot delete room with id=" + roomId, e);
		}
		log.trace("Deleted room with id=" + roomId);
	}
}
