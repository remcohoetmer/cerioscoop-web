package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		final List<Employee> dbEmployees = generalService.getEmployees();	
		final List<Customer> dbCustomers = generalService.getCustomers();	
		final User customer = new Customer();
		final User employee = new Employee();
		final User authenticatedCustomer;
		final User authenticatedEmployee;
		
		//input
		customer.setUsername(request.getParameter("txtUserName"));			
		customer.setPassword(request.getParameter("txtPassword"));
		employee.setUsername(request.getParameter("txtUserName"));			
		employee.setPassword(request.getParameter("txtPassword"));
		
		//output
		authenticatedCustomer = generalService.authenticateCustomer(customer, dbCustomers);
		authenticatedEmployee = generalService.authenticateEmployee(employee, dbEmployees);
		
 		final HttpSession session = request.getSession();
		if(generalService.authenticateUser(authenticatedCustomer)){
			session.setAttribute("user", authenticatedCustomer);
			session.setAttribute("usertype", "customer");
			response.sendRedirect("/cerioscoop-web/jsp/customer.jsp");
			return;
			}

		if(generalService.authenticateUser(authenticatedEmployee)){
			session.setAttribute("user", authenticatedEmployee);
			session.setAttribute("usertype", "employee");
			response.sendRedirect("/cerioscoop-web/jsp/employee.jsp");
			return;
			}

		session.removeAttribute("user");
		session.removeAttribute("usertype");
	}	
}

