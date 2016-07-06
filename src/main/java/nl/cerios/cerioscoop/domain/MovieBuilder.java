package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;

public final class MovieBuilder {
	
	private BigInteger movieId;
	private String title;
	private Category category;
	private int minutes;
	private int movieType;
	private String language;
	private String description;

	public MovieBuilder withMovieId(final BigInteger value) {
		movieId = value;
		return this;
	}

	public MovieBuilder withTitle(final String value) {
		title = value;
		return this;
	}
	
	public MovieBuilder withCategory(final Category value) {
		category = value;
		return this;
	}

	public MovieBuilder withMinutes(final int value) {
		minutes = value;
		return this;
	}

	public MovieBuilder withType(final int value) {
		movieType = value;
		return this;
	}

	public MovieBuilder withLanguage(final String value) {
		language = value;
		return this;
	}

	public MovieBuilder withDescription(final String value) {
		description = value;
		return this;
	}

	public Movie build() {
		return new Movie(movieId, title, category, minutes, movieType, language, description);
	}
}



