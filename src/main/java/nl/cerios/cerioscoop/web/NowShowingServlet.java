package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.ShowPresentation;
import nl.cerios.cerioscoop.domain.ShowPresentationBuilder;
import nl.cerios.cerioscoop.service.GeneralService;
import nl.cerios.cerioscoop.util.DateUtils;

/**
 * Servlet implementation class NowShowingServlet
 */
@WebServlet("/NowShowingServlet")
public class NowShowingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB // call DB
	private GeneralService generalService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final DateUtils dateUtils = new DateUtils();
		final List<ShowPresentation> showing = generalService.getShowings();
		final List<ShowPresentation> nowShowing = new ArrayList<ShowPresentation>();
		final ShowPresentation firstShowing = generalService.getFirstShowAfterCurrentDate(showing);

		// Sort the shows by their showDate/showTime, oldest first.
		showing.sort((itemL, itemR) -> {
			int compare = itemL.getShowingDate().compareTo(itemR.getShowingDate());
			if (compare == 0) {
				compare = itemL.getShowingTime().compareTo(itemR.getShowingTime());
			}
			return compare;
		});
		
		//Building the NowShowingList to show only the shows after the current date
		for (ShowPresentation show : showing){
			if (show.getShowingDate().after(dateUtils.getCurrentDate())){
				show = new ShowPresentationBuilder()
					.withShowingId(show.getShowingId())
					.withMovieTitle(show.getMovieTitle())
					.withRoomName(show.getRoomName())
					.withChairAmount(show.getChairAmount())
					.withShowingDate(show.getShowingDate())
					.withShowingTime(show.getShowingTime())
					.build();			
				nowShowing.add(show);
			}
		}
		request.setAttribute("nowShowing", nowShowing);
		
		// Third object in red box
		final Date showDateTime = DateUtils.toDateTime(firstShowing.getShowingDate(),
				firstShowing.getShowingTime());
		final String countdown = dateUtils
				.calculateTime(dateUtils.getSecondsBetween(showDateTime, dateUtils.getCurrentDate()));

		// Objects to sent to the now-showing.jsp
		request.setAttribute("first_upcoming_movie", firstShowing.getMovieTitle());
		request.setAttribute("countdown", countdown);
		request.setAttribute("todays_date", dateUtils.getDate());

		// route to jsp
		getServletContext().getRequestDispatcher("/jsp/now-showing.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
