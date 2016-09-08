package nl.cerios.cerioscoop.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.service.CustomerService;
import nl.cerios.cerioscoop.service.GeneralService;
/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private GeneralService generalService;

    @EJB
    private CustomerService customerService;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/jsp/register.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Customer customer = new Customer();
        final HttpSession session = request.getSession();
        String message="";

        if (request.getParameter("firstname").length() >= 8 && request.getParameter("firstname").length() <= 20 && customerService.isValidChar(request.getParameter("firstname"))) {
			customer.setFirstName(request.getParameter("firstname"));
		} else {
			message = "Invalid firstname: min 8 / max 20 alfanumeric characters";
		}

        if (request.getParameter("lastname").length() >= 8 && request.getParameter("lastname").length() <= 20 && customerService.isValidChar(request.getParameter("lastname"))) {
			customer.setLastName(request.getParameter("lastname"));
		} else {
			message = "Invalid lastname: min 8 / max 20 alfanumeric characters";
		}
        if (request.getParameter("username").length() >= 8 && request.getParameter("username").length() <= 20 && customerService.isValidChar(request.getParameter("username")) && customerService.isUniqueUser(request.getParameter("username"))) {
			customer.setUsername(request.getParameter("username"));
		} else {
			message = "Username invalid or not unique: min 8 / max 20 alfanumeric characters";
		}
           
		if (request.getParameter("password").length() >= 6 && request.getParameter("password").length() <= 12
				&& request.getParameter("password").equals(request.getParameter("password2"))) {
			customer.setPassword(request.getParameter("password"));
		} else {
			message= "Invalid username/password combination (username: min 6 / max 12 characters)";
		}

		if (request.getParameter("email").length() >= 6 && request.getParameter("email").contains("@")) {
			customer.setEmail(request.getParameter("email"));
		
            //getServletContext().getRequestDispatcher("/...ENTER VALID LINK....").forward(request, response); 
		} else {
		
			message= "Invalid email: minimal of 6 characters needed";
		}
		if(customer.getFirstName() !=null && customer.getLastName() !=null && customer.getUsername() != null && customer.getPassword() !=null && customer.getEmail() !=null ){
			generalService.registerCustomer(customer);
            session.setAttribute("customer", customer);
		}

			request.setAttribute("message", message);


			request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);

	}

}
