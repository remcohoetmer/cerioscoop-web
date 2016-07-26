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
import nl.cerios.cerioscoop.domain.Employee;
import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.domain.ShowingList;
import nl.cerios.cerioscoop.domain.ShowingListBuilder;
import nl.cerios.cerioscoop.domain.User;
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
						.withMovieId(resultSet.getBigDecimal("movie_id").toBigInteger())
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
	
	public List<Employee> getEmployees(){
		final List<Employee> employees = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()){
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT employee_id, first_name, last_name, username, password, email, employee_create_date, employee_create_time FROM employee"); { 

			while (resultSet.next()) {
				final int employeeId = resultSet.getInt("employee_id");
				final String firstName = resultSet.getString("first_name");
				final String lastName = resultSet.getString("last_name");
				final String username = resultSet.getString("username");
				final String password = resultSet.getString("password");
				final String email = resultSet.getString("email");
				final Date createDate = resultSet.getDate("employee_create_date");
				final Time createTime = resultSet.getTime("employee_create_time");
				
				employees.add(new Employee(employeeId, firstName, lastName, username, password, email, createDate, createTime));
	        	}
	        return employees;
	      }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the employees.", e);
	    }
	}
	
	public List<ShowingList> getShowingList(){
		final List<ShowingList> showingList = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()){
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT show_id, title, room_name, show_date, show_time FROM showing_list"); { 

			while (resultSet.next()) {
				final ShowingList showing = new ShowingListBuilder()
						.withShowingListId(resultSet.getBigDecimal("show_id").toBigInteger())
						.withMovieTitle(resultSet.getString("title"))
						.withRoomName(resultSet.getString("room_name"))
						.withShowingListDate(resultSet.getDate("show_date"))
						.withShowingListTime(resultSet.getTime("show_time"))
						.build();			
				showingList.add(showing);
	        	}
	        return showingList;
	      }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the ShowingList.", e);
	    }
	}
	/**
	 * Returns a first showing record.
	 * 
	 * @return firstShowing
	 */
	public ShowingList getFirstShowAfterCurrentDate(final List<ShowingList> listOfShows){
		final List<ShowingList> shows = listOfShows;
		final DateUtils dateUtils = new DateUtils();
		ShowingList firstShow = null;
		
		for (final ShowingList show : shows) {
			if(show.getShowingListDate().after(dateUtils.getCurrentSqlDate())){	
				if(firstShow == null){			//hier wordt voor 1x eerstVolgendeFilm gevuld					
					firstShow = show;
				}
				else if(show.getShowingListDate().before(firstShow.getShowingListDate())){
					firstShow = show;			
				}
			}
		}
		return firstShow;
	}
		
	public Movie getMovieByMovieId(final int movieId, final List<Movie> listOfMovies) throws MovieNotFoundException {
		final List<Movie> movies = listOfMovies;
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
	        	preparedStatement.setDate(6, customer.getCreateDate()); 
	        	preparedStatement.setTime(7,customer.getCreateTime());
	        	preparedStatement.executeUpdate();
	        	
	        	System.out.println("Data inserted.");
		    }catch (final SQLException e) {
		    	throw new ServiceException("Something went wrong while inserting the customer items.", e);
		    }
	}
	
	public User authenticateCustomer(User customer, List<Customer> listOfCustomers){
		final List<Customer> dbCustomers = listOfCustomers;		
		final String usernameCustomer = customer.getUsername();
		final String passwordCustomer = customer.getPassword();
		User authenticatedCustomer = null;
		
		for (final Customer customerItem : dbCustomers){
			if(customerItem.getUsername().equals(usernameCustomer) && customerItem.getPassword().equals(passwordCustomer)){
				authenticatedCustomer = customerItem;
				}
		}
		return authenticatedCustomer;
	}
	
	
	public User authenticateEmployee(User employee, List<Employee> listOfEmployees){
		final List<Employee> dbEmployees = listOfEmployees;	
		final String usernameEmployee = employee.getUsername();
		final String passwordEmployee = employee.getPassword();
		User authenticatedEmployee = null;
		
		for (final Employee employeeItem : dbEmployees){
			if(employeeItem.getUsername().equals(usernameEmployee) && employeeItem.getPassword().equals(passwordEmployee)){
				authenticatedEmployee = employeeItem;
				}
		}
		return authenticatedEmployee;
	}
	
	public Boolean authenticateUser(User authenticatedUser){
		if(authenticatedUser == null){
			return false;
		}
		return true;
	}
}

