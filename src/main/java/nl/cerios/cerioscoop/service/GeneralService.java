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

import nl.cerios.cerioscoop.domain.Category;
import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.util.DateUtils;

@Stateless										//Stateless is de status van de gevulde opjecten. Best Practice is stateless.
public class GeneralService {

	@Resource(name = "jdbc/cerioscoop")			//Content Dependency Injection techniek
	private DataSource dataSource;
	
	public List<Movie> getMovies(){
		final List<Movie> movies = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()) {			//AutoCloseable
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT movie_id, title, category, minutes, movie_type, language, description FROM movie");
			while (resultSet.next()) {
				final Movie movie = new MovieBuilder()
						.withMovieId(resultSet.getBigDecimal("movie_id"))
						.withTitle(resultSet.getString("title"))
						.withCategory(Category.valueOf(resultSet.getString("category")))
						.withMinutes(resultSet.getInt("minutes"))
						.withType(resultSet.getInt("movie_type"))
						.withLanguage(resultSet.getString("language"))
						.withDescription(resultSet.getString("description"))
						.build();
				movies.add(movie);
			}
			return movies;
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the movie.", e);
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
	    	throw new ServiceException("Something went terribly wrong while retrieving the first date.", e);
	    }
	}
	
	public List<Customer> getCustomers(){
		final List<Customer> customers = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()){
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT customer_id, first_name, last_name, username, password, email, customer_create_date, customer_create_time FROM customer"); { 

			while (resultSet.next()) {
				final int customerId = resultSet.getInt("customer_id");
				final String firstName = resultSet.getString("first_name");
				final String lastName = resultSet.getString("last_name");
				final String username = resultSet.getString("username");
				final String password = resultSet.getString("password");
				final String email = resultSet.getString("email");
				final Date createDate = resultSet.getDate("customer_create_date");
				final Time createTime = resultSet.getTime("customer_create_time");
				
				customers.add(new Customer(customerId, firstName, lastName, username, password, email, createDate, createTime));
	        	}
	        return customers;
	      }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the customers.", e);
	    }
	}
	
	/**
	 * Returns a first showing record.
	 * 
	 * @return firstShowing
	 */
	public Show getFirstShowAfterCurrentDate(){
		final List<Show> shows = getShows();
		final DateUtils dateUtils = new DateUtils();
		Show firstShow = null;
		
		for (final Show show : shows) {
			if(show.getShowDate().after(dateUtils.getCurrentSqlDate())){	
				if(firstShow == null){			//hier wordt voor 1x eerstVolgendeFilm gevuld					
					firstShow = show;
				}
				else if(show.getShowDate().before(firstShow.getShowDate())){
					firstShow = show;			
				}
			}
		}
		return firstShow;
	}
		
	public Movie getMovieByMovieId(final int movieId) throws FilmNotFoundException {
		final List<Movie> movies = getMovies();
		Movie movieByMovieId = null;
		
		for (final Movie movieItem : movies){
			if (movieItem.getMovieId().intValue() == movieId) {
				movieByMovieId = movieItem;
			}
		}
		return movieByMovieId;
	}
	
	public void registerCustomer(final Customer customer){
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO customer (first_name, last_name, username, password, email, customer_create_date, customer_create_time) VALUES (?,?,?,?,?,?,?);")) {
				
	        	preparedStatement.setString(1, customer.getFirstName());
	        	preparedStatement.setString(2, customer.getLastName());
	        	preparedStatement.setString(3, customer.getUsername());
	        	preparedStatement.setString(4, customer.getPassword());
	        	preparedStatement.setString(5, customer.getEmail());
	        	preparedStatement.setDate(6, customer.getCustomerCreateDate()); 
	        	preparedStatement.setTime(7,customer.getCustomerCreateTime());
	        	preparedStatement.executeUpdate();
	        	
	        	System.out.println("Data inserted.");
		    }catch (final SQLException e) {
		    	throw new ServiceException("Something went wrong while inserting the customer items.", e);
		    }
	}
	/**
	 * @param customer
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * TODO Includeer de employee door het gebruik van een hashmap!
	 */
	public Customer authenticateUser(Customer customer){
		final List<Customer> dbCustomers = getCustomers();	
		final String username = customer.getUsername();
		final String password = customer.getPassword();
		Customer authenticatedUser = null;
		
		for (final Customer customerItem : dbCustomers){
			if(customerItem.getUsername().equals(username) && customerItem.getPassword().equals(password)){
			authenticatedUser = customerItem;
			}
		}
		return authenticatedUser;
	}
}

