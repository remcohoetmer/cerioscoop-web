<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Navigation</title>

<!-- Navigation CSS
   	================================================== -->
<link rel="stylesheet" href="/cerioscoop-web/css/navbar.css">
<link href='/cerioscoop-web/css/shared.css' type='text/css'
	rel='stylesheet' />

</head>
<body>

	<div class="navbar">
		<div class="navbar-buttons-wrapper">
			<div class="navbar-button">
				<a href="/cerioscoop-web/index.jsp"> <span
					class="navbar-button-text">CERIOSCOOP</span>
				</a>
			</div>

				<div class="navbar-button">
				<a href="/cerioscoop-web/RegisterServlet"> <span
					class="navbar-button-text">REGISTER</span>
				</a>
			</div>

			<div class="navbar-button">
				<a id="navbar-login" href="#Login"> <span
					class="navbar-button-text">LOGIN</span>
				</a>
				<div class="login-menu">
					<jsp:include page="/jsp/login.jsp" />
				</div>
			</div>
				<div class="navbar-button">
				<a href="/cerioscoop-web/LogOutServlet"> <span
					class="navbar-button-text">LOGOUT</span>
				</a>
			</div>
		           
		
		</div>
		
	</div>

</body>
</html>