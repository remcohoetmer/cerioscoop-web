<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>

	<!-- Index CSS
   	================================================== -->
	<link rel="stylesheet" href="/cerioscoop-web/css/index.css">

</head>
<body>
	<jsp:include page="/jsp/navbar.jsp"></jsp:include>
 	<div class="body-footer-wrapper">
	 	<div class="login-wrapper">
	 		<jsp:include page="/jsp/login.jsp" />
	 	</div>
		<div class="footer">
			<div class="footer-center">
				<jsp:include page="/jsp/footer.jsp" />
			</div>
			
		</div>
	</div>
</body>
</html>