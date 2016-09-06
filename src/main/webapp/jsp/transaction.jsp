<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='/cerioscoop-web/css/shared.css' type='text/css' rel='stylesheet' />
<title>Insert title here</title>
<link href='/cerioscoop-web/css/now-showing.css' type='text/css'
	rel='stylesheet' />
</head>
<body>
	<div id="navbar">
		<jsp:include page="/jsp/shared/navbar.jsp"></jsp:include>
	</div>
   <h1>Ticket</h1>
	<table>
		<thead>
			<th>Movietitle</th>
			<th>plays on</th>
			<th>time</th>
			<th>room</th>
			<th>chairs</th>
			<th>available chairs</th>
			<th>ticket amount</th>

		</thead>
		<tbody>

			<tr>
				<td>${showPresentation.movieTitle}</td>
				<td>${showPresentation.showingDate}</td>
				<td>${showPresentation.showingTime}</td>
				<td>${showPresentation.roomName}</td>
				<td>${showPresentation.chairAmount}</td>
				<td>${availableChairs}</td>
				<td>
					<form method="POST" action="PaymentServlet">
						<input type="hidden" name="showid" value=${showPresentation.showingId } /> 
						<input type="text" name="ticketamount" placeholder="Enter amount" required>				
						
					 <input type="submit" name="submitit" value="Buy" />
					</form>
				</td>

			</tr>



		</tbody>
	</table>

</body>
</html>