package nl.cerios.cerioscoop.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.service.ShowingService;

/**
 * Servlet implementation class AddFilmServlet
 */
@WebServlet("/AddFilmServlet")
public class AddFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ShowingService SS = new ShowingService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFilmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("submitit").equals("Submit")) {
			String filmname = (request.getParameter("filmname"));
			int minutes = Integer.parseInt(request.getParameter("minutes"));
			int type = Integer.parseInt(request.getParameter("type"));
			String language = request.getParameter("language");		
			SS.registerFilm(filmname, minutes, type, language);
		}
		doGet(request, response);
	}

}
