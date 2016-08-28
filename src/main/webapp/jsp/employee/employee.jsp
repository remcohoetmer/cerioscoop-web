
<%@page import="nl.cerios.cerioscoop.domain.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="nl.cerios.cerioscoop.domain.Employee"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<c:if test="${usertype ne 'employee'}">
	<c:redirect url="/"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello employee!</title>

	<!-- Employee CSS
   	================================================== -->
	<link rel="stylesheet" href="/cerioscoop-web/css/employee.css">
	<link href='/cerioscoop-web/css/shared.css' type='text/css' rel='stylesheet' />

</head>
<body>
 	<div class="body-navbar-wrapper">
		<div class="navbar">
	 	<jsp:include page="/jsp/shared/navbar.jsp"></jsp:include>
		</div>
	</div>
	
	<div class="home-employee">
		<div id="navbar-employee">
	 		<jsp:include page="/jsp/employee/navbar-employee.jsp"></jsp:include>
	 	</div>
		<div class="home-content-employee">	
			<h3>Login Successful!</h3>
			
			<% User employee = (User) session.getAttribute("user"); %>
			Hello <%=employee.getUsername()%> (${usertype})!
		</div>
	</div>

 	<div class="body-footer-wrapper">
		<div class="footer">
			<jsp:include page="/jsp/shared/footer.jsp" />		
		</div>
	</div>
</body>
</html>