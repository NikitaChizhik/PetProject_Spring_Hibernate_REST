<!DOCTYPE HTML>

<%@page import="com.nikitachizhik91.university.domain.StudentController"%>
<%@page
	import="com.nikitachizhik91.university.domain.impl.StudentControllerImpl"%>
<%@page import="com.nikitachizhik91.university.model.Student"%>

<html>
<head>
<title>name of the site above</title>
</head>

<body>
	hello on the page.
	<br>
	<br>
	<%
		StudentController studentController = new StudentControllerImpl();
	%>
	<br> All students:

	<table border="2">
		<%
			int i = 1;
			for (Student student : studentController.findAll()) {
		%>
		<tr>
			<td>Student<%=i++%></td>
			<td>name=<%=student.getName()%></td>
			<td>id=<%=student.getId()%></td>
		</tr>
		<%
			}
		%>

	</table>

</body>
</html>

