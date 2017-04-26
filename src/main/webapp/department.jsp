<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">


<title>Department</title>

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">
		<h3>Department</h3>


		<form:form action="update" method="post" modelAttribute="department">

			<form:hidden path="id" />


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
					<td><form:input path="name" /></td>
					<td><input type="submit" value="Save" class="button" />
					<td>
				</tr>

			</table>
		</form:form>







		<h3>Subjects</h3>

		<c:if test="${not empty subjectsWithoutDepartment}">

			<form action="addSubject" method="post">


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


				<tr>
					<td><a href="../subject/${subject.id}">${subject.name}</a></td>

					<td><form action="deleteSubject" method="post">

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
			<form action="addTeacher" method="post">


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


				<tr>
					<td><a href="../teacher/${teacher.id}">${teacher.name}</a></td>

					<td><form action="deleteTeacher" method="post">

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
		<a href="../departments">Back to list of all departments</a>
	</p>

</body>
</html>