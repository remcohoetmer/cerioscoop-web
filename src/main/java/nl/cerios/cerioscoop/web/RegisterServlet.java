package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.io.PrintWriter;

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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
        final HttpSession session = request.getSession();


        if (request.getParameter("firstname").length() >= 8 && request.getParameter("firstname").length() <= 20 && customerService.isValidChar(request.getParameter("firstname"))) {
			customer.setFirstName(request.getParameter("firstname"));
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('User or password incorrect');");
			out.println("location='index.jsp';");
			out.println("</script>");
		}

        if (request.getParameter("lastname").length() >= 8 && request.getParameter("lastname").length() <= 20 && customerService.isValidChar(request.getParameter("lastname"))) {
			customer.setLastName(request.getParameter("lastname"));
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Last name needs to be between 8 and 20 characters long');");
			out.println("location='index.jsp';");
			out.println("</script>");
		}
        if (request.getParameter("username").length() >= 8 && request.getParameter("username").length() <= 20 && customerService.isValidChar(request.getParameter("username"))) {
			customer.setUsername(request.getParameter("username"));
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Username needs to be between 8 and 20 characters long');");
			out.println("location='index.jsp';");
			out.println("</script>");
		}
		if (request.getParameter("firstname").length() >= 8 && request.getParameter("firstname").length() <= 20) {
				customer.setFirstName(request.getParameter("firstname"));
			} else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('First name needs to be between 8 and 20 characters long');");
				out.println("location='index.jsp';");
				out.println("</script>");
			}

		if (request.getParameter("password").length() >= 6 && request.getParameter("password").length() <= 12
				&& request.getParameter("password").equals(request.getParameter("password2"))) {
			customer.setPassword(request.getParameter("password"));
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Password needs to be between 6 and 12 characters long');");
			out.println("location='index.jsp';");
			out.println("</script>");
		}

		if (request.getParameter("email").length() >= 6 && request.getParameter("email").contains("@")) {
			customer.setEmail(request.getParameter("email"));
			generalService.registerCustomer(customer);
            session.setAttribute("customer", customer);
            //getServletContext().getRequestDispatcher("/...ENTER VALID LINK....").forward(request, response); 
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Enter valid email with minimal 6 charachters long');");
			out.println("location='index.jsp';");
			out.println("</script>");
		}

		request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
	}

}
