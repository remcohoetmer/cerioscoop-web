<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Show</title>
<link href='/cerioscoop-web/css/masterdetail.css' type='text/css'
	rel='stylesheet' />
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.4/jquery.timepicker.min.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
 $(function() {
 $( "#datepicker" ).datepicker();});
</script>


</head>
<body>

    <div id="navbar">
         <jsp:include page="/jsp/navbar.jsp"></jsp:include>
     </div>
	<div class="home-employee">
		<div id="navbar-employee">
			<jsp:include page="/jsp/navbar-employee.jsp"></jsp:include>
		</div>
		<div class="home-content-employee"></div>
	</div>
<h1>Update show</h1>

    <div>
        <form name="update-showing" method="post" action="/cerioscoop-web/UpdateShowServlet">
        Show ID:<br>
        <input type="text" name="show_id" required><br>
        <br>Movie:<br>
        <input type="radio" name="movie_id" value="1">The Legend of Tarzan (2016)<br>
	    <input type="radio" name="movie_id" value="2">Tarzan the Ape Man (1932)<br>
	    <input type="radio" name="movie_id" value="3">Tarzan (1999)<br>
        <br>Room Type:<br>
        <input type="radio" name="room_id" value="2"> 2D<br>
	    <input type="radio" name="room_id" value="3"> 3D<br>
        <br>Premiere date:<br>
        <input type="text" id="datepicker" name="premieredate" required><br>
        <br>Time:<br>
        <input type="time" name="lastshowingdate" required><br>
        <input type="submit" name="submitit" value="Submit"/>
        <input type="reset" value="cancel" />
        </form>
     </div>
     
     <jsp:include page="/jsp/footer.jsp" />
</body>
</html>
