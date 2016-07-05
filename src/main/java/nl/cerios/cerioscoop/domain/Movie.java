package nl.cerios.cerioscoop.domain;

import java.math.BigDecimal;

public final class Movie {
	
	private BigDecimal movieId;
	private Category category;
	private String title;
	private int minutes;
	private int movieType;
	private String language;
	private String description;

	Movie(final BigDecimal movieId, final String title, final Category category, final int minutes, final int movieType, final String language, final String description) {
		this.movieId = movieId;
		this.title = title;
		this.category = category;
		this.minutes = minutes;
		this.movieType = movieType;
		this.language = language;
		this.description = description;
	}

	public BigDecimal getMovieId() {
		return movieId;
	}
	
	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getMovieType() {
		return movieType;
	}

	public String getLanguage() {
		return language;
	}

	public String getDescription() {
		return description;
	}
}



