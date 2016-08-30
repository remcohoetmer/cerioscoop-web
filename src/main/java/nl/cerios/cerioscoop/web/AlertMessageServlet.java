package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AlertMessageServlet
 */
@WebServlet("/AlertMessageServlet")
public class AlertMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/jsp/alert.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String user = request.getParameter("username");
		boolean result = user.equals("marcel");
		if(result == true){
		       request.getSession().setAttribute("username", user);            
		       response.sendRedirect("AlertMessageServlet");
		    }
		     else{
		    	   out.println("<script type=\"text/javascript\">");
		    	   out.println("alert('Computer says no :D');");
		    	   out.println("location='index.jsp';");
		    	   out.println("</script>");   
		    }
	}

}
