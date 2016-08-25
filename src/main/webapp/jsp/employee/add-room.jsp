<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link href='/cerioscoop-web/css/masterdetail.css' type='text/css' rel='stylesheet' />
<link href='/cerioscoop-web/css/shared.css' type='text/css' rel='stylesheet' />
</head>
<body>
 		<div><jsp:include page="/jsp/shared/navbar.jsp"></jsp:include></div>
		<div class="home-employee">
		<div id="navbar-employee">
			<jsp:include page="/jsp/employee/navbar-employee.jsp"></jsp:include>
		</div>
		<div class="home-content-employee"></div>
	</div>
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

	<jsp:include page="/jsp/shared/footer.jsp" />
</body> 
</html>