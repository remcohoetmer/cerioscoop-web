package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.FilmAgendaItem;
import nl.cerios.cerioscoop.service.FilmAgendaItemService;
import nl.cerios.cerioscoop.service.FilmAgendaServiceException;
import nl.cerios.cerioscoop.util.DateUtils;



/**
 * Servlet implementation class MyFirstServlet
 * 
 * http://stackoverflow.com/questions/2349633/doget-and-dopost-in-servlets
 */
@WebServlet("/MyFirstServlet")
public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DateUtils DU = new DateUtils();
	
   	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * R40><link href='/cerioscoop-web/cerioscoop.css' type='text/css' rel='stylesheet' />
	 * LET OP:		projectNaam/cssFileNaam.css		zo verwijs je naar de juiste locatie!
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<FilmAgendaItem> items = FilmAgendaItemService.getFilmAgendaItems();  //hierbij gaf de service een NullPointerException
		List<FilmAgendaItem> items2 = FilmAgendaItemService.getFirstFilmAgendaItems();
		List<FilmAgendaItem> items3 = FilmAgendaItemService.getFilmAgendaItems2();
		List<FilmAgendaItem> items4 = FilmAgendaItemService.getFirstFilmAgendaItems2();
		
		items3.sort(new Comparator<FilmAgendaItem>() {

			@Override
			public int compare(FilmAgendaItem itemL, FilmAgendaItem itemR) {  //is itemL groter dan itemR? anders bovenaan
				if (itemL.getDatum().before(itemR.getDatum())) {
					return 1;
				} else if (itemL.getDatum().after(itemR.getDatum())) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		StringBuilder html = new StringBuilder("<!DOCTYPE html>")
			        .append("<html>")
			        .append("<head><title>Filmagenda</title><link href='/cerioscoop-web/cerioscoop.css' type='text/css' rel='stylesheet' /></head>")
			        .append("<body><h1>Voorstellingen</h1>")
			        .append("<table>")
			        .append("<thead><th>Filmtitel</th><th>speelt op:</th><th>tijd</th></thead>")
			        .append("<tbody>");
			    for (FilmAgendaItem item : items) {
				html.append("<tr><td>")
					.append(item.getTitel())
					.append("</td><td>")
					.append(DU.format(item.getDatum()))
					.append("</td><td>")
					.append(DU.formatTijd(item.getTijd()))
					.append("</td></tr>");
					}
			    html.append("</tbody>")
					.append("</table>");
			    for (FilmAgendaItem item : items2) {
				html.append("</tbody>")
					.append("</table>")
					.append("<h1></h1>")
					.append("<p>Vandaag is het " +DU.getDate())
					.append("<br />De eerstvolgende film is op "+DU.format2(item.getDatum())+" om "+DU.formatTijd(item.getTijd()))
					.append("<br />Dat is over circa 2 weken."+ "</p>");
			    	}
			    
			    html.append("<body><h1>Filmagenda</h1>")
					.append("<table>")
					.append("<thead><th>Filmtitel</th><th>speelt op:</th><th>tijd</th></thead>")
					.append("<tbody>");
			    for (FilmAgendaItem item : items3) {
			    html.append("<tr><td>")
					.append(item.getTitel())
					.append("</td><td>")
					.append(DU.format(item.getDatum()))
					.append("</td><td>")
					.append(DU.formatTijd(item.getTijd()))
					.append("</td></tr>");
					}
			    for (FilmAgendaItem item : items4) {
				html.append("</tbody>")
					.append("</table>")
					.append("<h1></h1>")
					.append("<p>Vandaag is het " +DU.getDate())
					.append("<br />De eerstvolgende film is op "+DU.format2(item.getDatum())+" om "+DU.formatTijd(item.getTijd()))
					.append("<br />Dat is over circa 2 weken."+ "</p>");
			    	}
			    html.append("</body>")
			        .append("</html>");

			    response.setContentType("text/html;charset=UTF-8");
			    response.getWriter().write(html.toString());		    
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date date = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
		java.sql.Date sqlDate = null;
		java.sql.Date sqlDate2 = null;
		System.out.println("Status submitit: " + request.getParameter("submitit") + ".");

		if (request.getParameter("submitit").equals("submitted")) {
			String filmnaam = request.getParameter("filmnaam");
			System.out.println("In de if: " + request.getParameter("filmnaam"));
			String min = request.getParameter("minuten");
			int minuten = Integer.parseInt(min);
			System.out.println("In de if: " + request.getParameter("minuten"));
			String tp = request.getParameter("type");
			int type = Integer.parseInt(tp);
			System.out.println("In de if: " + request.getParameter("type"));
			String taal = request.getParameter("taal");
			System.out.println("In de if: " + request.getParameter("taal"));
			
			String premdat = request.getParameter("premdat");
			System.out.println("In de if: " + request.getParameter("premdat"));
			
			if(premdat != "yyyy-MM-dd"){
				System.out.println("De datum is NIET het juiste formaat: "+premdat);  
				//Hoe kan je nu een bericht in html terug geven?
				
			
				
			try {
				
				date = format.parse(premdat);  
				//bij het invoeren van de verkeerde datum of verkeerde formaat krijg je rare getallen!
				//check inbouwen!
				sqlDate = new java.sql.Date(date.getTime());  //Maak een methode in DateUtils!
			} catch (ParseException e) {
				throw new MyFirstServletException("Something went wrong while parsing premiere datum.", e);
				}
			}
			String lavoor = request.getParameter("lavoor");
			System.out.println("In de if: " + request.getParameter("lavoor"));
			try {
				date = format.parse(lavoor);
				sqlDate2 = new java.sql.Date(date.getTime());  //Maak een methode in DateUtils!
			} catch (ParseException e) {
				throw new MyFirstServletException("Something went wrong while parsing datum laatste voorstelling.", e);
			}
			FilmAgendaItemService.registerFilm(filmnaam, minuten, type, taal, sqlDate, sqlDate2);
		}
		doGet(request, response);
	}
}
