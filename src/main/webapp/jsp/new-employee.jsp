<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Employee</title>
<link href='/cerioscoop-web/css/masterdetail.css' type='text/css'
	rel='stylesheet' />
</head>
<body>
	<div id="navbar">
 		<jsp:include page="/jsp/navbar.jsp"></jsp:include>
 	</div>
 	 		<div class="home-employee">
		<div id="navbar-employee">
			<jsp:include page="/jsp/navbar-employee.jsp"></jsp:include>
		</div>
		<div class="home-content-employee"></div>
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
	
	<jsp:include page="/jsp/footer.jsp" />
</body>
</html>