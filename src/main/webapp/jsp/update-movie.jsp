<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Update movie</h1>
<form method="POST" action="/cerioscoop-web/UpdateMovieServlet"> 
<br>Select Movie:<br>
<input type="radio" name="movie_id" value="1">The Legend of Tarzan (2016)<br>
<input type="radio" name="movie_id" value="2">Tarzan the Ape Man (1932)<br>
<input type="radio" name="movie_id" value="3">Tarzan (1999)<br>
  <br>
  Update category:<br>
  <input type="radio" name="category" value="ACTION">Action<br>
  <input type="radio" name="category" value="COMEDY">Comedy<br>
  <input type="radio" name="category" value="KIDS">Kids<br>
  <input type="radio" name="category" value="HORROR">Horror<br>
  <br>Update title:<br>
  <input type="text" name="title">
  <br>
  Update minutes:<br>
  <input type="text" name="minutes">
  <br>
  Update movie type:
  <br>
  <input type="text" name="movie_type">
  <br>
  Update language:
  <br>
  <input type="text" name="language">
  <br>
  Update description:
  <br>
  <input type="text" name="description">
  <br><input type="submit" name="submitit" value="Submit">
</form> 
</body>
</html>