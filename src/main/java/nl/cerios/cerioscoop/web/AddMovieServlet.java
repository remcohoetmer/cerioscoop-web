package nl.cerios.cerioscoop.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.service.ShowService;

/**
 * Servlet implementation class AddFilmServlet
 */
@WebServlet("/AddMovieServlet")
public class AddMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ShowService showservice;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		Movie movie = new Movie();
		
		if ("Submit".equals(request.getParameter("submitit"))) {
			movie.setCategoryId(Integer.parseInt(request.getParameter("category")));			
			movie.setTitle(request.getParameter("moviename"));
			movie.setMinutes(Integer.parseInt(request.getParameter("minutes")));
			movie.setMovieType(Integer.parseInt(request.getParameter("movietype")));
			movie.setLanguage(request.getParameter("language"));
			movie.setDescription(request.getParameter("description"));
			
			showservice.addMovie(movie);
		}
		doGet(request, response);
	}
}
