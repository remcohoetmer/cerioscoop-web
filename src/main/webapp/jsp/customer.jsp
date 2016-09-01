<%@page import="nl.cerios.cerioscoop.domain.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="nl.cerios.cerioscoop.domain.Customer"%> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<c:if test="${usertype ne 'customer'}">
	<c:redirect url="/"/>
</c:if> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello customer!</title>

	<!-- Customer CSS
   	================================================== -->
	<link rel="stylesheet" href="/cerioscoop-web/css/customer.css">
	<link href='/cerioscoop-web/css/shared.css' type='text/css' rel='stylesheet' />


</head>
<body>
 	<div class="body-navbar-wrapper">
		<div class="navbar">
	 	<jsp:include page="/jsp/shared/navbar.jsp"></jsp:include>
		</div>
	</div>

	<div id="navbar-customer">
 		<jsp:include page="/jsp/navbar-customer.jsp"></jsp:include>
 	</div>
	<div class="login-message">
	<h3>Login Successful!</h3>
		<% User customer = (User) session.getAttribute("user"); %>
		Hello <%=customer.getUsername()%> (${usertype})!<br>
	</div> 
 	
 	<div class="body-footer-wrapper">
		<div class="footer">
			<jsp:include page="/jsp/shared/footer.jsp" />		
		</div>
	</div>
</body>
</html>