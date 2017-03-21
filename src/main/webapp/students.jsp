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
			<td><a href=<%=request.getContextPath() + "/student.jsp"%>><%=student.getName()%></a></td>
			<%
				session.setAttribute("" + student.getName(), student);
					pageContext.setAttribute("" + student.getName(), student, PageContext.SESSION_SCOPE);
			%>

		</tr>
		<%
			}
		%>
		<br>


	</table>

</body>
</html>

