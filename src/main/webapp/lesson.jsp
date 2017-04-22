<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">



<title>Lesson</title>

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">
		<h3>Lesson</h3>

		<form:form action="update" method="post" modelAttribute="lesson">

			<form:hidden path="id" />

			<table>
				<tr>
					<th>id</th>
					<th>Number</th>
					<th>New Number</th>
					<th>Save</th>

				</tr>

				<tr>
					<td>${lesson.id}</td>

					<td>${lesson.number}</td>

					<td><form:select path="number" class="button">

							<c:forEach var="number" items="${numbers}">
								<option value="${number }">${number}</option>
							</c:forEach>

						</form:select></td>



					<td><input type="submit" value="Save" class="button" />
					<td>
				</tr>



			</table>

			<table>

				<tr>
					<th>Subject</th>
					<th>New Subject</th>
					<th>Group</th>
					<th>New Group</th>

				</tr>
				<tr>

					<td>${lesson.subject.name}</td>
					<td><form:select path="subject.id" class="button">

								<c:forEach var="subject" items="${subjects}">
									<option value="${subject.id }">${subject.name}</option>
								</c:forEach>

							</form:select></td>

					<td>${lesson.group.name}</td>
					<td><form:select path="group.id" class="button">

								<c:forEach var="group" items="${groups}">
									<option value="${group.id }">${group.name}</option>
								</c:forEach>

							</form:select></td>



				</tr>
			</table>


			<table>

				<tr>
					<th>Teacher</th>
					<th>New Teacher</th>
					<th>Room</th>
					<th>New Room</th>
				</tr>
				<tr>

					<td>${lesson.teacher.name}</td>
					<td><form:select path="teacher.id" class="button">

								<c:forEach var="teacher" items="${teachers}">
									<option value="${teacher.id }">${teacher.name}</option>
								</c:forEach>

							</form:select></td>

					<td>${lesson.room.number}</td>
					<td><form:select path="room.id" class="button">

								<c:forEach var="room" items="${rooms}">
									<option value="${room.id }">${room.number}</option>
								</c:forEach>

							</form:select></td>

				</tr>
			</table>

			<table>
				<tr>
					<th>Date</th>
					<th>New Date</th>
				</tr>
				<tr>
					<td><fmt:formatDate pattern="dd/MM HH:mm"
							value="${lesson.date }" /></td>
					<!--  <td>${lesson.date }</td>-->
					<td><form:input path="date" value="2017/03/30 12:30:00"
								class="button" /></td>
				</tr>
			</table>

		</form:form>
	</div>

	<p>
		<a href="../lessons">Back to list of all lesson</a>
	</p>

</body>

</html>