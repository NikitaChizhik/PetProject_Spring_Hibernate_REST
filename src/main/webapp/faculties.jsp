<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>allFaculties</title>
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

			<form action="faculties" method="post">

				<input type="text" name="name" /> <input type="submit"
					value="Add Faculty" class="button" />
			</form>

			<table>

				<tr>
					<th>Name</th>
					<th>Departments</th>
					<th>Groups</th>
					<th>Delete</th>
				</tr>

				<c:forEach var="faculty" items="${faculties}">

					<c:url var="facultyLink" value="faculty">
						<c:param name="facultyId" value="${faculty.id}" />
					</c:url>


					<tr>
						<td><a href="${facultyLink}">${faculty.name}</a></td>
						<td>${fn:length(faculty.departments)}</td>
						<td>${fn:length(faculty.groups)}</td>

						<td><form action="faculty/delete" method="post">

								<input type="hidden" name="facultyId" value="${faculty.id}" />
								<input type="submit" value="Delete" class="button"
									onclick="if (!(confirm('Are you sure you want to delete this faculty?'))) return false" />
							</form></td>

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