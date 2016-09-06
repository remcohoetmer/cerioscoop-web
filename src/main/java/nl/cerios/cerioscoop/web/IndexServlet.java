package nl.cerios.cerioscoop.web;

import java.io.IOException;
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
import nl.cerios.cerioscoop.service.GeneralService;
import nl.cerios.cerioscoop.service.MovieNotFoundException;
import nl.cerios.cerioscoop.service.ServiceException;
import nl.cerios.cerioscoop.util.DateUtils;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GeneralService generalService;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		final DateUtils dateUtils = new DateUtils();
		// 1. haal alle shows op
		final List<Show> allShows = generalService.getShows();

		// 2. Haal alle movies op!
		final List<Movie> allMovies = generalService.getMovies();

		// 3. Haal alle actuele movies uit allShows
		final List<Movie> moviesFromAllShows = new ArrayList<Movie>();
		for (Show show : allShows) {
			if (show.getShowDate().after(dateUtils.getCurrentDate())) {
				Movie movie;
				try {
					movie = generalService.getMovieByMovieId(show.getMovieId(), allMovies);
				} catch (final MovieNotFoundException e) {
					throw new ServiceException("No movie was found", e);
				}
				moviesFromAllShows.add(movie);
			}

			// 4. zet de src van elk plaatje in een atribuut
			for (Movie movie : moviesFromAllShows) {
				request.setAttribute(movie.getMovieId().toString(), movie);
			}
		}
		getServletContext().getRequestDispatcher("/jsp/start.jsp").forward(request, response);
	}
}
