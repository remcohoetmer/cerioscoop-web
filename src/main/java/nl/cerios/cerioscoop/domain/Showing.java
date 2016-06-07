package nl.cerios.cerioscoop.domain;

import java.sql.Date;
import java.sql.Time;

public class Showing {
	private int showingID;
	private int filmID;
	private int roomID;
	private Date premiereDate;
	private Time premiereTime;
	private Date lastShowingDate;
	private Time lastShowingTime;
	
	public Showing() {
		
	}
	public Showing(int showingID, int filmID, int roomID, Date premiereDate, Time premiereTime, Date lastShowingDate, Time lastShowingTime) {
		this.showingID = showingID;
		this.filmID = filmID;
		this.roomID = roomID; 
		this.premiereDate = premiereDate;
		this.premiereTime = premiereTime;
		this.lastShowingDate = lastShowingDate;
		this.lastShowingTime = lastShowingTime;
		
	}
	public int getShowingID() {
		return showingID;
	}
	public void setShowingID(int showingID) {
		this.showingID = showingID;
	}
	public int getFilmID() {
		return filmID;
	}
	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public Date getPremiereDate() {
		return premiereDate;
	}
	public void setPremiereDate(Date premiereDate) {
		this.premiereDate = premiereDate;
	}
	public Time getPremiereTime() {
		return premiereTime;
	}
	public void setPremiereTime(Time premiereTime) {
		this.premiereTime = premiereTime;
	}
	public Date getLastShowingDate() {
		return lastShowingDate;
	}
	public void setLastShowingDate(Date lastShowingDate) {
		this.lastShowingDate = lastShowingDate;
	}
	public Time getLastShowingTime() {
		return lastShowingTime;
	}
	public void setLastShowingTime(Time lastShowingTime) {
		this.lastShowingTime = lastShowingTime;
	}
	
	
	
}
