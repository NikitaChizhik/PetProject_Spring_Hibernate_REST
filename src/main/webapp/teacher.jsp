<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Teacher</title>
<link type="text/css" rel="stylesheet" href="css/style.css">

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">
		<h3>Teacher</h3>

		<form action="teacher" method="post">
			<input type="hidden" name="teacherId" value="${teacher.id}" />

			<table>
				<tr>
					<th>id</th>
					<th>Name</th>
					<th>Subject</th>
					<th>New Subject</th>
					<th>New Name</th>
					<th>Save</th>

				</tr>

				<tr>
					<td>${teacher.id}</td>
					<td>${teacher.name}</td>
					<td>${teacher.subject.name}</td>
					<td><select name="subjectId" class="button">
							<c:forEach var="subject" items="${subjects}">
								<option value="${subject.id }">${subject.name}</option>
							</c:forEach>
					</select></td>
					<td><input type="text" name="name" value="${teacher.name}" /></td>
					<td><input type="submit" value="Save" class="button" />
					<td>
				</tr>



			</table>

		</form>
	</div>

	<p>
		<a href="teachers">Back to list of all teachers</a>
	</p>

</body>

</html>