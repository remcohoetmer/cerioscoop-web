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
 * Servlet implementation class FilmShowingServlet
 * 
 * http://stackoverflow.com/questions/2349633/doget-and-dopost-in-servlets
 */
@WebServlet("/FilmShowingServlet")
public class FilmShowingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DateUtils dateUtils = new DateUtils();
	private static ShowingService showingService = new ShowingService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * @Todo filmnaam weergeven door koppeling met film_id o.i.d.
	 */
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		
		//List<Film> films = showingService.getFilms(); 
		final List<Showing> showings = showingService.getShowings();
		final Showing firstShowing = showingService.getFirstShowingAfterCurrentDate();
		java.util.Date showingPremiere = null;
		String showingPremiereSqlDatabase = null;
		
		showingPremiereSqlDatabase = dateUtils.sqlDatabaseFormat(firstShowing.getPremiereDate())+" "+dateUtils.timeFormat(firstShowing.getPremiereTime());
		try {
			showingPremiere = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(showingPremiereSqlDatabase);
		} catch (ParseException e) {
			throw new FilmShowingServletException("Something went wrong while parsing premiere datum.", e);
		}
		
		
		showings.sort(new Comparator<Showing>() {

			@Override
			public int compare(final Showing itemL, final Showing itemR) {  //is itemL groter dan itemR? anders bovenaan
				if (itemL.getPremiereDate().before(itemR.getPremiereDate())) {
					return -1;
				} else if (itemL.getPremiereDate().after(itemR.getPremiereDate())) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		
		final StringBuilder html = new StringBuilder("<!DOCTYPE html>")
			        .append("<html>")
			        .append("<head><title>Filmagenda</title><link href='/cerioscoop-web/cerioscoop.css' type='text/css' rel='stylesheet' /></head>")
			        .append("<body><h1>Now Showing</h1>")
			        .append("<table>")
			        .append("<thead><th>Filmtitle</th><th>plays on:</th><th>time</th></thead>")
			        .append("<tbody>");
			    for (Showing item : showings) {
				html.append("<tr><td>")
					.append(item.getFilmId())
					.append("</td><td>")
					.append(dateUtils.format(item.getPremiereDate()))
					.append("</td><td>")
					.append(dateUtils.timeFormat(item.getPremiereTime()))
					.append("</td></tr>");
					}
			    html.append("</tbody>")
					.append("</table>")
			    	.append("<p>Today it is " +dateUtils.getDate())
					.append("<br />The first upcoming film: "+firstShowing.getFilmId() +" is on "+dateUtils.format2(firstShowing.getPremiereDate())+" at "+dateUtils.timeFormat(firstShowing.getPremiereTime()))
					.append("<br />That's in "+ dateUtils.calculateTime(dateUtils.getSecondsBetween(showingPremiere, dateUtils.getCurrentDate())) +"</p>")
					.append("</body>")
			        .append("</html>");

			    response.setContentType("text/html;charset=UTF-8");
			    response.getWriter().write(html.toString());		    
	}
	
	/**
	 *	Posts the film opslaan form in the database. 
	 *
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
