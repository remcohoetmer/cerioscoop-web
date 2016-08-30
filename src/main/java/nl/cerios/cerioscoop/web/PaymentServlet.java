package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private CustomerService customerService;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    	int showingId = (Integer.parseInt(request.getParameter("showid")));
    	final ShowPresentation showPresentation = customerService.getShowPresentationByShowId(showingId);
    	int chairAmount = (showPresentation.getChairAmount()).intValue();
    	int chairsSold = (showPresentation.getChairsSold()).intValue();
    	int ticketAmount = (Integer.parseInt(request.getParameter("ticketamount")));
		int availableChairs = chairAmount-chairsSold;
	
	
		if((availableChairs-ticketAmount) >=0){
			request.setAttribute("showPresentation", showPresentation);
			customerService.updateChairsSold(ticketAmount, showingId);
			getServletContext().getRequestDispatcher("/jsp/successful-payment.jsp").forward(request, response);
		}
		else{
			   out.println("<script type=\"text/javascript\">");
	            out.println("alert('Not enough tickets');");
	            out.println("location='TicketServlet';");
	            out.println("</script>");   

		}
	} 

}
