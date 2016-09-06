package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;

public final class Movie {

	private BigInteger movieId;
	private String movieTitle;
	private String movieDescription;

	Movie(final BigInteger movieId, final String movieTitle, final String movieDescription) {
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieDescription = movieDescription;

	}

	public BigInteger getMovieId() {
		return movieId;
	}

	public String getTitle() {
		return movieTitle;
	}

	public String getDescription() {
		return movieDescription;
	}

}
