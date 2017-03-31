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

			<form action="departmentSubject" method="post">


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


			<c:forEach var="subject" items="${subjectsOfDepartment}">

				<c:url var="subjectLink" value="subject">
					<c:param name="subjectId" value="${subject.id}" />
				</c:url>

				<c:url var="deleteSubjectLink" value="departmentSubject">
					<c:param name="subjectId" value="${subject.id}" />
					<c:param name="departmentId" value="${department.id}" />
				</c:url>

				<tr>
					<td><a href="${subjectLink}">${subject.name}</a></td>
					<td><a href="${deleteSubjectLink}"
						onclick="if (!(confirm('Are you sure you want to delete this subject?'))) return false">
							Delete</a></td>
				</tr>
			</c:forEach>
		</table>







		<h3>Teachers</h3>



		<form action="departmentTeacher" method="post">


			<select name="teacherId" class="button">
				<c:forEach var="teacher" items="${teachers}">
					<option value="${teacher.id}">${teacher.name}</option>
				</c:forEach>
			</select> <input type="hidden" name="departmentId" value="${department.id }" />
			<input type="submit" value="Add Teacher" class="button" />

		</form>


		<table>

			<tr>
				<th>Teachers</th>
				<th>Delete</th>

			</tr>


			<c:forEach var="teacher" items="${teachersOfDepartment}">

				<c:url var="teacherLink" value="teacher">
					<c:param name="subjectId" value="${subject.id}" />
				</c:url>

				<c:url var="deleteTeacherLink" value="departmentTeacher">
					<c:param name="teacherId" value="${teacher.id}" />
					<c:param name="departmentId" value="${department.id}" />
				</c:url>

				<tr>
					<td><a href="${teacherLink}">${teacher.name}</a></td>
					<td><a href="${deleteTeacherLink}"
						onclick="if (!(confirm('Are you sure you want to delete this teacher	?'))) return false">
							Delete</a></td>
				</tr>
			</c:forEach>
		</table>


	</div>

	<p>
		<a href="departments">Back to list of all departments</a>
	</p>

</body>
</html>