package nl.cerios.cerioscoop.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.service.EmployeeService;

/**
 * Servlet implementation class AddFilmServlet
 */
@WebServlet("/AddMovieServlet")
public class AddMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EmployeeService employeeService;
	
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
			
			employeeService.addMovie(movie);
		}
		request.getRequestDispatcher("/html/add-movie.html").
        forward(request,response);
	}
}
