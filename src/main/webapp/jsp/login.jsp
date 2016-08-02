<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

	<!-- Login CSS
   	================================================== -->
	<link rel="stylesheet" href="/cerioscoop-web/css/login.css">
	
</head>
<body>
<form name="login" method="post" action="/cerioscoop-web/LoginServlet">

	<br>User Name: 
	<br><input id="loginUsername" type="text" name="txtUserName"/>
	<br>Password: 
	<br><input id="loginPassword" type="password" name="txtPassword"/>
	<br><button id="login" type="submit">Login</button>

</form>
</body>
</html>