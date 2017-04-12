<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Room</title>
<link type="text/css" rel="stylesheet" href="css/style.css">

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">
		<h3>Room</h3>


		<form action="room" method="post">
			<input type="hidden" name="roomId" value="${room.id}" />

			<table>
				<tr>
					<th>id</th>
					<th>Number</th>
					<th>Update</th>
					<th>Save</th>

				</tr>

				<tr>
					<td>${room.id}</td>
					<td>${room.number}</td>
					<td><input type="text" name="number" value="${room.number}" /></td>
					<td><input type="submit" value="Save"
						class="button" />
					<td>
				</tr>



			</table>

		</form>
	</div>

	<p>
		<a href="rooms">Back to list of all rooms</a>
	</p>
	
</body>

</html>