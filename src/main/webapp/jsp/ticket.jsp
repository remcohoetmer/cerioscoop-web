<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href='/cerioscoop-web/css/now-showing.css' type='text/css'
	rel='stylesheet' />
</head>
<body>
	<div id="navbar">
		<jsp:include page="/jsp/navbar.jsp"></jsp:include>
	</div>
	<h1>Ticket</h1>
	<table>
		<thead>
			<th>Movietitle</th>
			<th>plays on</th>
			<th>time</th>
			<th>room</th>
			<th>available chairs</th>
			<th>ticket amount</th>

		</thead>
		<tbody>

			<tr>
				<td>${movieTitle}</td>
				<td>${showingDate}</td>
				<td>${showingTime}</td>
				<td>${roomName}</td>
				<td>${chairAmount}</td>
				<td>
					<form method="POST" action="PaymentServlet">
						<input type="hidden" name="showid" value=${showingId } /> 
						<input type="hidden" name="chairAmount" value=${chairAmount } /> 
						<input type="hidden" name="movieTitle" value="${fn:escapeXml(movieTitle)}">
						<select name="ticketamount">
							<option value="1">1 Ticket</option>
							<option value="2">2 Tickets</option>
							<option value="3">3 Tickets</option>
							<option value="4">4 Tickets</option>
							<option value="5">5 Tickets</option>
						</select> <input type="submit" name="submitit" value="Buy" />
					</form>
				</td>

			</tr>



		</tbody>
	</table>
	<jsp:include page="/jsp/footer.jsp" />
</body>
</html>