<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Show</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.4/jquery.timepicker.min.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
 $(function() {
 $( "#datepicker" ).datepicker();});
</script>

		<link href='/cerioscoop-web/css/masterdetail.css' type='text/css' rel='stylesheet' />

</head>
<body>
 <div><jsp:include page="/jsp/navbar.jsp"></jsp:include></div>	
		<div class="home-employee">
		<div id="navbar-employee">
			<jsp:include page="/jsp/navbar-employee.jsp"></jsp:include>
		</div>
		<div class="home-content-employee"></div>
	</div>
<h1>Add show</h1>

	<div>
		<form name="add-showing" method="post" action="/cerioscoop-web/AddShowServlet">
		Select movie:<br>
		<select name="movie_id">
					<c:forEach items="${currentMovies}" var="currentMovies">
							<option value="${currentMovies.movieId}">${currentMovies.title}</option>
						</c:forEach>
						</select><br>
		<br>Room name:<br>
		<input type="radio" name="room_id" value="1"> Red room<br>
		<input type="radio" name="room_id" value="2"> Blue room<br>
		<br>Premiere date:<br>
		<input type="text" id="datepicker" name="premieredate"><br>
		<br>Time:<br>
		<input type="time" name="show_time"><br>
		<input type="submit" name="submitit" value="Submit"/>
		<input type="reset" value="cancel" />
		</form>
	</div>
	<div>
	
	<table>
<thead><th>Movietitle</th><th>plays on</th><th>time</th><th>room</th></thead>
<tbody>

<c:forEach items="${showing}" var="show">
  
<tr>
	<td>${show.movieTitle}</td>
	<td>${show.showingDate}</td>
	<td>${show.showingTime}</td>
	<td>${show.roomName}</td>
</tr>

</c:forEach>

</tbody>
</table>

	</div>
	<jsp:include page="/jsp/footer.jsp" />
</body>
</html>