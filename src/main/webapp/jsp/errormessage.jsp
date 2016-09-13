<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body>
	<div id="navbar">
		<jsp:include page="/jsp/shared/navbar.jsp"></jsp:include>
	</div>
<%=request.getAttribute("errorMessage")%>
<form method="post" action="/cerioscoop-web/ConserveFormDataServlet">
<input type="hidden" name="firstname" value=<%=request.getAttribute("firstname")%>>
<input type="hidden" name="lastname" value=<%=request.getAttribute("lastname")%>>
<input type="hidden" name="password" value=<%=request.getAttribute("password")%>>
<input type="hidden" name="email" value=<%=request.getAttribute("email")%>>
<br><button type="submit">Try again</button>
		</form>
</body>
</html>