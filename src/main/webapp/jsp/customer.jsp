<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="nl.cerios.cerioscoop.domain.Customer"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello customer!</title>

	<!-- Customer CSS
   	================================================== -->
	<link rel="stylesheet" href="/cerioscoop-web/css/customer.css">


</head>
<body>
 	<div class="body-navbar-wrapper">
		<div class="navbar">
	 	<jsp:include page="/jsp/navbar.jsp"></jsp:include>
		</div>
	</div>

 	<div class="login-message">
	<h3>Login Successful!</h3>
		<% Customer customer = (Customer) session.getAttribute("user"); %>
		Hello <%=customer.getUsername()%>!
	</div> 
	<div id="navbar-customer">
 		<jsp:include page="/jsp/navbar-customer.jsp"></jsp:include>
 	</div>

 	
 	<div class="body-footer-wrapper">
		<div class="footer">
			<jsp:include page="/jsp/footer.jsp" />		
		</div>
	</div>
</body>
</html>