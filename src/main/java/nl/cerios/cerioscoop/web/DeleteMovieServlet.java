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
import nl.cerios.cerioscoop.service.EmployeeService;
import nl.cerios.cerioscoop.service.GeneralService;

/**
 * Servlet implementation class DeleteMovieServlet
 */
@WebServlet("/DeleteMovieServlet")
public class DeleteMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EmployeeService employeeService;
	
	@EJB
	private GeneralService generalService;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final List<Movie> currentMovies = generalService.getMovies();
		request.setAttribute("currentMovies", currentMovies);
		getServletContext().getRequestDispatcher("/jsp/delete-movie.jsp").forward(request, response);
		doPost(request, response);
	}
	
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
				
		if ("Submit".equals(request.getParameter("submitit"))) {
			int MovieId = (Integer.parseInt(request.getParameter("movie_id")));
			
					
			employeeService.deleteMovieFromDatabase(MovieId);
		
		}
		
	}
	
}
