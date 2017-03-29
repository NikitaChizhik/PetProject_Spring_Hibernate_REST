<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Group</title>
<link type="text/css" rel="stylesheet" href="css/style.css">

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">
		<h3>Group</h3>

		<form action="group" method="post">
			<input type="hidden" name="groupId" value="${group.id}" />

			<table>
				<tr>
					<th>id</th>
					<th>Name</th>
					<th>Update</th>
					<th>Save</th>

				</tr>

				<tr>
					<td>${group.id}</td>
					<td>${group.name}</td>
					<td><input type="text" name="name" value="${group.name}" /></td>
					<td><input type="submit" value="Save"
						class="add-student-button" />
					<td>
				</tr>
			</table>
		</form>

		<h3>Students</h3>

		<form action="groupStudent" method="post">
			<select name="studentId" class="add-student-button">

				<c:forEach var="student" items="${students}">
					<option value="${student.id }">${student.name}</option>
				</c:forEach>

			</select> <input type="hidden" name="groupId" value="${group.id }" /> <input
				type="submit" value="Add Student" class="add-student-button" />

		</form>




		<table>


			<c:forEach var="student" items="${group.students}">

				<c:url var="studentLink" value="student">
					<c:param name="studentId" value="${student.id}" />
				</c:url>
				
				<c:url var="studentDelete" value="groupStudentDelete">
					<c:param name="studentId" value="${student.id}" />
					<c:param name="groupId" value="${group.id}" />
				</c:url>


				<tr>
					<td><a href="${studentLink}">${student.name}</a></td>
					<td><a href="${studentDelete}">Delete</a></td>
				</tr>

			</c:forEach>


		</table>


	</div>

	<p>
		<a href="groups">Back to list of groups</a>
	</p>
</body>


</html>