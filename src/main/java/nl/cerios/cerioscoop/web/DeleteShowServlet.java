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
 * Servlet implementation class DeleteShowServlet
 */
@WebServlet("/DeleteShowServlet")
public class DeleteShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private EmployeeService employeeService;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("Submit".equals(request.getParameter("submitit"))) {
			int showId = (Integer.parseInt(request.getParameter("show_id")));
			
					
			employeeService.deleteShowFromDatabase(showId);
		
		}
		request.getRequestDispatcher("/jsp/delete-show.jsp").
        forward(request,response);
	}

}
