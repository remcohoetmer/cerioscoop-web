<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	</div>
</body>
</html>