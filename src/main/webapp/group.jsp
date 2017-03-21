<%@page import="java.util.List"%>
<%@page import="com.nikitachizhik91.university.model.Student"%>
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
<body>
	<%
		GroupManagerImpl groupManager = new GroupManagerImpl();
		Group group = groupManager.findById(Integer.parseInt(request.getParameter("id")));

		List<Student> students = group.getStudents();
	%>

	Group name :
	<%=group.getName()%>
	<table border="2">
		<tr>
			<td>Order</td>
			<td>Name</td>
		</tr>

		<%
			int i = 1;
			for (Student student : students) {
		%>
		<tr>
			<td><%=i++%></td>
			<td><a href="student.jsp?id=<%=student.getId()%>"><%=student.getName()%></a></td>

		</tr>
		<%
			}
		%>

	</table>

</body>
</html>