<%@page import="com.nikitachizhik91.university.model.Group"%>
<%@page
	import="com.nikitachizhik91.university.domain.impl.SimpleGroupManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<h2>All Groups</h2>
<body>
	<br>

	<%
		SimpleGroupManager groupManager = new SimpleGroupManager();
	%>

	<table border="2">
		<tr>
			<td>Order</td>
			<td>Name</td>
			<td>Ammount of students</td>
		</tr>
		<%
			int i = 1;
			for (Group group : groupManager.findAll()) {
		%>
		<tr>
			<td><%=i++%></td>
			<td><a href="http://localhost:8080/university/group.jsp"><%=group.getName()%></a></td>

			<td><%=group.getStudents().size()%></td>
		</tr>
		<%
			}
		%>

	</table>
</body>
</html>