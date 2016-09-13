package nl.cerios.cerioscoop.domain;

import java.util.List;

public class ShowsPresentationVO {
	private int movieId;
	public String movieTitle;
	public List<Show> shows;
	
	public ShowsPresentationVO() {
		
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}
	
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	
	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> show) {
		this.shows = show;
	}

}
