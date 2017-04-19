package com.nikitachizhik91.university.web.servlets.rooms;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.RoomManager;
import com.nikitachizhik91.university.domain.impl.RoomManagerImpl;
import com.nikitachizhik91.university.model.Room;
import com.nikitachizhik91.university.web.WebException;
//webservlet/rooms
public class RoomsServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(RoomsServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.trace("Get request to find all rooms");

		RoomManager roomManager = new RoomManagerImpl();
		List<Room> rooms = null;

		try {
			rooms = roomManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all rooms.", e);
			throw new WebException("Cannot find all rooms.", e);
		}

		// .setAttribute("rooms", rooms);
		// request.getRequestDispatcher("/rooms.jsp").forward(request,
		// response);

		log.trace("Found all rooms");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String number = request.getParameter("number");

		log.trace("Post request to create room with number=" + number);

		Room room = new Room();
		room.setNumber(number);

		RoomManager roomManager = new RoomManagerImpl();

		try {
			roomManager.create(room);

		} catch (DomainException e) {

			log.error("Cannot add room=" + room, e);
			throw new WebException("Cannot add room=" + room, e);
		}

		response.sendRedirect("rooms");

		log.trace("Created room with number=" + number);
	}
}
