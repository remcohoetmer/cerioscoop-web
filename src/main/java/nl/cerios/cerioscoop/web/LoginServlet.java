package nl.cerios.cerioscoop.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Employee;
import nl.cerios.cerioscoop.domain.User;
import nl.cerios.cerioscoop.service.GeneralService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GeneralService generalService; 
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final User customer = new Customer();
		final User employee = new Employee();
		User authenticatedCustomer = new Customer();
		User authenticatedEmployee = new Employee();
		
		customer.setUsername(request.getParameter("txtUserName"));			
		customer.setPassword(request.getParameter("txtPassword"));
		employee.setUsername(request.getParameter("txtUserName"));			
		employee.setPassword(request.getParameter("txtPassword"));
		
		authenticatedCustomer = generalService.authenticateCustomer(customer);
		authenticatedEmployee = generalService.authenticateEmployee(employee);
		
 
		if(authenticatedCustomer.getUsername() != "No customer" && authenticatedCustomer.getPassword() != "No customer"){
			request.getSession().setAttribute("user", authenticatedCustomer);
			response.sendRedirect("/cerioscoop-web/jsp/customer.jsp");
			return;
			}else{
				authenticatedCustomer = authenticatedEmployee;
			}

		if(authenticatedEmployee.getUsername() != "No employee" && authenticatedEmployee.getPassword() != "No employee"){
			request.getSession().setAttribute("user", authenticatedEmployee);
			response.sendRedirect("/cerioscoop-web/jsp/employee.jsp");
			return;
			}
			
	}	
}

