<%@page import="com.nikitachizhik91.university.model.Student"%>
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
<body>
	<%
		SimpleGroupManager groupManager = new SimpleGroupManager();
	%>

	<table border="2">
		<tr>
			<td>id</td>
			<td>Name</td>

		</tr>
		<%
			int i = 1;
			Group group = groupManager.findAll().get(0);

			Student student = group.getStudents().get(0);
		%>
		<tr>
			<td><%=i++%></td>
			<td><%=student.getName()%>
			</td>
		</tr>
	</table>

</body>
</html>