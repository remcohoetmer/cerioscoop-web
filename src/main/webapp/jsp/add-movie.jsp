<%@page import="javax.naming.InitialContext"%>
<%@page import="nl.cerios.cerioscoop.domain.Movie"%>
<%@page import="java.util.Comparator"%>
<%@page import="nl.cerios.cerioscoop.web.ShowException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.List"%>
<%@page import="nl.cerios.cerioscoop.domain.Show"%>
<%@page import="nl.cerios.cerioscoop.service.GeneralService"%>
<%@page import="nl.cerios.cerioscoop.util.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add-Movie</title>
</head>
<body>
 		<jsp:include page="/jsp/navbar.jsp"></jsp:include>


<h1>Add Movie</h1>

	<div>
		<form method="POST" action="AddMovieServlet"> 
		  Title:<br>
		  <input type="text" name="moviename" required>
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
		  <input type="text" name="minutes" required>
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
		  <input type="text" name="description" required>
		  <br>
		  <br>Trailer:<br>
		  <input type="text" name="trailer" required>
		  <br>
		  <br><input type="submit" value="Submit">
		</form> 
	</div>
	<table>
<thead><th>MovieId</th><th>Category</th><th>Title</th><th>Duration</th><th>Type</th><th>Language</th><th>Description</th><th>Trailer</th></thead>
<tbody>

<c:forEach items="${currentMovies}" var="currentMovies">
  
<tr>
	<td>${currentMovies.movieId}</td>
	<td>${currentMovies.category}</td>
	<td>${currentMovies.title}</td>
	<td>${currentMovies.minutes}</td>
	<td>${currentMovies.movieType}</td>
	<td>${currentMovies.language}</td>
	<td>${currentMovies.description}</td>
	<td>${currentMovies.trailer}</td>
	</tr>

</c:forEach>

</tbody>
</table>
	
	<jsp:include page="/jsp/footer.jsp" />
</body>
</html>