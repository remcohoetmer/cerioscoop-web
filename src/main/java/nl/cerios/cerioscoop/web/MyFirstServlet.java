package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.FilmAgendaItem;
import nl.cerios.cerioscoop.service.FilmAgendaItemService;



/**
 * Servlet implementation class MyFirstServlet
 */
@WebServlet("/MyFirstServlet")
public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DATE_FORMAT = "dd-MM-yyyy";
	private static final String TIME_FORMAT = "HH:mm";
	
   	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * R40><link href='/cerioscoop-web/cerioscoop.css' type='text/css' rel='stylesheet' />
	 * LET OP:		projectNaam/cssFileNaam.css		zo verwijs je naar de juiste locatie!
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<FilmAgendaItem> items = FilmAgendaItemService.getFilmAgendaItems();  //hierbij gaf de service een NullPointerException
		
		
		 StringBuilder html = new StringBuilder("<!DOCTYPE html>")
			        .append("<html>")
			        .append("<head><title>Filmagenda</title><link href='/cerioscoop-web/cerioscoop.css' type='text/css' rel='stylesheet' /></head>")
			        .append("<body><h1>Filmagenda</h1>")
			        .append("<table>")
			        .append("<thead><th>Filmtitel</th><th>speelt op:</th><th>tijd</th></thead>")
			        .append("<tbody>");
			    for (FilmAgendaItem item : items) {
			      html.append("<tr><td>")
			          .append(item.getTitel())
			          .append("</td><td>")
			          .append(format(item.getDatum()))
			          .append("</td><td>")
			          .append(formatTijd(item.getTijd()))
			          .append("</td></tr>");
			    }
			    for (FilmAgendaItem item : items) {
			    html.append("</tbody>")
			        .append("</table>")
			        .append("<h1></h1>")
			        .append("<p>Vandaag is het " +getDate())
			        .append("<br />De eerstvolgende film is op "+item.getDatum())
			        .append("<br />Dat is over circa 2 weken."+ "</p>");
			    }
			    html.append("</body>")
			        .append("</html>");

			    response.setContentType("text/html;charset=UTF-8");
			    response.getWriter().write(html.toString());
	}
	
	/**
	 * It returns a date object in a string with format "dd-MM-yyyy".
	 * 
	 * @param datum
	 * @return "dd-MM-yyyy"
	 */
	private String format(Date datum) {
	    return datum != null ? new SimpleDateFormat(DATE_FORMAT).format(datum) : "onbekend";
	 }
	
	/**
	 * It returns a time object in a string with format "HH:mm".
	 * 
	 * @param tijd
	 * @return
	 */
	private String formatTijd(Date tijd) {
	    return tijd != null ? new SimpleDateFormat(TIME_FORMAT).format(tijd) : "onbekend";
	 }
	
	/**
	 * It returns a date object in a string with format "d MMMM, HH:mm".
	 * 
	 * @param datum
	 * @return 
	 */
//	private String format2(Date datum) {
//	    return datum != null ? new SimpleDateFormat(DATE_FORMAT).format(datum) : "onbekend";
//	 }
	private String getDate() {
		return new SimpleDateFormat("d MMMM, HH:mm").format(new Date());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
