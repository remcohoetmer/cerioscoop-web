<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="nl.cerios.cerioscoop.domain.Employee"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello employee!</title>
</head>
<body>
	<div id="navbar">
 		<jsp:include page="/jsp/navbar.jsp"></jsp:include>
 	</div>

	<div>
		<h3>Login Successful!</h3>
		<% Employee employee = (Employee) session.getAttribute("user"); %>
		Hello <%=employee.getUsername()%>!
	</div>

	<div id="navbar-employee">
 		<jsp:include page="/jsp/navbar-employee.jsp"></jsp:include>
 	</div>
 	
 	<div id="footer">
		<jsp:include page="/jsp/footer.jsp" />
	</div>
 	
</body>
</html>