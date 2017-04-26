<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">



<title>Teacher</title>

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">
		<h3>Teacher</h3>

		<form:form action="update" method="post" modelAttribute="teacher">

			<form:hidden path="id" />

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



					<td><form:select path="subject.id" class="button">

							<c:forEach var="subject" items="${subjects}">
								<option value="${subject.id }">${subject.name}</option>
							</c:forEach>

						</form:select></td>


					<td><form:input path="name" /></td>
					<td><input type="submit" value="Save" class="button" />
					<td>
				</tr>



			</table>

		</form:form>
	</div>

	<p>
		<a href="../teachers">Back to list of all teachers</a>
	</p>

</body>

</html>