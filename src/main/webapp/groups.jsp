<%@page import="com.nikitachizhik91.university.model.Group"%>
<%@page
	import="com.nikitachizhik91.university.domain.impl.GroupManagerImpl"%>
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
		GroupManagerImpl groupManager = new GroupManagerImpl();
	%>

	<table border="2">
		<tr>
			<td>Order</td>
			<td>Name</td>
			<td>Amount of students</td>
			<td>id</td>
		</tr>
		<%
			int i = 1;
			for (Group group : groupManager.findAll()) {
		%>
		<tr>
			<td><%=i++%></td>
			<td><a href="group.jsp?id=<%=group.getId()%>"><%=group.getName()%></a></td>

			<td><%=group.getStudents().size()%></td>
			<td><%=group.getId()%></td>
		</tr>
		<%
			}
		%>

	</table>
</body>
</html>