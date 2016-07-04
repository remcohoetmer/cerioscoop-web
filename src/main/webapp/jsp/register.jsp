<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>


</head>
<body>



<h1>Register</h1>
<form method="POST" action="/cerioscoop-web/RegisterServlet"> 
  First name:<br>
  <input type="text" name="firstname">
  <br>Last name:<br>
  <input type="text" name="lastname">
  <br>Username:<br>
  <input type="text" name="username">
  <br>Password:<br>
  <input type="text" name="password">
  <br>E-mail:<br>
  <input type="text" name="email">
  <br>
  <br><input type="submit" name="submitit" value="Submit">
</form> 
</body>
</html>