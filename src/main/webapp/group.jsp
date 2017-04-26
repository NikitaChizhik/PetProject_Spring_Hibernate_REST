<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Group</title>

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">


</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">
		<h3>Group</h3>

		<form:form action="update" method="post" modelAttribute="group">

			<form:hidden path="id" />


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

					<td><form:input path="name" /></td>
					<td><input type="submit" value="Save" class="button" /></td>
				</tr>
			</table>
		</form:form>

		<h3>Students</h3>

		<c:if test="${not empty students}">

			<form action="addStudent" method="post">
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


				<tr>
					<td><a href="../student/${student.id}">${student.name}</a></td>
					<td><form action="deleteStudent" method="post">

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
		<a href="../groups">Back to list of groups</a>
	</p>
</body>


</html>