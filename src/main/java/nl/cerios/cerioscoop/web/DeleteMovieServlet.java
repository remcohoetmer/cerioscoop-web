package nl.cerios.cerioscoop.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.Room;
import nl.cerios.cerioscoop.service.EmployeeService;

/**
 * Servlet implementation class DeleteMovieServlet
 */
@WebServlet("/DeleteMovieServlet")
public class DeleteMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EmployeeService employeeService;
	
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
				
		if ("Submit".equals(request.getParameter("submitit"))) {
			int MovieId = (Integer.parseInt(request.getParameter("movie_id")));
			
					
			employeeService.deleteMovieFromDatabase(MovieId);
		
		}
		request.getRequestDispatcher("/jsp/delete-movie.jsp").
        forward(request,response);
	}
	
}
