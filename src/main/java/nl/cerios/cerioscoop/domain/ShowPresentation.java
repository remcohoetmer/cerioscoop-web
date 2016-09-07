package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class ShowPresentation {
	private BigInteger showingId;
	private String movieTitle;
	private Date showingDate;
	private Time showingTime;


	
	ShowPresentation(final BigInteger showingId, final String movieTitle, 
			final Date showingDate, final Time showingTime) {
		this.showingId = showingId;
		this.movieTitle = movieTitle;
		this.showingDate = showingDate;
		this.showingTime = showingTime;
	}
	
	public BigInteger getShowingId() {
		return showingId;
	}
	public String getMovieTitle() {
		return movieTitle;
	}

	public Date getShowingDate() {
		return showingDate;
	}
	public Time getShowingTime() {
		return showingTime;
	}	

}


