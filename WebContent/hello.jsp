<!DOCTYPE HTML>
<%@page import="java.util.Date"%>
<%@page import="com.nikitachizhik91.university.dao.impl.StudentDaoImpl"%>
<html>
<head>
<title>name of the site above</title>
</head>

<body>
	hello on the page.
	<br>
	<br> Today's date is:<%=new Date()%>
	<!-- <%=new Date()%> -->
	<br>
	<%
		out.print("print out");
	%>
	<br>
	<%
		StudentDaoImpl studentDao = new StudentDaoImpl();
	%>
	<br> All students:
	<%=studentDao.findAll()%>

</body>
</html>

