package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class ShowingList {
	private BigInteger showingListId;
	private String movieTitle;
	private String roomName;
	private Date showingListDate;
	private Time showingListTime;
	
	ShowingList(final BigInteger showingListId, final String movieTitle, final String roomName, final Date showingListDate, final Time showingListTime) {
		this.showingListId = showingListId;
		this.movieTitle = movieTitle;
		this.roomName = roomName;
		this.showingListDate = showingListDate;
		this.showingListTime = showingListTime;
	}
	
	public BigInteger getShowingListId() {
		return showingListId;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public String getRoomName() {
		return roomName;
	}
	public Date getShowingListDate() {
		return showingListDate;
	}
	public Time getShowingListTime() {
		return showingListTime;
	}
}
