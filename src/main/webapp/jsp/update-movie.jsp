<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

		<link href='/cerioscoop-web/css/masterdetail.css' type='text/css' rel='stylesheet' />

</head>
<body>
<div id="navbar">
 		<jsp:include page="/jsp/navbar.jsp"></jsp:include>
 	</div>
<h1>Update movie</h1>
<form method="POST" action="/cerioscoop-web/UpdateMovieServlet"> 
<br>Select Movie:<br>
 <input type="text" name="movie_id" required><br>
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
  <br>Trailer:<br>
  <input type="text" name="trailer">
  <br>
  <br><input type="submit" name="submitit" value="Submit">
</form> 

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