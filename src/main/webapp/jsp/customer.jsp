<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="nl.cerios.cerioscoop.domain.Customer"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
</head>
<body>
	<div id="navbar">
 	<jsp:include page="/jsp/navbar.jsp"></jsp:include>
 	</div>

	<div>	
	<h3>Login Successful!</h3>
		<% Customer customer = (Customer) session.getAttribute("user"); %>
		Hello <%=customer.getUsername()%>!
	</div> 
	
	<div id="navbar-customer">
 	<jsp:include page="/jsp/navbar-customer.jsp"></jsp:include>
 	</div>
</body>
</html>