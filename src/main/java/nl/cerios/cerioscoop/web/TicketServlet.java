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
	
	@EJB
	private CustomerService customerService;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		getServletContext().getRequestDispatcher("/jsp/ticket.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int showID =Integer.parseInt(request.getParameter("showid"));
		ShowPresentation showPresentation = customerService.getShowPresentationByShowId(showID);
		request.setAttribute("showPresentation", showPresentation);
		int chairAmount = (showPresentation.getChairAmount()).intValue();
		int chairsSold = (showPresentation.getChairsSold()).intValue();
		int availableChairs = chairAmount-chairsSold;
		request.setAttribute("availableChairs", availableChairs);
		
		doGet(request, response);
	}

}
