<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Department</title>
<link type="text/css" rel="stylesheet" href="css/style.css">

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">
		<h3>Department</h3>


		<form action="department" method="post">
			<input type="hidden" name="departmentId" value="${department.id}" />

			<table>
				<tr>
					<th>id</th>
					<th>Name</th>
					<th>Update</th>
					<th>Save</th>

				</tr>

				<tr>
					<td>${department.id}</td>
					<td>${department.name}</td>
					<td><input type="text" name="name" value="${department.name}" /></td>
					<td><input type="submit" value="Save" class="button" />
					<td>
				</tr>

			</table>
		</form>







		<h3>Subjects</h3>

		<c:if test="${not empty subjectsWithoutDepartment}">

			<form action="department/addSubject" method="post">


				<select name="subjectId" class="button">
					<c:forEach var="subject" items="${subjectsWithoutDepartment}">
						<option value="${subject.id}">${subject.name}</option>
					</c:forEach>
				</select> <input type="hidden" name="departmentId" value="${department.id }" />
				<input type="submit" value="Add Subject" class="button" />

			</form>
		</c:if>

		<table>

			<tr>
				<th>Subjects</th>
				<th>Delete</th>

			</tr>


			<c:forEach var="subject" items="${department.subjects}">

				<c:url var="subjectLink" value="subject">
					<c:param name="subjectId" value="${subject.id}" />
				</c:url>


				<tr>
					<td><a href="${subjectLink}">${subject.name}</a></td>

					<td><form action="department/deleteSubject" method="post">

							<input type="hidden" name="departmentId" value="${department.id}" />
							<input type="hidden" name="subjectId" value="${subject.id}" /> <input
								type="submit" value="Delete" class="button"
								onclick="if (!(confirm('Are you sure you want to delete this subject?'))) return false" />
						</form></td>
				</tr>
			</c:forEach>
		</table>







		<h3>Teachers</h3>


		<c:if test="${not empty teachersWithoutDepartment}">
			<form action="department/addTeacher" method="post">


				<select name="teacherId" class="button">

					<c:forEach var="teacher" items="${teachersWithoutDepartment}">
						<option value="${teacher.id}">${teacher.name}</option>
					</c:forEach>
				</select> <input type="hidden" name="departmentId" value="${department.id }" />
				<input type="submit" value="Add Teacher" class="button" />

			</form>
		</c:if>

		<table>

			<tr>
				<th>Teachers</th>
				<th>Delete</th>

			</tr>


			<c:forEach var="teacher" items="${department.teachers}">

				<c:url var="teacherLink" value="teacher">
					<c:param name="teacherId" value="${teacher.id}" />
				</c:url>



				<tr>
					<td><a href="${teacherLink}">${teacher.name}</a></td>

					<td><form action="department/deleteTeacher" method="post">

							<input type="hidden" name="departmentId" value="${department.id}" />
							<input type="hidden" name="teacherId" value="${teacher.id}" /> <input
								type="submit" value="Delete" class="button"
								onclick="if (!(confirm('Are you sure you want to delete this teacher?'))) return false" />
						</form></td>

				</tr>
			</c:forEach>
		</table>


	</div>

	<p>
		<a href="departments">Back to list of all departments</a>
	</p>

</body>
</html>