package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class ShowPresentation {
	private BigInteger showingId;
	private String movieTitle;
	private String roomName;
	private Date showingDate;
	private Time showingTime;
	private BigInteger chairAmount;
	
	ShowPresentation(final BigInteger showingId, final String movieTitle, final String roomName, final Date showingDate, final Time showingTime, final BigInteger chairAmount) {
		this.showingId = showingId;
		this.movieTitle = movieTitle;
		this.roomName = roomName;
		this.showingDate = showingDate;
		this.showingTime = showingTime;
		this.chairAmount = chairAmount;
	}
	
	public BigInteger getShowingId() {
		return showingId;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public String getRoomName() {
		return roomName;
	}
	public Date getShowingDate() {
		return showingDate;
	}
	public Time getShowingTime() {
		return showingTime;
	}	
	public BigInteger getChairAmount() {
		return chairAmount;
	}
}
