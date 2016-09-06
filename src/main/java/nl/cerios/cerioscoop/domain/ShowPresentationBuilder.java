package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class ShowPresentationBuilder {
	private BigInteger showId;
	private String movieTitle;
	private Date showingDate;
	private Time showingTime;

	
	public ShowPresentationBuilder withShowId(final BigInteger value) {
		showId = value;
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
	public ShowPresentationBuilder withShowingTime(final Time value) {
		showingTime = value;
		return this;
	}

	public ShowPresentation build() {
		return new ShowPresentation(showId, movieTitle, showingDate, showingTime);
	}
}

