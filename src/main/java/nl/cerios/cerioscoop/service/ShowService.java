package nl.cerios.cerioscoop.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.util.DateUtils;

public class ShowService {

	private static final  Connection CONNECTION = DatabaseConnection.connectionDatabase();
	
	/*TODO rename the SQL field names to full caps names
	 * Example:
	 * film_id 
	 * 
	 * should be:
	 * FILM_ID
	 * 
	 * But the names in the database have to match the new full-caps names to work.
	 * 
	 * 
	 * TODO replace the "System.out.println" with an other alternative.
	 * Once this application is on a server there is no way the client could see those comments
	 * since those will be printed on the server not the client side.
	 * */
	
	public List<Movie> getMovies(){
		try {
			final List<Movie> movies = new ArrayList<>();
			final Statement statement = CONNECTION.createStatement();
			final ResultSet resultSet = statement.executeQuery("select film_id, name, minutes, type, language from film");
			while (resultSet.next()) {
	        	final int movieId = resultSet.getInt("film_id");
	        	final int minutes = resultSet.getInt("minutes");
	        	final int movieType = resultSet.getInt("type");
	        	final String movieName = resultSet.getString("name");
	        	final String language = resultSet.getString("language");
	        	
	        	movies.add(new Movie(movieId, movieName, minutes, movieType, language));
			}
			return movies;
	    }catch (final SQLException e) {
	    	throw new ShowServiceException("Something went terribly wrong while retrieving the first date.", e);
	    }
	}
	
	public List<Show> getShows(){
		final List<Show> shows = new ArrayList<>();
		try {
			final Statement statement = CONNECTION.createStatement();
			final ResultSet resultSet = statement.executeQuery("select showing_id, film_id, room_id, premiere_date, premiere_time, last_showing_date, last_showing_time from showing"); { 
			while (resultSet.next()) {
				final int showingId = resultSet.getInt("showing_id");
				final int filmId = resultSet.getInt("film_id");
				final int roomId = resultSet.getInt("room_id");
				final Date premiereDate = resultSet.getDate("premiere_date");
				final Time premiereTime = resultSet.getTime("premiere_time");
				final Date lastShowingDate = resultSet.getDate("last_showing_date");
				final Time lastShowingTime = resultSet.getTime("last_showing_time");
				
	        	shows.add(new Show(showingId, filmId, roomId, premiereDate, premiereTime, lastShowingDate, lastShowingTime));
	        	}
	        return shows;
	      }
	    }catch (final SQLException e) {
	    	throw new ShowServiceException("Something went terribly wrong while retrieving the first date.", e);
	    }
	}
	
	/**
	 * Returns a first showing record.
	 * 
	 * @return firstShowing
	 */
	public Show getFirstShowingAfterCurrentDate(){
		final List<Show> shows = getShows();
		final DateUtils dateUtils = new DateUtils();
		Show firstShowing = null;
		
		for (final Show show : shows) {
			if(show.getPremiereDate().after(dateUtils.getCurrentSqlDate())){	
				if(firstShowing == null){			//hier wordt voor 1x eerstVolgendeFilm gevuld					
					firstShowing = show;
				}
				else if(show.getPremiereDate().before(firstShowing.getPremiereDate())){
					firstShowing = show;			
				}
			}
		}
		System.out.println(firstShowing);
		return firstShowing;
	}
		
	public Movie getMovieByMovieId(final int movieId) throws FilmNotFoundException {
		final List<Movie> movies = getMovies();
		Movie movieByMovieId = null;
		
		for (final Movie movieItem : movies){
			if(movieItem.getMovieId() == movieId){
				movieByMovieId = movieItem;
			}
		}
		return movieByMovieId;
	}
	
	
	
	public void addMovie(final String newMovieName, final int minutes, final int movieType, final String language) {
		try {
			final PreparedStatement preparedStatement = CONNECTION.prepareStatement(
					"INSERT INTO film (name, minutes, type, language) values (?,?,?,?);");
			preparedStatement.setString(1, newMovieName);
			preparedStatement.setInt(2, minutes);
			preparedStatement.setInt(3, movieType);
			preparedStatement.setString(4, language);
			preparedStatement.executeUpdate();
			
			System.out.println("Data inserted.");
	    }catch (final SQLException e) {
	    	throw new ShowServiceException("Something went wrong while inserting the movie items.", e);
	    }
	}
	
	public void addShow(Show show){		
		try {
			final PreparedStatement preparedStatement = CONNECTION.prepareStatement(
					"INSERT INTO showing (film_id, room_id, premiere_date, premiere_time, last_showing_date, last_showing_time) values (?,?,?,?,?,?);");
        	preparedStatement.setInt(1, show.getMovieId());
        	preparedStatement.setInt(2, show.getRoomId());
        	preparedStatement.setDate(3, show.getPremiereDate());
        	preparedStatement.setTime(4, show.getPremiereTime());
        	preparedStatement.setDate(5, show.getLastShowingDate());
        	preparedStatement.setTime(6, show.getLastShowingTime());
        	preparedStatement.executeUpdate();
        	
        	System.out.println("Data inserted.");
	    }catch (final SQLException e) {
	    	throw new ShowServiceException("Something went wrong while inserting the filmagenda items.", e);
	    }
	}
}

