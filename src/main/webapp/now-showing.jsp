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


   	<!-- Main CSS
   	================================================== -->
	<link rel="stylesheet" href="css/style.css">

	<!-- Google web font
   ================================================== -->
	<link href='https://fonts.googleapis.com/css?family=Raleway:700' rel='stylesheet' type='text/css'>

	<!-- Cerioscoop CSS
   ================================================== -->
	<link href='/cerioscoop-web/cerioscoop.css' type='text/css' rel='stylesheet' />
	
</head>



<body>

<!-- Navigation section
================================================== -->
<section class="navbar navbar-fixed-top custom-navbar" role="navigation">
	<div class="container">

		<div class="navbar-header">
			<button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon icon-bar"></span>
				<span class="icon icon-bar"></span>
				<span class="icon icon-bar"></span>
			</button>
			<a href="/cerioscoop-web/index.html" class="smoothScroll navbar-brand">CERIOSCOOP</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-right">
            
				<!-- <li><a href="#home" class="smoothScroll">HOME</a></li> -->
                
				<li><a href="now-showing.jsp" class="smoothScroll">NOW SHOWING</a></li>
				<li><a href="/cerioscoop-web/index.html" class="smoothScroll">ABOUT</a></li>
				<li><a href="/cerioscoop-web/index.html" class="smoothScroll">TEAM</a></li>
				<li><a href="add-show.html" class="smoothScroll">ADD SHOW</a></li>
				<li><a href="add-movie.html" class="smoothScroll">ADD MOVIE</a></li>
				<li><a href="/cerioscoop-web/index.html" class="smoothScroll">TRAILERS</a></li>
			</ul>
		</div>

	</div>
</section>

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