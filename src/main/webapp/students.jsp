<!DOCTYPE HTML>

<%@page
	import="com.nikitachizhik91.university.domain.impl.StudentManagerImpl"%>
<%@page import="com.nikitachizhik91.university.model.Student"%>

<html>
<head>
<title>name of the site above</title>
</head>

<body>

	<br>
	<br>
	<%
		StudentManagerImpl studentManager = new StudentManagerImpl();
	%>
	<br> All students:

	<table border="2">
		<tr>
			<td>Order</td>
			<td>Name</td>

		</tr>
		<%
			int i = 1;
			for (Student student : studentManager.findAll()) {
		%>
		<tr>
			<td><%=i++%></td>
			<td><a href="student.jsp?id=<%=student.getId()%>"><%=student.getName()%></a></td>

		</tr>
		<%
			}
		%>
		<br>


	</table>

</body>
</html>

