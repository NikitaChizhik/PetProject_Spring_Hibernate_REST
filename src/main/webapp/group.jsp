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
					<td><input type="submit" value="Save" class="button" />
					<td>
				</tr>
			</table>
		</form>

		<h3>Students</h3>

		<c:if test="${not empty students}">

			<form action="group/addStudent" method="post">
				<select name="studentId" class="button">

					<c:forEach var="student" items="${students}">
						<option value="${student.id }">${student.name}</option>
					</c:forEach>

				</select> <input type="hidden" name="groupId" value="${group.id }" /> <input
					type="submit" value="Add Student" class="button" />

			</form>
		</c:if>



		<table>


			<c:forEach var="student" items="${group.students}">

				<c:url var="studentLink" value="student">
					<c:param name="studentId" value="${student.id}" />
				</c:url>


				<tr>
					<td><a href="${studentLink}">${student.name}</a></td>
					<td><form action="group/deleteStudent" method="post">

							<input type="hidden" name="groupId" value="${group.id}" /> <input
								type="hidden" name="studentId" value="${student.id}" /> <input
								type="submit" value="Delete" class="button"
								onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false" />
						</form></td>
				</tr>

			</c:forEach>


		</table>


	</div>

	<p>
		<a href="groups">Back to list of groups</a>
	</p>
</body>


</html>