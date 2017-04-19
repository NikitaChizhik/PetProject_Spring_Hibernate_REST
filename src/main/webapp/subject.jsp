<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Subject</title>
<link type="text/css" rel="stylesheet" href="css/style.css">

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">
		<h3>Subject</h3>

		<form:form action="update" method="post" modelAttribute="subject">

			<form:hidden path="id" />

			<table>
				<tr>
					<th>id</th>
					<th>Name</th>
					<th>Update</th>
					<th>Save</th>

				</tr>

				<tr>
					<td>${subject.id}</td>
					<td>${subject.name}</td>

					<td><form:label path="name">Name:</form:label> <form:input
							path="name" /></td>

					<td><input type="submit" value="Save" class="button" />
					<td>
				</tr>



			</table>

		</form:form>
	</div>

	<p>
		<a href="../subjects">Back to list of all subjects</a>
	</p>

</body>

</html>