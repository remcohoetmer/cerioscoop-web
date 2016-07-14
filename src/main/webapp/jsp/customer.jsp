<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="nl.cerios.cerioscoop.domain.Customer"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello customer!</title>
</head>
<body>
	<div id="navbar">
 	<jsp:include page="/jsp/navbar.jsp"></jsp:include>
 	</div>

	<div id="helloCustomer">	
	<h3>Login Successful!</h3>
		<% Customer customer = (Customer) session.getAttribute("user"); %>
		Hello <%=customer.getUsername()%>!
	</div> 
	
	<div id="navbar-customer">
 	<jsp:include page="/jsp/navbar-customer.jsp"></jsp:include>
 	</div>
</body>
</html>