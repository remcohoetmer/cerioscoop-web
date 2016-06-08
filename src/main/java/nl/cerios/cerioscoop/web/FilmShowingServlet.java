package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Showing;
import nl.cerios.cerioscoop.service.ShowingService;
import nl.cerios.cerioscoop.util.DateUtils;

/**
 * Servlet implementation class MyFirstServlet
 * 
 * http://stackoverflow.com/questions/2349633/doget-and-dopost-in-servlets
 */
@WebServlet("/FilmShowingServlet")
public class FilmShowingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DateUtils DU = new DateUtils();
	private static ShowingService SS = new ShowingService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * R40><link href='/cerioscoop-web/cerioscoop.css' type='text/css' rel='stylesheet' />
	 * LET OP:		projectNaam/cssFileNaam.css		zo verwijs je naar de juiste locatie!
	 * 
	 * @Todo filmnaam weergeven door koppeling met film_id o.i.d.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//List<Film> films = SS.getFilms(); 
		List<Showing> showings = SS.getShowing();
		Showing firstShowing = SS.getFirstShowingAfterCurrentDate();
		java.util.Date premMoment = null;
		String premMomentDB = null;
		
		showings.sort(new Comparator<Showing>() {

			@Override
			public int compare(Showing itemL, Showing itemR) {  //is itemL groter dan itemR? anders bovenaan
				if (itemL.getPremiereDate().before(itemR.getPremiereDate())) {
					return -1;
				} else if (itemL.getPremiereDate().after(itemR.getPremiereDate())) {
					return 1;
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
			    for (Showing item : showings) {
				html.append("<tr><td>")
					.append(item.getFilmID())
					.append("</td><td>")
					.append(DU.format(item.getPremiereDate()))
					.append("</td><td>")
					.append(DU.timeFormat(item.getPremiereTime()))
					.append("</td></tr>");
					}
			    html.append("</tbody>")
					.append("</table>");
			   // for (Film item : items) {
			    	premMomentDB = DU.sqlDatabaseFormat(firstShowing.getPremiereDate())+" "+DU.timeFormat(firstShowing.getPremiereTime());
					try {
						premMoment = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(premMomentDB);
					} catch (ParseException e) {
						throw new FilmShowingServletException("Something went wrong while parsing premiere datum.", e);
					}
			    html.append("</tbody>")
					.append("</table>")
					.append("<h1></h1>")
					.append("<p>Vandaag is het " +DU.getDate())
					.append("<br />De eerstvolgende film: "+firstShowing.getFilmID() +" is op "+DU.format2(firstShowing.getPremiereDate())+" om "+DU.timeFormat(firstShowing.getPremiereTime()))
					.append("<br />Dat is over "+ DU.calculateTime(DU.getSecondsBetween(premMoment, DU.getCurrentDate())) +"</p>");
			//    	}
			    html.append("</body>")
			        .append("</html>");

			    response.setContentType("text/html;charset=UTF-8");
			    response.getWriter().write(html.toString());		    
	}
	
	/**
	 *	Posts the film opslaan form in the database. 
	 *
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
