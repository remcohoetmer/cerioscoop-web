<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete show</title>
</head>
<body>
<div id="navbar">
 		<jsp:include page="/jsp/navbar.jsp"></jsp:include>
 	</div>
<h1>Delete show</h1>
<form method="POST" action="/cerioscoop-web/DeleteShowServlet"> 
 Show ID:<br>
<input type="text" name="show_id"><br>
<br><input type="submit" name="submitit" value="Submit">
</form> 
</body>
</html>