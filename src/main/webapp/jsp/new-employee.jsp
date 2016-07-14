<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Employee</title>
</head>
<body>
	<div id="navbar">
 		<jsp:include page="/jsp/navbar.jsp"></jsp:include>
 	</div>
 	
<h1>New Employee</h1>

	<div>
	<form method="POST" action="/cerioscoop-web/EmployeeServlet"> 
	  First name:<br>
	  <input type="text" name="firstname" placeholder="Enter your first name" required>
	  <br>Last name:<br>
	  <input type="text" name="lastname" placeholder="Enter your last name" required>
	  <br>Username:<br>
	  <input type="text" name="username" placeholder="Enter your username" required>
	  <br>Password:<br>
	  <input type="text" name="password" placeholder="Enter your password" required>
	  <br>E-mail:<br>
	  <input type="email" name="email" placeholder="Enter your email address" required>
	  <br>
	  <br><input type="submit" name="submitit" value="Submit">
	</form> 
	</div>
</body>
</html>