package com.nikitachizhik91.university.web.servlets.rooms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/roomDelete")
public class DeleteRoomServlet extends HttpServlet {
	//
	// private final static Logger log =
	// LogManager.getLogger(DeleteRoomServlet.class.getName());
	// private static final long serialVersionUID = 1L;
	//
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		// String roomId = request.getParameter("roomId");
		//
		// log.trace("Post request to delete room with id=" + roomId);
		//
		// RoomManager roomManager = new RoomManagerImpl();
		//
		// try {
		// roomManager.delete(Integer.parseInt(roomId));
		//
		// } catch (NumberFormatException e) {
		// log.error("The id=" + roomId + " is wrong.", e);
		// throw new WebException("The id=" + roomId + " is wrong.", e);
		//
		// } catch (DomainException e) {
		// log.error("Cannot delete room with id=" + roomId, e);
		// throw new WebException("Cannot delete room with id=" + roomId, e);
		// }
		//
		// response.sendRedirect("rooms");
		//
		// log.trace("Delete room with id=" + roomId);
	}
}
