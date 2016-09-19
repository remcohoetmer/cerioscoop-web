<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="nl.cerios.cerioscoop.domain.ErrorMessage"%>
<!DOCTYPE html>
<html>
<head>
<style>
p {color:red;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<div id="navbar">
		<jsp:include page="/jsp/shared/navbar.jsp"></jsp:include>
	</div>

	<h1>Register</h1>

		
	<div>
		<form method="POST" action="/cerioscoop-web/RegisterServlet">
			First name:<br> <input id="firstname" type="text"
				name="firstname" placeholder="Enter your first name" value=<%=session.getAttribute("firstname")%>> 
				<br>Last name:<br> <input id="lastname" type="text" name="lastname" placeholder="Enter your last name"
				value=<%=session.getAttribute("lastname")%>>
				<br>Username:<br>
			<input id="username" type="text" name="username"
				placeholder="Enter your username">
				<br>Password:<br>
			<input id="password" type="password" name="password"
				placeholder="Enter your password" value=<%=session.getAttribute("password")%>> <br>Password:<br>
			<input id="password2" type="password" name="password2"
				placeholder="Enter your password" value=<%=session.getAttribute("password")%>>
				 <br>E-mail:<br>
			<input id="email" type="text" name="email"
				placeholder="Enter your email address" value=<%=session.getAttribute("email")%>><br>
				 <br> <br>
			<input id="submit" type="submit" name="submitit" value="Register">
		</form>
		<form action="/cerioscoop-web">
			<input type="submit" value="Cancel" />
		</form>
	</div>
	
	<div>
	<p>
	${errorMessage.firstnameError}<br>
	${errorMessage.lastnameError}<br>
	${errorMessage.usernameError}<br>
	${errorMessage.passwordError}<br>
	${errorMessage.emailError}<br>
	<%request.getAttribute("errorMessage");%>
	<%if(request.getAttribute("errorMessage") !=null) { %>
		<%	ErrorMessage errorMessage = (ErrorMessage) request.getAttribute("errorMessage"); %>
		
		<%if(errorMessage.getFirstnameError() !=null) { errorMessage.getFirstnameError(); } %>
			<%	if(errorMessage.getLastnameError() !=null) { errorMessage.getLastnameError(); } %>
				<%	if(errorMessage.getLastnameError() !=null) { errorMessage.getLastnameError(); } %>
					<%	if(errorMessage.getUsernameError() !=null) { errorMessage.getUsernameError(); } %>
							<%if(errorMessage.getPasswordError() !=null) { errorMessage.getPasswordError(); }%>
					<%} %>
	</p>

		</div>
				
</body>
</html>