package nl.cerios.cerioscoop.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Category;
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
	
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final Movie movie = new Movie(null,
				request.getParameter("moviename"),
				Category.valueOf(request.getParameter("category")),
				Integer.parseInt(request.getParameter("minutes")),
				Integer.parseInt(request.getParameter("movietype")),
				request.getParameter("language"),
				request.getParameter("description"));
		employeeService.addMovie(movie);

		request.getRequestDispatcher("/html/add-movie.html").
        forward(request,response);
	}
}
