<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<form name="login" method="post" action="/cerioscoop-web/LoginServlet">

	<br>User Name: <input id="loginUsername" type="text" name="txtUserName"/>
	<br>Password: <input id="loginPassword" type="password" name="txtPassword"/>
	<br><button id="login" type="submit">Login</button>

</form>
</body>
</html>