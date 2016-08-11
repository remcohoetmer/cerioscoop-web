package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.domain.ShowPresentation;
import nl.cerios.cerioscoop.service.EmployeeService;
import nl.cerios.cerioscoop.service.GeneralService;
import nl.cerios.cerioscoop.util.DateUtils;

/**
 * Servlet implementation class AddShowServlet
 */
@WebServlet("/AddShowServlet")
public class AddShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EmployeeService employeeService;   

	@EJB
	private GeneralService generalService;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final List<Movie> currentMovies = generalService.getMovies();
		final List<ShowPresentation> showing = generalService.getShowings();
	
				
		request.setAttribute("currentMovies", currentMovies);
		request.setAttribute("showing", showing);
		getServletContext().getRequestDispatcher("/jsp/add-show.jsp").forward(request, response);
	}
	
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		Show show = new Show();
		final DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy"); 	
		final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

		if ("Submit".equals(request.getParameter("submitit"))) {
			show.setMovieId(Integer.parseInt(request.getParameter("movie_id")));
			try {
				String showTime = (request.getParameter("show_time")+ ":00");
							
				show.setShowDate(new java.sql.Date(dateformat.parse(request.getParameter("premieredate")).getTime()));
				show.setShowTime(new java.sql.Time(timeFormat.parse(showTime).getTime()));
			} catch (ParseException e) {
				throw new ShowException("Something went wrong while parsing premiere date.", e);
			}
			employeeService.addShow(show);
		}
		doGet(request, response);
	}

}
