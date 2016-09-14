<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body>

Username allready exists, please try again.
<form>
<input type="hidden" name="firstname" value=<%=session.getAttribute("firstname")%>>
<input type="hidden" name="lastname" value=<%=session.getAttribute("lastname")%>>
<input type="hidden" name="password" value=<%=session.getAttribute("password")%>>
<input type="hidden" name="email" value=<%=session.getAttribute("email")%>>
<br><a id="register-button" href="/cerioscoop-web/jsp/register.jsp" class="button">Try again</a>
		</form>

</body>
</html>