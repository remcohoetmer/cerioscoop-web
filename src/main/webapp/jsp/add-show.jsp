<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

</head>
<body>

	<div id="navbar">
 		<jsp:include page="/jsp/navbar.jsp"></jsp:include>
 	</div>

<h1>Add show</h1>

	<div>
		<form name="add-showing" method="post" action="/cerioscoop-web/AddShowServlet">
		Movie ID:<br>
		<input type="text" name="movieID"><br>
		<br>Room name:<br>
		<input type="radio" name="roomID" value="1"> Red room<br>
		<input type="radio" name="roomID" value="2"> Blue room<br>
		<br>Premiere date:<br>
		<input type="text" id="datepicker" name="premieredate"><br>
		<br>Time:<br>
		<input type="time" name="show_time"><br>
		<input type="submit" name="submitit" value="Submit"/>
		<input type="reset" value="cancel" />
		</form>
	</div>
</body>
</html>