<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">


<title>Main page</title>
</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<table>

				<tr>
					<td><a href="students">Students</a> <br> <br></td>
				<tr>
					<td><a href="groups">Groups</a><br> <br></td>
				</tr>
				<tr>
					<td><a href="rooms">Rooms</a><br> <br></td>
				</tr>
				<tr>
					<td><a href="subjects">Subjects</a><br> <br></td>
				</tr>

				<tr>
					<td><a href="teachers">Teachers</a><br> <br></td>
				</tr>
				<tr>
					<td><a href="lessons">Lessons</a><br> <br></td>
				</tr>

				<tr>
					<td><a href="departments">Departments</a><br> <br></td>
				</tr>

				<tr>
					<td><a href="faculties">Faculties</a><br> <br></td>
				</tr>
				<tr>
					<td><a href="studentTimetableForDay">StudentForDay</a><br>
						<br></td>
				</tr>
				<tr>
					<td><a href="studentTimetableForMonth">StudentForMonth</a><br>
						<br></td>
				</tr>
				<tr>
					<td><a href="teacherTimetableForDay">TeacherForDay</a><br>
						<br></td>
				</tr>
				<tr>
					<td><a href="teacherTimetableForMonth">TeacherForMonth</a><br>
						<br></td>
				</tr>


			</table>

		</div>

	</div>
</body>


</html>