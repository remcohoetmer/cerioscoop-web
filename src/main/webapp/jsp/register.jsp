<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<div id="navbar">
 		<jsp:include page="/jsp/navbar.jsp"></jsp:include>
 	</div>
 	<a href="/cerioscoop-web/RegisterServlet">Klik hier</a>
<h1>Register</h1>

	<div>
	<form method="POST" action="/cerioscoop-web/RegisterServlet"> 
	  First name:<br>
	  <input id="firstname" type="text" name="firstname" placeholder="Enter your first name" required>
	  <br>Last name:<br>
	  <input id="lastname" type="text" name="lastname" placeholder="Enter your last name" required>
	  <br>Username:<br>
	  <input id="username" type="text" name="username" placeholder="Enter your username" required>
	  <br>Password:<br>
	  <input id="password" type="text" name="password" placeholder="Enter your password" required>
	  <br>E-mail:<br>
	  <input id="email" type="email" name="email" placeholder="Enter your email address" required>
	  <br>
	  <br><input id="submit" type="submit" name="submitit" value="Submit">
	</form> 
	</div>
</body>
</html>