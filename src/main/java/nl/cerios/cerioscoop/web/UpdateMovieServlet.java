package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.math.BigInteger;

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

/**
 * Servlet implementation class UpdateMovieServlet
 */
@WebServlet("/UpdateMovieServlet")
public class UpdateMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	@EJB
	private EmployeeService employeeService;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Movie movie = new MovieBuilder()
				.withMovieId(new BigInteger(request.getParameter("movie_id")))
				.withTitle(request.getParameter("title"))
				.withCategory(Category.valueOf(request.getParameter("category")))
				.withMinutes(Integer.parseInt(request.getParameter("minutes")))
				.withType(Integer.parseInt(request.getParameter("movie_type")))
				.withLanguage(request.getParameter("language"))
				.withDescription(request.getParameter("description"))
				.build();		
					
			employeeService.updateMovieFromDatabase(movie);
		
		
		request.getRequestDispatcher("/jsp/update-movie.jsp").
        forward(request,response);
	}
}
