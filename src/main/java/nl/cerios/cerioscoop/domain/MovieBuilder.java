package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;

public final class MovieBuilder {
	
	private BigInteger movieId;
	private String movieTitle;
	private String movieDescription;

	public MovieBuilder withMovieId(final BigInteger value) {
		movieId = value;
		return this;
	}

	public MovieBuilder withMovieTitle(final String value) {
		movieTitle = value;
		return this;
	}

	public MovieBuilder withMovieDescription(final String value) {
		movieDescription = value;
		return this;
	}

	
	public Movie build() {
		return new Movie(movieId, movieTitle, movieDescription);
	}
}



