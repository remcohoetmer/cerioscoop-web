package nl.cerios.cerioscoop.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.service.GeneralService;
import nl.cerios.cerioscoop.util.DateUtils;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final DateUtils dateUtils = new DateUtils(); 
	
	@EJB
	private GeneralService generalService;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer = new Customer();
		
		if ("Submit".equals(request.getParameter("submitit"))) {		
			customer.setFirstName(request.getParameter("firstname"));
			customer.setLastName(request.getParameter("lastname"));
			customer.setUsername(request.getParameter("username"));
			customer.setPassword(request.getParameter("password"));
			customer.setEmail(request.getParameter("email"));
			generalService.registerCustomer(customer);
		}
		request.getRequestDispatcher("/jsp/register.jsp").
        forward(request,response);
	}

}
