<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width = device-width, initial-scale = 1">
    <title>Test Page</title>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style>
    #container {
        width: 960px;
        margin: 0 auto;
    }
    #nav {
        height: 142px;
        background-color: #ffa500;
    }
    .jumbotron{
    background-color:#ffa500;
    color:black;
    overflow: hidden;
    text-align:center;
    white-space: nowrap;
}
    </style>
</head>
<body>
<div id="nav">
    <div id="container">
    <div class="jumbotron">
        <a href="#home" class="btn btn-danger btn-lg"> CERIOSCOOP <span class="glyphicon glyphicon-home"></span></a>
	<a href="now-showing.jsp" class="btn btn-primary btn-normal"> NOW SHOWING <span class="glyphicon glyphicon-sunglasses"></span></a>
	<a href="#about" class="btn btn-primary btn-normal"> ABOUT <span class="glyphicon glyphicon-font"></span></a>
	<a href="#team" class="btn btn-primary btn-normal"> TEAM <span class="glyphicon glyphicon-user"></span></a>
	<a href="add-showing.html" class="btn btn-primary btn-normal"> ADD SHOW <span class="glyphicon glyphicon-facetime-video"></span></a>
	<a href="add-film.html" class="btn btn-primary btn-normal"> ADD FILM <span class="glyphicon glyphicon-hd-video"></span></a>
	<a href="#" class="btn btn-primary btn-normal"> TRAILERS <span class="glyphicon glyphicon-film"></span></a>
		</div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>