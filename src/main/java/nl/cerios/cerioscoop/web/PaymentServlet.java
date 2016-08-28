package nl.cerios.cerioscoop.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.service.CustomerService;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private CustomerService customerService;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int ticketAmount = (Integer.parseInt(request.getParameter("ticketamount")));
		int chairAmount = (Integer.parseInt(request.getParameter("chairAmount")));
		int showingId = (Integer.parseInt(request.getParameter("showid")));
		String movieTitle = (request.getParameter("movieTitle"));
		request.setAttribute("movieTitle", movieTitle);

		customerService.updateChairsSold(ticketAmount, showingId);
		getServletContext().getRequestDispatcher("/jsp/successful-payment.jsp").forward(request, response);
	}

}
