package com.nikitachizhik91.university.web.servlets.rooms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

@WebServlet("/room")
public class RoomServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(RoomServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started findById() method.");

		String roomId = request.getParameter("roomId");
		Room room = null;
		RoomManager roomManager = new RoomManagerImpl();

		try {
			room = roomManager.findById(Integer.parseInt(roomId));

		} catch (NumberFormatException e) {
			log.error("The id=" + roomId + " is wrong.", e);
			throw new WebException("The id=" + roomId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot find room by id=" + roomId, e);
			throw new WebException("Cannot find room by id=" + roomId, e);
		}

		request.setAttribute("room", room);
		request.getRequestDispatcher("/room.jsp").forward(request, response);

		log.trace("Finished findById() method.");
		log.info("Found the room.");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started update() method.");

		String number = request.getParameter("number");
		Room room = null;
		RoomManager studentManager = new RoomManagerImpl();
		String roomId = request.getParameter("roomId");

		try {
			room = studentManager.findById(Integer.parseInt(roomId));
			room.setNumber(number);
			studentManager.update(room);

		} catch (DomainException e) {
			log.error("Cannot update room=" + room, e);
			throw new WebException("Cannot update room=" + room, e);

		} catch (NumberFormatException e) {
			log.error("The id=" + roomId + " is wrong.", e);
			throw new WebException("The id=" + roomId + " is wrong.", e);
		}

		request.setAttribute("room", room);
		request.getRequestDispatcher("/room.jsp").forward(request, response);

		log.trace("Finished update() method.");
		log.info("Updated the room.");
	}

}
