<%@page import="com.nikitachizhik91.university.model.Student"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>allStudents</title>
<link type="text/css" rel="stylesheet" href="css/style.css">

</head>



<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<input type="button" value="AddStudent"
				onclick="window.location.href='addStudentForm.jsp';return false;"
				calss="add-student-button" />

			<table>

				<tr>
					<th>Order</th>
					<th>First Name</th>
					<th>Update|Delete</th>
				</tr>
				<%
					int i = 0;
				%>
				<c:forEach var="student" items="${allStudents}">

					<c:url var="updateLink" value="StudentLoadServlet">
						<c:param name="studentId" value="${student.id}" />
					</c:url>

					<c:url var="deleteLink" value="StudentDeleteServlet">
						<c:param name="studentId" value="${student.id}" />
					</c:url>


					<tr>
						<td><%=++i%></td>
						<td>${student.name}</td>
						<td><a href="${updateLink}">Update|<a
								href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
									Delete</a></td>
					</tr>

				</c:forEach>

			</table>

		</div>

	</div>
</body>


</html>