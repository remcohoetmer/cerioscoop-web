package nl.cerios.cerioscoop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import nl.cerios.cerioscoop.domain.Employee;
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
					"INSERT INTO movie (category, title, minutes, movie_type, language, description, trailer) VALUES (?,?,?,?,?,?,?);");
			preparedStatement.setString(1, newMovie.getCategory().name());
			preparedStatement.setString(2, newMovie.getTitle());
			preparedStatement.setInt(3, newMovie.getMinutes());
			preparedStatement.setInt(4, newMovie.getMovieType());
			preparedStatement.setString(5, newMovie.getLanguage());
			preparedStatement.setString(6, newMovie.getDescription());
			preparedStatement.setString(7, newMovie.getTrailer());
			preparedStatement.executeUpdate();
			
			System.out.println("Data inserted.");
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went wrong while inserting the movie items.", e);
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
	    	throw new ServiceException("Something went wrong while inserting the filmagenda items.", e);
	    }
	}

	public void addRoom(final Room room) {
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO room (room_id, room_name, chair_amount, room_type) VALUES (?,?,?,?);")) {
				
        	preparedStatement.setInt(1, room.getRoomId());
        	preparedStatement.setString(2, room.getName());
        	preparedStatement.setInt(3, room.getChairAmount());
        	preparedStatement.setInt(4, room.getRoomType());
        	preparedStatement.executeUpdate();
        	
        	System.out.println("Data inserted.");
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went wrong while inserting the room items.", e);
	    }
	}
	
	public void deleteMovieFromDatabase(int movie_id) {
		String deleteSQL = "DELETE FROM movie WHERE movie_id = ?";
		
		try {
			final Connection connection = dataSource.getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, movie_id);
			preparedStatement.executeUpdate();
	        	
			System.out.println("Movie is deleted.");
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went wrong while deleting the movie items.", e);
	    }
	}
	
	public void updateMovieFromDatabase(Movie updateMovie) {         
		try {
	        final Connection connection = dataSource.getConnection();
	        final PreparedStatement preparedStatement = 
	        connection.prepareStatement("UPDATE movie SET category = ?, title = ?, minutes = ?, movie_type = ?, language = ?, description = ?, trailer = ? WHERE movie_id = ?");
		                  
	        preparedStatement.setString(1, updateMovie.getCategory().name());
	        preparedStatement.setString(2, updateMovie.getTitle());
	        preparedStatement.setInt(3, updateMovie.getMinutes());
		    preparedStatement.setInt(4, updateMovie.getMovieType());
		    preparedStatement.setString(5, updateMovie.getLanguage());
		    preparedStatement.setString(6, updateMovie.getDescription());
			preparedStatement.setString(7, updateMovie.getTrailer());
		    preparedStatement.setInt(8, updateMovie.getMovieId().intValue());
		    preparedStatement.executeUpdate();
		              
		    System.out.println("Movie is updated.");
		 }catch (final SQLException e) {
			 throw new ServiceException("Something went wrong while updating the movie items.", e);
		 }
    }

	public void deleteShowFromDatabase(int showId) {
			String deleteSQL = "DELETE FROM `show` WHERE show_id = ?";
			
			try {
				final Connection connection = dataSource.getConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, showId);
				preparedStatement.executeUpdate();
		        	
				System.out.println("Show is deleted.");
		    }catch (final SQLException e) {
		    	throw new ServiceException("Something went wrong while deleting the show items.", e);
		    }
		}
	
	public void updateShowFromDatabase(final Show show){
			              
	          String updateSQL = "UPDATE `show` SET movie_id = ?, room_id = ?, show_date = ?, show_time = ? WHERE show_id = ?";
			              
	          try {
		           final Connection connection = dataSource.getConnection();
		           final PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
			                  
		           preparedStatement.setInt(1, show.getMovieId());
			       preparedStatement.setInt(2, show.getRoomId());
			       preparedStatement.setDate(3, show.getShowDate());
			       preparedStatement.setTime(4, show.getShowTime());
			       preparedStatement.setInt(5, show.getShowId());
			       preparedStatement.executeUpdate();
			                      
		           System.out.println("Show is updated.");
		       }catch (final SQLException e) {
		           throw new ServiceException("Something went wrong while updating the show items.", e);
		       }
			              
	}
	
	public void newEmployee(final Employee customer){
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO employee (first_name, last_name, username, password, email, employee_create_date, employee_create_time) VALUES (?,?,?,?,?,?,?);")) {
				
	        	preparedStatement.setString(1, customer.getFirstName());
	        	preparedStatement.setString(2, customer.getLastName());
	        	preparedStatement.setString(3, customer.getUsername());
	        	preparedStatement.setString(4, customer.getPassword());
	        	preparedStatement.setString(5, customer.getEmail());
	        	preparedStatement.setDate(6, customer.getCreateDate()); 
	        	preparedStatement.setTime(7,customer.getCreateTime());
	        	preparedStatement.executeUpdate();
	        	
	        	System.out.println("Data inserted.");
		    }catch (final SQLException e) {
		    	throw new ServiceException("Something went wrong while inserting the employee items.", e);
			    }
		}
}