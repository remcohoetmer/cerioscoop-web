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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Now Showing</title>

<!-- Cerioscoop CSS
   ================================================== -->
<link href='/cerioscoop-web/css/now-showing.css' type='text/css'rel='stylesheet' />
<link href='/cerioscoop-web/css/shared.css' type='text/css' rel='stylesheet' />

</head>


<body>
	<div id="navbar">
		<jsp:include page="/jsp/shared/navbar.jsp"></jsp:include>
	</div>

	<h1>Now Showing</h1>


	<table>
		<thead>
			<th>ShowId</th>
			<th>Movietitle</th>
			<th>plays on</th>
			<th>time</th>
			<th>room</th>
			<th>chairs</th>
			<th>trailer</th>
			<th>Buy Ticket</th>

		</thead>
		<tbody>

			<c:forEach items="${nowShowing}" var="show">

				<tr>
					<td>${show.showingId}</td>
					<td>${show.movieTitle}</td>
					<td>${show.showingDate}</td>
					<td>${show.showingTime}</td>
					<td>${show.roomName}</td>
					<td>${show.chairAmount}</td>
					<td><a class="button" href="${show.trailer}">trailer</a></td>
					<td>
						<form method="post" action="TicketServlet">
							<input type="hidden" name="showid" value=${show.showingId} />
							<input type="submit" value="Buy">
						</form>
					</td>
				</tr>

			</c:forEach>

		</tbody>
	</table>
	<p>
		Today it is ${todays_date} <br />The first upcoming film:
		${first_upcoming_movie} <br />That's in ${countdown}
	</p>

</body>
</html>