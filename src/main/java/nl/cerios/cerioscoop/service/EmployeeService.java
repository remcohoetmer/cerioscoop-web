package nl.cerios.cerioscoop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.Room;
import nl.cerios.cerioscoop.domain.Show;

@Stateless
public class EmployeeService {

	@Resource(name = "jdbc/cerioscoop")			//Content Dependency Injection techniek
	private DataSource dataSource;
	
	public void addMovie(final Movie newMovie) {
		try (final Connection connection = dataSource.getConnection()){
			final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO movie (category, title, minutes, movie_type, language, description) VALUES (?,?,?,?,?,?);");
			preparedStatement.setString(1, newMovie.getCategory().name());
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

	public void addRoom(final Room room) {
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO room (room_id, name, chair_amount, room_type) VALUES (?,?,?,?);")) {
				
	        	preparedStatement.setInt(1, room.getRoomId());
	        	preparedStatement.setString(2, room.getName());
	        	preparedStatement.setInt(3, room.getChairAmount());
	        	preparedStatement.setInt(4, room.getRoomType());
	        	preparedStatement.executeUpdate();
	        	
	        	System.out.println("Data inserted.");
		    }catch (final SQLException e) {
		    	throw new ShowServiceException("Something went wrong while inserting the room items.", e);
		    }
	}
	
	public void deleteMovieFromDatabase(int movie_id) {
		
		String deleteSQL = "DELETE FROM movie WHERE movie_id = ?";
		
		try {
			final Connection connection = dataSource.getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, movie_id);
			preparedStatement.executeUpdate();
	        	
			System.out.println("Record is deleted.");
		    }catch (final SQLException e) {
		    	throw new ShowServiceException("Something went wrong while deleting the movie items.", e);
		    }
	}
}
