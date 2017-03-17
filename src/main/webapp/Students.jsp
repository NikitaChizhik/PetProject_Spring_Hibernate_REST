<!DOCTYPE HTML>

<%@page import="com.nikitachizhik91.university.domain.StudentManager"%>
<%@page
	import="com.nikitachizhik91.university.domain.impl.SimpleStudentManager"%>
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
		StudentManager studentManager = new SimpleStudentManager();
	%>
	<br> All students:

	<table border="2">
		<%
			int i = 1;
			for (Student student : studentManager.findAll()) {
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

