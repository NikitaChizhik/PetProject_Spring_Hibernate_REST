<!DOCTYPE HTML>

<%@page import="com.nikitachizhik91.university.domain.StudentBusiness"%>
<%@page
	import="com.nikitachizhik91.university.domain.impl.StudentBusinessImpl"%>
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
		StudentBusiness studentDomain = new StudentBusinessImpl();
	%>
	<br> All students:

	<table border="2">
		<%
			int i = 1;
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

