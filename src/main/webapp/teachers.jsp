<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>allTeachers</title>
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
			<form action="teachers" method="post">
				<table>

					<tr>
						<th>Subject</th>
						<th>Name</th>
						<th></th>
					</tr>
					<tr>
						<td><select name="subjectId" class="button">
								<c:forEach var="subject" items="${subjects}">
									<option value="${subject.id }">${subject.name}</option>
								</c:forEach>
						</select></td>

						<td><input type="text" name="name" /></td>
						<td><input type="submit" value="Add Teacher" class="button" /></td>

					</tr>
				</table>
			</form>



			<table>

				<tr>
					<th>Name</th>
					<th>Subject</th>
					<th>Delete</th>
				</tr>

				<c:forEach var="teacher" items="${teachers}">

					<c:url var="teacherLink" value="teacher">
						<c:param name="teacherId" value="${teacher.id}" />
					</c:url>

					<c:url var="deleteLink" value="teacherDelete">
						<c:param name="teacherId" value="${teacher.id}" />
					</c:url>

					<tr>
						<td><a href="${teacherLink}">${teacher.name}</a></td>
						<td>${teacher.subject.name}</td>
						<td><a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this teacher?'))) return false">
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