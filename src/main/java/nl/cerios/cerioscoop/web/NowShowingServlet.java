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

import nl.cerios.cerioscoop.domain.ShowingList;
import nl.cerios.cerioscoop.domain.ShowingListBuilder;
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
		final List<ShowingList> showingList = generalService.getShowingList();
		final List<ShowingList> NowShowingList = new ArrayList<ShowingList>();
		final ShowingList firstShowing = generalService.getFirstShowAfterCurrentDate(showingList);

		// Sort the shows by their showDate/showTime, oldest first.
		showingList.sort((itemL, itemR) -> {
			int compare = itemL.getShowingListDate().compareTo(itemR.getShowingListDate());
			if (compare == 0) {
				compare = itemL.getShowingListTime().compareTo(itemR.getShowingListTime());
			}
			return compare;
		});
		
		//Building the actualShowingList to show only the shows after the current date
		for (ShowingList show : showingList){
			if (show.getShowingListDate().after(dateUtils.getCurrentDate())){
				show = new ShowingListBuilder()
					.withShowingListId(show.getShowingListId())
					.withMovieTitle(show.getMovieTitle())
					.withRoomName(show.getRoomName())
					.withShowingListDate(show.getShowingListDate())
					.withShowingListTime(show.getShowingListTime())
					.build();			
				NowShowingList.add(show);
			}
		}
		request.setAttribute("actualShows", NowShowingList);
		
		// Third object in red box
		final Date showDateTime = DateUtils.toDateTime(firstShowing.getShowingListDate(),
				firstShowing.getShowingListTime());
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
