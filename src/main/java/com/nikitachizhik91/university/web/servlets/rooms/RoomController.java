package com.nikitachizhik91.university.web.servlets.rooms;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.RoomManager;
import com.nikitachizhik91.university.model.Room;
import com.nikitachizhik91.university.web.WebException;

@Controller
public class RoomController {
	private final static Logger log = LogManager.getLogger(RoomController.class.getName());

	@Autowired
	private RoomManager roomManager;

	@RequestMapping(value = "rooms", method = RequestMethod.GET)
	public ModelAndView findAll(ModelAndView model) throws WebException {

		log.trace("Get request to find all rooms");

		List<Room> rooms = null;

		try {
			rooms = roomManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all rooms.", e);
			throw new WebException("Cannot find all rooms.", e);
		}

		model.addObject("rooms", rooms);
		model.addObject("room", new Room());
		model.setViewName("rooms");

		log.trace("Found all rooms");

		return model;
	}

	@RequestMapping(value = "room/{id}", method = RequestMethod.GET)
	public ModelAndView findById(ModelAndView model, @PathVariable("id") int roomId) throws WebException {

		log.trace("Get request to find room by id=" + roomId);

		Room room = null;

		try {

			room = roomManager.findById(roomId);

		} catch (NumberFormatException e) {

			log.error("The id=" + roomId + " is wrong.", e);
			throw new WebException("The id=" + roomId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot find room by id=" + roomId, e);
			throw new WebException("Cannot find room by id=" + roomId, e);
		}

		model.addObject("room", room);
		model.setViewName("room");

		log.trace("Found room by id=" + roomId);

		return model;
	}

	@RequestMapping(value = "room/create", method = RequestMethod.POST)
	public String create(@ModelAttribute("room") Room room) throws WebException {

		log.trace("Post request to create room with number=" + room.getNumber());

		try {

			room = roomManager.create(room);

		} catch (DomainException e) {

			log.error("Cannot add room=" + room, e);
			throw new WebException("Cannot add room=" + room, e);
		}

		log.trace("Created room with number=" + room.getNumber());

		return "redirect:/rooms";
	}

	@RequestMapping(value = "room/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("room") Room room) throws WebException {

		int roomId = room.getId();
		String number = room.getNumber();

		log.trace("Post request to update room wtih id=" + roomId + " on number=" + number);

		try {

			room = roomManager.findById(roomId);
			room.setNumber(number);

			roomManager.update(room);

		} catch (DomainException e) {

			log.error("Cannot update room=" + room, e);
			throw new WebException("Cannot update room=" + room, e);

		} catch (NumberFormatException e) {

			log.error("The id=" + roomId + " is wrong.", e);
			throw new WebException("The id=" + roomId + " is wrong.", e);
		}

		log.trace("Updated room wtih id=" + roomId + " on number=" + number);

		return "redirect:/room/" + roomId;
	}

	@RequestMapping(value = "room/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") int roomId) throws WebException {

		log.trace("Post request to delete room with id=" + roomId);

		try {

			roomManager.delete(roomId);

		} catch (NumberFormatException e) {

			log.error("The id=" + roomId + " is wrong.", e);
			throw new WebException("The id=" + roomId + " is wrong.", e);

		} catch (DomainException e) {

			log.error("Cannot delete room with id=" + roomId, e);
			throw new WebException("Cannot delete room with id=" + roomId, e);
		}

		log.trace("Deleted room with id=" + roomId);

		return "redirect:/rooms";
	}
}
