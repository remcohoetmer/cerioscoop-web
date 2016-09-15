package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.service.DataAccessObject;

/**
 * Servlet implementation class MovieServlet
 */
@WebServlet("/MoviePresentationServlet")
public class MoviePresentationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB // call DB
	private DataAccessObject dao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final List<Movie> movies = dao.getMovies();
		request.setAttribute("movies", movies);

		//int movieId = (int) request.getAttribute("4");
		//System.out.println(movieId);
		//for loop maken 
		getServletContext().getRequestDispatcher("/jsp/movie-presentation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
