<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add-Movie</title>
</head>
<body>

	<div id="navbar">
 		<jsp:include page="/jsp/navbar.jsp"></jsp:include>
 	</div>

<h1>Add Movie</h1>

	<div>
		<form method="POST" action="../AddMovieServlet"> 
		  Title:<br>
		  <input type="text" name="moviename">
		  <br>
		  <br>Category:<br>
		  <select name="category">
		    <option value="ACTION">Action</option>
		    <option value="COMEDY">Comedy</option>
		    <option value="KIDS">Kids</option>
		    <option value="HORROR">Horror</option>
		  </select>
		  <br>
		  <br>Duration in minutes:<br>
		  <input type="text" name="minutes">
		  <br>
		  <br>Movie type:<br>
		  <input type="radio" name="movietype" value="2" checked> 2D<br>
		  <input type="radio" name="movietype" value="3"> 3D<br>
		  <br>Language:<br>
		  <select name="language">
		    <option value="English">English</option>
		    <option value="German">German</option>
		    <option value="Dutch">Dutch</option>
		    <option value="French">French</option>
		  </select>
		  <br>
		  <br>Description:<br>
		  <input type="text" name="description"/>
		  <br>
		  <br><input type="submit" value="Submit">
		</form> 
	</div>
</body>
</html>