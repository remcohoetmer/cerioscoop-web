package nl.cerios.cerioscoop.domain;

public class Movie {
	private int movieId;
	private int categoryId;
	private String title;
	private int minutes;
	private int movieType;
	private String language;
	private String description;

	public Movie(){
	}
	public Movie(final int movieId, final String title, final int minutes, final int movieType, final String language) {
		this.movieId = movieId;
		this.title = title;
		this.minutes = minutes;
		this.movieType = movieType;
		this.language = language;
	}

	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public int getMovieType() {
		return movieType;
	}
	public void setMovieType(int movieType) {
		this.movieType = movieType;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}



