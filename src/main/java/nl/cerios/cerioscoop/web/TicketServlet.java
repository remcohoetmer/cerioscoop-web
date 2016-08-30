package nl.cerios.cerioscoop.web;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.ShowPresentation;
import nl.cerios.cerioscoop.service.CustomerService;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShowPresentation showPresentation;
	private int availableChairs;
	
	@EJB
	private CustomerService customerService;
       
	/**
	 * This servlet checks if the request for buying a certain amount of tickets is possible or not,
	 * if so it updates the chairs_sold amount.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("showPresentation", showPresentation);
		request.setAttribute("availableChairs", availableChairs);
		getServletContext().getRequestDispatcher("/jsp/ticket.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int showID =Integer.parseInt(request.getParameter("showid"));
 		request.setAttribute("showID", showID);
		showPresentation = customerService.getShowPresentationByShowId(showID);
		request.setAttribute("showPresentation", showPresentation);
		int chairAmount = (showPresentation.getChairAmount()).intValue();
		int chairsSold = (showPresentation.getChairsSold()).intValue();
		availableChairs = chairAmount-chairsSold;
		request.setAttribute("availableChairs", availableChairs);
		
				
			doGet(request, response);
		
	} 

}
