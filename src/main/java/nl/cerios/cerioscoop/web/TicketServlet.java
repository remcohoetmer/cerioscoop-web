package nl.cerios.cerioscoop.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String showingId;
	private String movieTitle;
	private String showingDate;
	private String showingTime;
	private String roomName;
	private String chairAmount;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("showingId", showingId);
		request.setAttribute("movieTitle", movieTitle);
		request.setAttribute("showingDate", showingDate);
		request.setAttribute("showingTime", showingTime);
		request.setAttribute("roomName", roomName);
		request.setAttribute("chairAmount", chairAmount);
		getServletContext().getRequestDispatcher("/jsp/ticket.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		showingId =request.getParameter("showid");
		movieTitle =request.getParameter("movieTitle");
		showingDate =request.getParameter("showingDate");
		showingTime =request.getParameter("showingTime");
		roomName =request.getParameter("roomName");
		chairAmount =request.getParameter("chairAmount");
				
		doGet(request, response);
	}

}
