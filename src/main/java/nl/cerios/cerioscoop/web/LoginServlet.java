package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.cerios.cerioscoop.domain.Customer;
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
		final List<Customer> dbCustomers = generalService.getCustomers();	
		final User customer = new Customer();
		final User authenticatedCustomer;
		final HttpSession session = request.getSession();
		final PrintWriter out = response.getWriter();
		
		//input
		customer.setUsername(request.getParameter("txtUserName"));			
		customer.setPassword(request.getParameter("txtPassword"));
				
		//output
		authenticatedCustomer = generalService.authenticateCustomer(customer, dbCustomers);
				
        response.setContentType("text/html;charset=UTF-8");
        
		if (authenticatedCustomer == null) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Combination username and password do not match!');");
			out.println("location='index.jsp';");
			out.println("</script>");
		} else if (generalService.authenticateUser(authenticatedCustomer)) {
			session.setAttribute("user", authenticatedCustomer);
			session.setAttribute("usertype", "customer");
			response.sendRedirect("/cerioscoop-web/jsp/customer.jsp");
		} else
			return;
	}
}

