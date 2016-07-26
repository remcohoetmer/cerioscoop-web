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
<title>Now Showing</title>

	<!-- Cerioscoop CSS
   ================================================== -->
	<link href='/cerioscoop-web/css/now-showing.css' type='text/css' rel='stylesheet' />
	
</head>


<body>
	<div id="navbar">
 		<jsp:include page="/jsp/navbar.jsp"></jsp:include>
 	</div>

<h1>Now Showing</h1>

 
<table>
<thead><th>Movietitle</th><th>plays on</th><th>time</th><th>room</th></thead>
<tbody>

<c:forEach items="${shows}" var="show">
  
<tr>
	<td>${show.movieTitle}</td>
	<td>${show.showingListDate}</td>
	<td>${show.showingListTime}</td>
	<td>${show.roomName}</td>
</tr>

</c:forEach>

</tbody>
</table>
<p>Today it is ${todays_date}
<br />The first upcoming film: ${first_upcoming_movie}
<br />That's in ${countdown}
</p>
</body>
</html>