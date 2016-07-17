package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.service.GeneralService;
import nl.cerios.cerioscoop.service.MovieNotFoundException;
import nl.cerios.cerioscoop.service.ServiceException;
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

		final List<Show> shows = generalService.getShows();
		final Show firstShowing = generalService.getFirstShowAfterCurrentDate();

		// Sort the shows by their showDate/showTime, oldest first.
		shows.sort((itemL, itemR) -> {
			int compare = itemL.getShowDate().compareTo(itemR.getShowDate());
			if (compare == 0) {
				compare = itemL.getShowTime().compareTo(itemR.getShowTime());
			}
			return compare;
		});

		if (firstShowing != null) {
			// Second object in red box
			final String firstUpcomingMovie;
			try {
				firstUpcomingMovie = generalService.getMovieByMovieId(firstShowing.getMovieId()).getTitle() + " on "
						+ dateUtils.format2(firstShowing.getShowDate()) + " at "
						+ dateUtils.timeFormat(firstShowing.getShowTime());
			} catch (MovieNotFoundException e) {
				throw new ServiceException("Something went terribly wrong while finding the movie.", e);
			}

			// Third object in red box
			final Date showDateTime = DateUtils.toDateTime(firstShowing.getShowDate(), firstShowing.getShowTime());
			final String countdown = dateUtils
					.calculateTime(dateUtils.getSecondsBetween(showDateTime, dateUtils.getCurrentDate()));

			// Objects to sent to the now-showing.jsp
			request.setAttribute("first_upcoming_movie", firstUpcomingMovie);
			request.setAttribute("countdown", countdown);
		}

		// Objects to sent to the now-showing.jsp
		request.setAttribute("todays_date", dateUtils.getDate());
		request.setAttribute("shows", shows);

		// route to jsp
		getServletContext().getRequestDispatcher("/jsp/now-showing.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
