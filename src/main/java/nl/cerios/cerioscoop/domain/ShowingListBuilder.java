package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class ShowingListBuilder {
	private BigInteger showingListId;
	private String movieTitle;
	private String roomName;
	private Date showingListDate;
	private Time showingListTime;
	
	public ShowingListBuilder withShowingListId(final BigInteger value) {
		showingListId = value;
		return this;
	}
	public ShowingListBuilder withMovieTitle(final String value) {
		movieTitle = value;
		return this;
	}
	public ShowingListBuilder withRoomName(final String value) {
		roomName = value;
		return this;
	}
	public ShowingListBuilder withShowingListDate(final Date value) {
		showingListDate = value;
		return this;
	}
	public ShowingListBuilder withShowingListTime(final Time value) {
		showingListTime = value;
		return this;
	}
	public ShowingList build() {
		return new ShowingList(showingListId, movieTitle, roomName, showingListDate, showingListTime);
	}
}
