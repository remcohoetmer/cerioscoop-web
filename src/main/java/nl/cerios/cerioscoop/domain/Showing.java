package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class Showing {
	private BigInteger showingId;
	private String movieTitle;
	private String roomName;
	private Date showingDate;
	private Time showingTime;
	
	Showing(final BigInteger showingId, final String movieTitle, final String roomName, final Date showingDate, final Time showingTime) {
		this.showingId = showingId;
		this.movieTitle = movieTitle;
		this.roomName = roomName;
		this.showingDate = showingDate;
		this.showingTime = showingTime;
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
}
