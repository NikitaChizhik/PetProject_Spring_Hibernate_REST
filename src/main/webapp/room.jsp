<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">


<title>Room</title>


</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">
		<h3>Room</h3>



		<form:form action="update" method="post" modelAttribute="room">

			<form:hidden path="id" />


			<table>
				<tr>
					<th>id</th>
					<th>Number</th>
					<th>Update</th>
					<th>Save</th>

				</tr>

				<tr>
					<td>${room.id}</td>
					<td>${room.number}</td>

					<td><form:label path="number"></form:label> <form:input
							path="number" /></td>

					<td><input type="submit" value="Save" class="button" />
				</tr>



			</table>

		</form:form>
	</div>

	<p>
		<a href="../rooms">Back to list of all rooms</a>
	</p>

</body>

</html>