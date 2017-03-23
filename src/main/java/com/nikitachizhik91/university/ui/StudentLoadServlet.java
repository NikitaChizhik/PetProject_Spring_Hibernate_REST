package com.nikitachizhik91.university.ui;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.StudentManager;
import com.nikitachizhik91.university.domain.impl.StudentManagerImpl;
import com.nikitachizhik91.university.model.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentLoadServlet")
public class StudentLoadServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(StudentLoadServlet.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		loadStudent(request, response);

	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentId = request.getParameter("studentId");
		Student student = null;
		StudentManager studentManager = new StudentManagerImpl();

		try {
			student = studentManager.findById(Integer.parseInt(studentId));
		} catch (NumberFormatException e) {
			log.error("Cannot load student.", e);
			throw new ServletException("Cannot load student.", e);
		} catch (DomainException e) {
			log.error("Cannot load student.", e);
			throw new ServletException("Cannot load student.", e);
		}

		request.setAttribute("loadStudent", student);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/updateStudentForm.jsp");
		dispatcher.forward(request, response);

		log.trace("Dispathcer forwarded requeset and response to /updateStudentForm.jsp .");
	}

}
