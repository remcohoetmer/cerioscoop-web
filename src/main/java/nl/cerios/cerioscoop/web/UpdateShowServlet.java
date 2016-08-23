package nl.cerios.cerioscoop.web;
  
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.service.EmployeeService;
import nl.cerios.cerioscoop.util.DateUtils;
  
/**
* Servlet implementation class UpdateShowServlet
*/
@WebServlet("/UpdateShowServlet")
public class UpdateShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static DateUtils dateUtils = new DateUtils();
         
    @EJB
    private EmployeeService employeeService;
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
    }
  
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Show show = new Show();
    	final DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy"); 	
		final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
          
        if ("Submit".equals(request.getParameter("submitit"))) {
            show.setShowId(Integer.parseInt(request.getParameter("show_id")));  
            show.setMovieId(Integer.parseInt(request.getParameter("movie_id")));
            show.setRoomId(Integer.parseInt(request.getParameter("room_id")));    
            show.setShowTime(dateUtils.getCurrentSqlTime()); 
            try {
            	String showTime = (request.getParameter("lastshowingdate")+ ":00");
				
				show.setShowDate(new java.sql.Date(dateformat.parse(request.getParameter("premieredate")).getTime()));
				show.setShowTime(new java.sql.Time(timeFormat.parse(showTime).getTime()));
              } catch (ParseException e) {
                  throw new ShowException("Something went wrong while parsing premiere date.", e);
              }                    
              employeeService.updateShowFromDatabase(show);
          
          }
          request.getRequestDispatcher("/jsp/update-show.jsp").
          forward(request,response);
      }
  }
