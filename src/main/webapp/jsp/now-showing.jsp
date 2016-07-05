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
<%
// TODO EJB-calls vanuit servlets, niet vanuit een JSP! Dit willen we niet meer zien!
final GeneralService generalService = (GeneralService) new InitialContext().lookup("java:module/GeneralService");

final DateUtils dateUtils = new DateUtils();
final List<Show> shows = generalService.getShows();
final Show firstShowing = generalService.getFirstShowAfterCurrentDate();
java.util.Date showingPremiere = null;
String showingPremiereSqlDatabase = null;
 
showingPremiereSqlDatabase = dateUtils.sqlDatabaseFormat(firstShowing.getShowDate())+" "+dateUtils.timeFormat(firstShowing.getShowTime());
try {
	showingPremiere = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(showingPremiereSqlDatabase);
} catch (ParseException e) {
	throw new ShowException("Something went wrong while parsing premiere datum.", e);
}

shows.sort(new Comparator<Show>() {

	public int compare(final Show itemL, final Show itemR) {  //is itemL groter dan itemR? anders bovenaan
		if (itemL.getShowDate().before(itemR.getShowDate())) {
			return -1;
		} else if (itemL.getShowDate().after(itemR.getShowDate())) {
			return 1;
		} else {
			return 0;
		}
	}
});		
 %>
<table>
<thead><th>Movietitle</th><th>plays on:</th><th>time</th></thead>
<tbody>
<% for (Show item : shows) {

if (item.getShowDate().after(dateUtils.getCurrentDate())){
%>
<tr>
	<td><%=generalService.getMovieByMovieId(item.getMovieId()).getTitle()%></td>
	<td><%=dateUtils.format(item.getShowDate())%> </td>
	<td><%=dateUtils.timeFormat(item.getShowTime())%></td>
</tr>
<% }} %>
</tbody>
</table>
<p>Today it is <%= dateUtils.getDate()%>
<br />The first upcoming film: <%=generalService.getMovieByMovieId(firstShowing.getMovieId()).getTitle()%> on <%=dateUtils.format2(firstShowing.getShowDate())%> at <%=dateUtils.timeFormat(firstShowing.getShowTime())%>
<br />That's in <%= dateUtils.calculateTime(dateUtils.getSecondsBetween(showingPremiere, dateUtils.getCurrentDate())) %></p>

</body>
</html>