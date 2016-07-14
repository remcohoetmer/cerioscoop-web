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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<thead><th>Movietitle</th><th>plays on:</th><th>time</th></thead>
<tbody>

<c:forEach items="${shows}" var="show">
  

<%-- 
if (item.getShowDate().after(dateUtils.getCurrentDate())){
--%>
<tr>
	<%-- <td>${show.movie.title}</td>
	<td><%=generalService.getMovieByMovieId(item.getMovieId()).getTitle()%></td>
	--%>
	<td>${show.movieId}</td>
	<td>${show.showDate}</td>
	<td>${show.showTime}</td>

</tr>
<%-- } --%>

</c:forEach>

</tbody>
</table>


</body>
</html>