package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class ShowingBuilder {
	private BigInteger showingId;
	private String movieTitle;
	private String roomName;
	private Date showingDate;
	private Time showingTime;
	
	public ShowingBuilder withShowingId(final BigInteger value) {
		showingId = value;
		return this;
	}
	public ShowingBuilder withMovieTitle(final String value) {
		movieTitle = value;
		return this;
	}
	public ShowingBuilder withRoomName(final String value) {
		roomName = value;
		return this;
	}
	public ShowingBuilder withShowingDate(final Date value) {
		showingDate = value;
		return this;
	}
	public ShowingBuilder withShowingTime(final Time value) {
		showingTime = value;
		return this;
	}
	public Showing build() {
		return new Showing(showingId, movieTitle, roomName, showingDate, showingTime);
	}
}
