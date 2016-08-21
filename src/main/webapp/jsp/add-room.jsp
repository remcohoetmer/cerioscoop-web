<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
 		<jsp:include page="/jsp/navbar.jsp"></jsp:include>

<h1>Add Room</h1>
	
	<div>
	<form method="POST" action="/cerioscoop-web/AddRoomServlet"> 
	  Room name:<br>
	  <input type="text" name="name">
	  <br>
	  <br>Chair amount:<br>
	  <input type="text" name="chair_amount">
	  <br>
	  <br>Room type 2D of 3D:<br>
	  <input type="radio" name="room_type" value="2"> 2D<br>
	  <input type="radio" name="room_type" value="3"> 3D<br>
	  <br><input type="submit" name="submitit" value="Submit">
	</form> 
	</div>
	
	<jsp:include page="/jsp/footer.jsp" />
</body> 
</html>