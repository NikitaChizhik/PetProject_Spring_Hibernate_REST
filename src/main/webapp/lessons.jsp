<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>allLessons</title>
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
			<form action="lessons" method="post">
				<table>

					<tr>
						<th>Number</th>
						<th>Subject</th>
						<th>Group</th>

					</tr>
					<tr>
						<td><select name="number" class="button">
								<c:forEach var="number" items="${numbers}">
									<option value="${number}">${number}</option>
								</c:forEach>
						</select></td>

						<td><select name="subjectId" class="button">
								<c:forEach var="subject" items="${subjects}">
									<option value="${subject.id }">${subject.name}</option>
								</c:forEach>
						</select></td>

						<td><select name="groupId" class="button">
								<c:forEach var="group" items="${groups}">
									<option value="${group.id }">${group.name}</option>
								</c:forEach>
						</select></td>



					</tr>
				</table>

				<table>

					<tr>
						<th>Teacher</th>
						<th>Room</th>
						<th>Date</th>
						<th>Add Lesson</th>

					</tr>
					<tr>


						<td><select name="teacherId" class="button">
								<c:forEach var="teacher" items="${teachers}">
									<option value="${teacher.id }">${teacher.name}</option>
								</c:forEach>
						</select></td>

						<td><select name="roomId" class="button">
								<c:forEach var="room" items="${rooms}">
									<option value="${room.id }">${room.number}</option>
								</c:forEach>
						</select></td>



						<td><input type="text" name="date"
							value="2017/03/30 12:30:00" /></td>


						<td><input type="submit" value="Add Lesson" class="button" /></td>

					</tr>
				</table>

			</form>



			<table>

				<tr>

					<th>Subject</th>
					<th>Date</th>
					<th>Number</th>
					<th>Delete</th>

				</tr>

				<c:forEach var="lesson" items="${lessons}">

					<c:url var="lessonLink" value="lesson">
						<c:param name="lessonId" value="${lesson.id}" />
					</c:url>

					<c:url var="deleteLink" value="lessonDelete">
						<c:param name="lessonId" value="${lesson.id}" />
					</c:url>

					<tr>

						<td><a href="${lessonLink}">${lesson.subject.name}</a></td>
						<td>${lesson.date }</td>
						<td>${lesson.number }</td>

						<td><a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this lesson?'))) return false">
								Delete</a></td>
					</tr>

				</c:forEach>

			</table>

		</div>

	</div>
	<p>
		<a href="index.html">Back to University</a>
	</p>

</body>


</html>