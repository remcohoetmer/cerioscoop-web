<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Show</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
 $(function() {
 $( "#datepicker" ).datepicker();});
</script>
<script>
$(document).ready(function(){
            $('input.timepicker').timepicker({});
        });
</script>

</head>
<body>

    <div id="navbar">
         <jsp:include page="/jsp/navbar.jsp"></jsp:include>
     </div>

<h1>Update show</h1>

    <div>
        <form name="update-showing" method="post" action="/cerioscoop-web/UpdateShowServlet">
        Show ID:<br>
        <input type="text" name="show_id"><br>
        Movie ID:<br>
        <input type="text" name="movie_id"><br>
        <br>Room ID:<br>
        <input type="radio" name="room_id" value="1"> Red room<br>
        <input type="radio" name="room_id" value="2"> Blue room<br>
        <br>Premiere date:<br>
        <input type="text" id="datepicker" name="premieredate"><br>
        <br>Time:<br>
        <input type="text" id="timepicker" name="lastshowingdate"><br>
        <input type="submit" name="submitit" value="Submit"/>
        <input type="reset" value="cancel" />
        </form>
     </div>
</body>
</html>
