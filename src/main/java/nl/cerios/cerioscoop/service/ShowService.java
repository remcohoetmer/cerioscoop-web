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

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.util.DateUtils;

@Stateless										//Stateless is de status van de gevulde opjecten. Best Practice is stateless.
public class ShowService {

	@Resource(name = "jdbc/cerioscoop")			//Content Dependency Injection techniek
	private DataSource dataSource;
	
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
		try (final Connection connection = dataSource.getConnection()) {			//AutoCloseable
			final List<Movie> movies = new ArrayList<>();
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT movie_id, title, minutes, movie_type, language FROM movie");
			while (resultSet.next()) {
	        	final int movieId = resultSet.getInt("movie_id");
	        	final String movieTitle = resultSet.getString("title");
	        	final int minutes = resultSet.getInt("minutes");
	        	final int movieType = resultSet.getInt("movie_type");
	        	final String language = resultSet.getString("language");
	        	
	        	movies.add(new Movie(movieId, movieTitle, minutes, movieType, language));
			}
			return movies;
	    }catch (final SQLException e) {
	    	throw new ShowServiceException("Something went terribly wrong while retrieving the movie.", e);
	    }
	}
	
	public List<Show> getShows(){
		final List<Show> shows = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()){
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT show_id, movie_id, room_id, show_date, show_time FROM `show`"); { 
			while (resultSet.next()) {
				final int showId = resultSet.getInt("show_id");
				final int movieId = resultSet.getInt("movie_id");
				final int roomId = resultSet.getInt("room_id");
				final Date showDate = resultSet.getDate("show_date");
				final Time showTime = resultSet.getTime("show_time");
				
	        	shows.add(new Show(showId, movieId, roomId, showDate, showTime));
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
			if(show.getShowDate().after(dateUtils.getCurrentSqlDate())){	
				if(firstShowing == null){			//hier wordt voor 1x eerstVolgendeFilm gevuld					
					firstShowing = show;
				}
				else if(show.getShowDate().before(firstShowing.getShowDate())){
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
	
	public void addMovie(final Movie newMovie) {
		try (final Connection connection = dataSource.getConnection()){
			final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO movie (category_id, title, minutes, movie_type, language, description) VALUES (?,?,?,?,?,?);");
			preparedStatement.setInt(1, newMovie.getCategoryId());
			preparedStatement.setString(2, newMovie.getTitle());
			preparedStatement.setInt(3, newMovie.getMinutes());
			preparedStatement.setInt(4, newMovie.getMovieType());
			preparedStatement.setString(5, newMovie.getLanguage());
			preparedStatement.setString(6, newMovie.getDescription());
			preparedStatement.executeUpdate();
			
			System.out.println("Data inserted.");
	    }catch (final SQLException e) {
	    	throw new ShowServiceException("Something went wrong while inserting the movie items.", e);
	    }
	}
	
	public void addShow(final Show show){		
		try (final Connection connection = dataSource.getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO `show` (movie_id, room_id, show_date, show_time) VALUES (?,?,?,?);")) {
			
        	preparedStatement.setInt(1, show.getMovieId());
        	preparedStatement.setInt(2, show.getRoomId());
        	preparedStatement.setDate(3, show.getShowDate());
        	preparedStatement.setTime(4, show.getShowTime());
        	preparedStatement.executeUpdate();
        	
        	System.out.println("Data inserted.");
	    }catch (final SQLException e) {
	    	throw new ShowServiceException("Something went wrong while inserting the filmagenda items.", e);
	    }
	}
}

