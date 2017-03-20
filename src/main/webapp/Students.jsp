<!DOCTYPE HTML>

<%@page
	import="com.nikitachizhik91.university.domain.impl.SimpleStudentManager"%>
<%@page import="com.nikitachizhik91.university.model.Student"%>

<html>
<head>
<title>name of the site above</title>
</head>

<body>

	<br>
	<br>
	<%
		SimpleStudentManager studentManager = new SimpleStudentManager();
	%>
	<br> All students:

	<table border="2">
		<%
			int i = 1;
			for (Student student : studentManager.findAll()) {
		%>
		<tr>
			<td><%=i++%></td>
			<td><%=student.getName()%></td>
		</tr>
		<%
			}
		%>

	</table>

</body>
</html>

