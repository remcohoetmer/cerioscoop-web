package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class ShowPresentationBuilder {
	private BigInteger showingId;
	private String movieTitle;
	private Date showingDate;
	private Time showingTime;

	public ShowPresentationBuilder withShowingId(final BigInteger value) {
		showingId = value;
		return this;
	}
	
	public ShowPresentationBuilder withMovieTitle(final String value) {
		movieTitle = value;
		return this;
	}
	
	public ShowPresentationBuilder withShowingDate(final Date value) {
		showingDate = value;
		return this;
	}
	
	public ShowPresentationBuilder withShowingTime(final Time time) {
		showingTime = time;
		return this;
	}

	public ShowPresentation build() {
		return new ShowPresentation(showingId, movieTitle, showingDate, showingTime);
	}
}
