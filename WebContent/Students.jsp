<!DOCTYPE HTML>
<%@page import="com.nikitachizhik91.university.domain.StudentDomain"%>
<%@page import="com.nikitachizhik91.university.dao.impl.StudentDaoImpl"%>
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
		int i = 1;
		StudentDomain studentDomain = new StudentDomain();
	%>
	<br> All students:

	<table border="2">
		<%
			for (Student student : studentDomain.findAll()) {
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

