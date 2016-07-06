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
  Movie id:<br>
  <input type="text" name="movie_id">
  <br>
  Update category:<br>
  <input type="text" name="category">
  <br>
  Update title:<br>
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