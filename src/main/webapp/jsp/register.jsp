<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>

<h1>Register</h1>

	<div>
	<form method="POST" action="/cerioscoop-web/RegisterServlet"> 
	  First name:<br>
	  <input id="firstname" type="text" name="firstname" placeholder="Enter your first name">
	  <br>Last name:<br>
	  <input id="lastname" type="text" name="lastname" placeholder="Enter your last name">
	  <br>Username:<br>
	  <input id="username" type="text" name="username" placeholder="Enter your username">
	  <br>Password:<br>
	  <input id="password" type="text" name="password" placeholder="Enter your password">
	  <br>Password:<br>
	  <input id="password" type="text" name="password2" placeholder="Enter your password">
	  <br>E-mail:<br>
	  <input id="email" type="text" name="email" placeholder="Enter your email address">
	  <br>
	  <br><input id="submit" type="submit" name="submitit" value="Login">
	</form> 
	</div>
	

</body>
</html>