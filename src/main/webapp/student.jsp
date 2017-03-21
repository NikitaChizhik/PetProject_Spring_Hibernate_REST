<%@page import="com.nikitachizhik91.university.model.Student"%>
<%@page
	import="com.nikitachizhik91.university.domain.impl.StudentManagerImpl"%>
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
		StudentManagerImpl studentManager = new StudentManagerImpl();
		Student student = studentManager.findById(Integer.parseInt(request.getParameter("id")));
	%>

	<table border="2">
		<tr>
			<td>id</td>
			<td>Name</td>

		</tr>

		<tr>
			<td><%=student.getId()%></td>
			<td><%=student.getName()%></td>

		</tr>

	</table>

</body>
</html>