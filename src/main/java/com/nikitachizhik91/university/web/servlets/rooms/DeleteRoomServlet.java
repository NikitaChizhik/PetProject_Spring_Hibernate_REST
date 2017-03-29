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
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/roomDelete")
public class DeleteRoomServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DeleteRoomServlet.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started delete() method.");

		RoomManager roomManager = new RoomManagerImpl();
		String roomId = request.getParameter("roomId");

		try {
			roomManager.delete(Integer.parseInt(roomId));

		} catch (NumberFormatException e) {
			log.error("The id=" + roomId + " is wrong.", e);
			throw new WebException("The id=" + roomId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete room with id=" + roomId, e);
			throw new WebException("Cannot delete room with id=" + roomId, e);
		}

		response.sendRedirect("rooms");

		log.trace("Finished delete() method.");
	}

}
