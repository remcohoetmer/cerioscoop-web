package nl.cerios.cerioscoop.domain;

import java.sql.Date;
import java.sql.Time;

public class Show {
	private int showId;
	private int movieId;
	private int roomId;
	private Date showDate;
	private Time showTime;
	
	public Show() {
	}
	public Show(final int showId, final int movieId, final int roomId, final Date showDate, final Time showTime) {
		this.showId = showId;
		this.movieId = movieId;
		this.roomId = roomId; 
		this.showDate = showDate;
		this.showTime = showTime;
	}
	public int getShowId() {
		return showId;
	}
	public void setShowId(final int showId) {
		this.showId = showId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(final int movieId) {
		this.movieId = movieId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(final int roomId) {
		this.roomId = roomId;
	}
	public Date getShowDate() {
		return showDate;
	}
	public void setShowDate(final Date showDate) {
		this.showDate = showDate;
	}
	public Time getShowTime() {
		return showTime;
	}
	public void setShowTime(final Time showTime) {
		this.showTime = showTime;
	}
}
