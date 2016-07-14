package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.service.GeneralService;
import nl.cerios.cerioscoop.service.MovieNotFoundException;
import nl.cerios.cerioscoop.util.DateUtils;

/**
 * Servlet implementation class NowShowingServlet
 */
@WebServlet("/NowShowingServlet")
public class NowShowingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//call DB
		GeneralService generalService;
		try {
			generalService = (GeneralService) new InitialContext().lookup("java:module/GeneralService");
		} catch (NamingException ne) {
			throw new ServletException(ne);
		}

		final DateUtils dateUtils = new DateUtils();
		final List<Show> shows = generalService.getShows();
		final Show firstShowing = generalService.getFirstShowAfterCurrentDate();
		java.util.Date showDate = null;
		String showingPremiereSqlDatabase = null;
		 
		if(firstShowing == null) {
			return;	
		}
		 else{showingPremiereSqlDatabase = dateUtils.sqlDatabaseFormat(firstShowing.getShowDate())+" "+dateUtils.timeFormat(firstShowing.getShowTime());
			try {
				showDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(showingPremiereSqlDatabase);
			}catch (ParseException e) {
			throw new ShowException("Something went wrong while parsing premiere datum.", e);
			}
		}

		shows.sort(new Comparator<Show>() {

			public int compare(final Show itemL, final Show itemR) {  //is itemL groter dan itemR? anders bovenaan
				if (itemL.getShowDate().before(itemR.getShowDate())) {
					return -1;
				} else if (itemL.getShowDate().after(itemR.getShowDate())) {
					return 1;
				} else {
					return 0;
				}
			}
		});	
		
		// First object in red box
				String todaysDate = dateUtils.getDate();
				System.out.println(todaysDate);
				
				String firstUpcomingMovie = null;
				// Second object in red box
				try {
					firstUpcomingMovie = generalService.getMovieByMovieId(firstShowing.getMovieId()).getTitle() + " on " + dateUtils.format2(firstShowing.getShowDate())+" at "+ dateUtils.timeFormat(firstShowing.getShowTime());
					System.out.println(firstUpcomingMovie);
				} catch (MovieNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
				// Third object in red box
				String countdown = dateUtils.calculateTime(dateUtils.getSecondsBetween(showDate, dateUtils.getCurrentDate()));
				System.out.println(countdown);
				
				// Objects to sent to the now-showing.jsp
				request.setAttribute("todays_date", todaysDate);
				request.setAttribute("first_upcoming_movie", firstUpcomingMovie);
				request.setAttribute("countdown", countdown);
				request.setAttribute("shows", shows);
		//send model
		
		
		

		// routate to jsp
		getServletContext().getRequestDispatcher("/jsp/now-showing.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
