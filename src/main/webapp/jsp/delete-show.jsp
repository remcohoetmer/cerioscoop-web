<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete show</title>
<link href='/cerioscoop-web/css/masterdetail.css' type='text/css'
	rel='stylesheet' />
</head>
<body>
 <div><jsp:include page="/jsp/navbar.jsp"></jsp:include></div>	
 		<div class="home-employee">
		<div id="navbar-employee">
			<jsp:include page="/jsp/navbar-employee.jsp"></jsp:include>
		</div>
		<div class="home-content-employee"></div>
	</div>
<h1>Delete show</h1>
<form method="POST" action="/cerioscoop-web/DeleteShowServlet"> 
 Show ID:<br>
<input type="text" name="show_id"><br>
<br><input type="submit" name="submitit" value="Submit">
</form>

	<jsp:include page="/jsp/footer.jsp" /> 
</body>
</html>