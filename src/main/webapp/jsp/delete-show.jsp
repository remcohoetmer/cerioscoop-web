<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete show</title>
</head>
<body>
 	<jsp:include page="/jsp/navbar.jsp"></jsp:include>
 	
<h1>Delete show</h1>
<form method="POST" action="/cerioscoop-web/DeleteShowServlet"> 
 Show ID:<br>
<input type="text" name="show_id"><br>
<br><input type="submit" name="submitit" value="Submit">
</form>

	<jsp:include page="/jsp/footer.jsp" /> 
</body>
</html>