package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import nl.cerios.cerioscoop.domain.Category;
import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.service.EmployeeService;
import nl.cerios.cerioscoop.service.GeneralService;

/**
 * Servlet implementation class AddFilmServlet
 */
@WebServlet("/AddMovieServlet")
public class AddMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EmployeeService employeeService;
	
	@EJB
	private GeneralService generalService;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final List<Movie> currentMovies = generalService.getMovies();
		request.setAttribute("currentMovies", currentMovies);
		getServletContext().getRequestDispatcher("/jsp/employee/add-movie.jsp").forward(request, response);
	}
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		final Movie movie = new MovieBuilder()
				.withTitle(request.getParameter("moviename"))
				.withCategory(Category.valueOf(request.getParameter("category")))
				.withMinutes(Integer.parseInt(request.getParameter("minutes")))
				.withType(Integer.parseInt(request.getParameter("movietype")))
				.withLanguage(request.getParameter("language"))
				.withDescription(request.getParameter("description"))
				.withTrailer(request.getParameter("trailer"))
				.build();
		employeeService.addMovie(movie);
		doGet(request, response);
	}
}
