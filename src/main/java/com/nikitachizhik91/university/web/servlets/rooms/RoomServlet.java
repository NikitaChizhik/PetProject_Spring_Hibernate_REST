package com.nikitachizhik91.university.web.servlets.rooms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@WebServlet("/room")
public class RoomServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(RoomServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// String roomId = request.getParameter("roomId");
		//
		// log.trace("Get request to find room by id=" + roomId);
		//
		// RoomManager roomManager = new RoomManagerImpl();
		// Room room = null;
		//
		// try {
		// room = roomManager.findById(Integer.parseInt(roomId));
		//
		// } catch (NumberFormatException e) {
		// log.error("The id=" + roomId + " is wrong.", e);
		// throw new WebException("The id=" + roomId + " is wrong.", e);
		//
		// } catch (DomainException e) {
		// log.error("Cannot find room by id=" + roomId, e);
		// throw new WebException("Cannot find room by id=" + roomId, e);
		// }
		//
		// request.setAttribute("room", room);
		// request.getRequestDispatcher("/room.jsp").forward(request, response);
		//
		// log.trace("Found room by id=" + roomId);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// String roomId = request.getParameter("roomId");
		// String number = request.getParameter("number");
		//
		// log.trace("Post request to update room wtih id=" + roomId + " on
		// number=" + number);
		//
		// RoomManager studentManager = new RoomManagerImpl();
		// Room room = null;
		//
		// try {
		// room = studentManager.findById(Integer.parseInt(roomId));
		// room.setNumber(number);
		//
		// studentManager.update(room);
		//
		// } catch (DomainException e) {
		// log.error("Cannot update room=" + room, e);
		// throw new WebException("Cannot update room=" + room, e);
		//
		// } catch (NumberFormatException e) {
		// log.error("The id=" + roomId + " is wrong.", e);
		// throw new WebException("The id=" + roomId + " is wrong.", e);
		// }
		//
		// response.sendRedirect("room?roomId=" + roomId);
		//
		// log.trace("Updated room wtih id=" + roomId + " on number=" + number);
	}
}
