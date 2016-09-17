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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Show;

@Stateless
public class DataAccessObject {
	
	private static Logger LOG = LoggerFactory.getLogger(DataAccessObject.class);
	
	@Resource(name = "jdbc/cerioscoop")			//Content Dependency Injection techniek
	private DataSource dataSource;

	public List<Movie> getMovies(){
		final List<Movie> movies = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()) {			//AutoCloseable
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT movie_id, title, movie_description FROM movie");
			while (resultSet.next()) {
				final Movie movie = new MovieBuilder()
						.withMovieId(resultSet.getBigDecimal("movie_id").toBigInteger())
						.withMovieTitle(resultSet.getString("title"))
						.withMovieDescription(resultSet.getString("movie_description"))
						.build();
				movies.add(movie);
			}
			LOG.debug("Fetched {} movies.", movies.size());
			return movies;
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the movie.", e);
	    }
	}
	
	public List<Show> getShowsForToday(){
		final List<Show> shows = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()){
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT show_id, movie_id, show_date, show_time FROM show_table WHERE show_date = CURDATE()"); { 

			while (resultSet.next()) {
				final int showId = resultSet.getInt("show_id");
				final int movieId = resultSet.getInt("movie_id");
				final Date showDate = resultSet.getDate("show_date");
				final Time showTime = resultSet.getTime("show_time");
				shows.add(new Show(showId, movieId, showDate, showTime));
        	}
			LOG.debug("Fetched {} shows.", shows.size());
        return shows;
	      }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the ShowingList.", e);
	    }
	}
	
    public void deleteCustomerByUsername(String username) {
    	try (final Connection connection = dataSource.getConnection()) {
			final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE username = ?");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();

			LOG.debug("Customer is deleted.");
        }catch (final SQLException e) {
            throw new ServiceException("Something went wrong while deleting the customer items.", e);
        }
    }

}
