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

			<form action="students" method="post">

				<input type="submit" value="Add Student" class="add-student-button" />
				<input type="text" name="name" />
			</form>

			<table>

				<tr>
					<th>Name</th>
					<th>Delete</th>
				</tr>

				<c:forEach var="student" items="${students}">

					<c:url var="studentLink" value="student">
						<c:param name="studentId" value="${student.id}" />
					</c:url>

					<c:url var="deleteLink" value="studentDelete">
						<c:param name="studentId" value="${student.id}" />
					</c:url>

					<tr>
						<td><a href="${studentLink}">${student.name}</a></td>
						<td><a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
								Delete</a></td>
					</tr>

				</c:forEach>

			</table>

		</div>

	</div>
</body>


</html>