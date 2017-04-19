

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Student</title>
<link type="text/css" rel="stylesheet" href="css/style.css">

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">
		<h3>Student</h3>


		<form:form action="update" method="post" modelAttribute="student">

			<form:hidden path="id" />

			<table>
				<tr>
					<th>id</th>
					<th>Name</th>
					<th>Update</th>
					<th>Save</th>

				</tr>

				<tr>
					<td>${student.id}</td>
					<td>${student.name}</td>

					<td><form:label path="name">Name:</form:label> <form:input
							path="name" /></td>

					<td><input type="submit" value="Save" class="button" />
					<td>
				</tr>



			</table>

		</form:form>
	</div>





	<p>
		<a href="../students">Back to list of all students</a>
	</p>
	<p>
		<a href="../groups">Back to list of all groups</a>
	</p>
</body>


</html>