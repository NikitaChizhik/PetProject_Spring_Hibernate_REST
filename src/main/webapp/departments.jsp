<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<title>allDepartments</title>
</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">


			<form action="department/create" method="post">

				<input type="text" name="name" />
				<input type="submit" value="Add Department" class="button" />
			</form>


			<table>

				<tr>
					<th>Name</th>
					<th>Subjects</th>
					<th>Teachers</th>
					<th>Delete</th>
				</tr>

				<c:forEach var="department" items="${departments}">


					<tr>
						<td>
							<a href="department/${department.id}">${department.name}</a>
						</td>
						<td>${fn:length(department.subjects)}</td>
						<td>${fn:length(department.teachers)}</td>

						<td>
							<form action="department/delete/${department.id }" method="post">

								<input type="submit" value="Delete" class="button"
									onclick="if (!(confirm('Are you sure you want to delete this department?'))) return false" />
							</form>
						</td>

					</tr>

				</c:forEach>

			</table>

		</div>

	</div>
	<p>
		<a href="index.jsp">Back to University</a>
	</p>

</body>

</html>