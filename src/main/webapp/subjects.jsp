<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>allSubjects</title>
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

			<form action="subjects" method="post">

				<input type="text" name="name" /> <input type="submit"
					value="Add Subject" class="button" />
			</form>

			<table>

				<tr>
					<th>Name</th>
					<th>Delete</th>
				</tr>

				<c:forEach var="subject" items="${subjects}">

					<c:url var="subjectLink" value="subject">
						<c:param name="subjectId" value="${subject.id}" />
					</c:url>

					<c:url var="deleteLink" value="subjectDelete">
						<c:param name="subjectId" value="${subject.id}" />
					</c:url>

					<tr>
						<td><a href="${subjectLink}">${subject.name}</a></td>
						<td><a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this subject?'))) return false">
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