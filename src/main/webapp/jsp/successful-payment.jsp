<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="navbar">
		<jsp:include page="/jsp/navbar.jsp"></jsp:include>
	</div>
	<h1>Thank you for purchasing ${movieTitle}!</h1>
	<h2>We hope seeing you back again soon!</h2>
		<jsp:include page="/jsp/footer.jsp" />
</body>
</html>