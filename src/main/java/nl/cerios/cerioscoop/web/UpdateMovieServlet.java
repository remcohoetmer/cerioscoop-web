package nl.cerios.cerioscoop.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("Submit".equals(request.getParameter("submitit"))) {
			
			int movieId = (Integer.parseInt(request.getParameter("movie_id")));
			int category_id = (Integer.parseInt(request.getParameter("category_id")));
			String title = (request.getParameter("title"));
			int minutes = (Integer.parseInt(request.getParameter("minutes")));
			int movie_type = (Integer.parseInt(request.getParameter("movie_type")));
			String language = (request.getParameter("language"));
			String description = (request.getParameter("description")); 
			
					
			employeeService.updateMovieFromDatabase(movieId, category_id, title, minutes, movie_type, language, description);
		
		}
		request.getRequestDispatcher("/jsp/update-movie.jsp").
        forward(request,response);
	}
}
