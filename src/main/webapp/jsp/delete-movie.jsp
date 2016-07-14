<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="navbar">
 		<jsp:include page="/jsp/navbar.jsp"></jsp:include>
 	</div>
<h1>Delete movie</h1>
<form method="POST" action="/cerioscoop-web/DeleteMovieServlet"> 
<br>Movie:<br>
<input type="radio" name="movie_id" value="1">The Legend of Tarzan (2016)<br>
<input type="radio" name="movie_id" value="2">Tarzan the Ape Man (1932)<br>
<input type="radio" name="movie_id" value="3">Tarzan (1999)<br>
<br><input type="submit" name="submitit" value="Submit">
</form> 
</body>
</html>