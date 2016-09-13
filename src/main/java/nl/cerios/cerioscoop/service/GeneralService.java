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

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.domain.ShowPresentation;
import nl.cerios.cerioscoop.domain.ShowPresentationBuilder;
import nl.cerios.cerioscoop.domain.ShowsPresentationVO;
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
			final ResultSet resultSet = statement.executeQuery("SELECT movie_id, title, description FROM movie");
			while (resultSet.next()) {
				final Movie movie = new MovieBuilder()
						.withMovieId(resultSet.getBigDecimal("movie_id").toBigInteger())
						.withMovieTitle(resultSet.getString("title"))
						.withMovieDescription(resultSet.getString("description"))
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
			final ResultSet resultSet = statement.executeQuery("SELECT show_id, movie_id, room_id, show_date, show_time, available_places, show_price FROM show_table"); { 
			while (resultSet.next()) {
				final int showId = resultSet.getInt("show_id");
				final int movieId = resultSet.getInt("movie_id");
				final int roomId = resultSet.getInt("room_id");
				final Date showDate = resultSet.getDate("show_date");
				final Time showTime = resultSet.getTime("show_time");
				final int availablePlaces = resultSet.getInt("available_places");
				final float showPrice = resultSet.getInt("show_price");
				
	        	shows.add(new Show(showId, movieId, roomId, showDate, showTime, availablePlaces, showPrice));
	        	}
			System.out.println(shows);
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
			final ResultSet resultSet = statement.executeQuery("SELECT customer_id, first_name, last_name, username, password, email FROM customer"); { 

			while (resultSet.next()) {
				final int customerId = resultSet.getInt("customer_id");
				final String firstName = resultSet.getString("first_name");
				final String lastName = resultSet.getString("last_name");
				final String username = resultSet.getString("username");
				final String password = resultSet.getString("password");
				final String email = resultSet.getString("email");

				customers.add(new Customer(customerId, firstName, lastName, username, password, email));
	        	}
	        return customers;
	      }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the customers.", e);
	    }
	}
		
	public List<ShowPresentation> getShowings(){
		final List<ShowPresentation> showings = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()){
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT show_id, title, show_date, show_time FROM show_presentation"); { 

			while (resultSet.next()) {
				final ShowPresentation show = new ShowPresentationBuilder()
						.withShowingId(resultSet.getBigDecimal("show_id").toBigInteger())
						.withMovieTitle(resultSet.getString("title"))
						.withShowingDate(resultSet.getDate("show_date"))
						.withShowingTime(resultSet.getTime("show_time"))
						.build();			
				showings.add(show);
	        	}
	        return showings;
	      }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the ShowingList.", e);
	    }
	}
	
	public List<Show> getTodaysShows(){
		//syso
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
        return shows;
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
	public ShowPresentation getFirstShowAfterCurrentDate(final List<ShowPresentation> listOfShows){
		final List<ShowPresentation> shows = listOfShows;
		final DateUtils dateUtils = new DateUtils();
		ShowPresentation firstShow = null;
		
		for (final ShowPresentation show : shows) {
			if(show.getShowingDate().after(dateUtils.getCurrentSqlDate())){	
				if(firstShow == null){			//hier wordt voor 1x eerstVolgendeFilm gevuld					
					firstShow = show;
				}
				else if(show.getShowingDate().before(firstShow.getShowingDate())){
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
						"INSERT INTO customer (first_name, last_name, username, password, email) VALUES (?,?,?,?,?)")) {
				
	        	preparedStatement.setString(1, customer.getFirstName());
	        	preparedStatement.setString(2, customer.getLastName());
	        	preparedStatement.setString(3, customer.getUsername());
	        	preparedStatement.setString(4, customer.getPassword());
	        	preparedStatement.setString(5, customer.getEmail());
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
	
	public Boolean authenticateUser(User authenticatedUser){
		if(authenticatedUser == null){
			return false;
		}
		return true;
	}
	
	public List<ShowsPresentationVO> generateShowTable(final List<Show> shows, final List<Movie> movies) throws MovieNotFoundException {
		List<ShowsPresentationVO> todaysShowsTable = new ArrayList<ShowsPresentationVO>();

		// voeg alle shows toe aan de tabel
		for (Show show : shows) {
			ShowsPresentationVO existingShowRow = null; // checkt of de movie van de huidige tabel al is opgenomen
			for (ShowsPresentationVO showsRowIter : todaysShowsTable) {
				if (show.getMovieId() == showsRowIter.getMovieId()) {// hier bestaat de movie al in de index
					showsRowIter.shows.add(show);
					existingShowRow = showsRowIter;
				}
			}
			if (existingShowRow == null) {//
				ShowsPresentationVO newShowRow = new ShowsPresentationVO();
				List<Show> showRow = new ArrayList<Show>();
				showRow.add(show);
				newShowRow.setMovieId(show.getMovieId());
				newShowRow.setShows(showRow);
				todaysShowsTable.add(newShowRow);
			}
		}
		for (ShowsPresentationVO showsPresentationVO : todaysShowsTable) {
			String row_title = getMovieByMovieId(showsPresentationVO.getMovieId(), movies).getTitle();
			showsPresentationVO.setMovieTitle(row_title);
		}
		// data integriteit, constraint in de DB ondervangt verkeerde data
		// Logbestand maken, gelogd worden als technische 
		// standaard applicatielog en daar moet de melding terecht komen
		// Nu is de tabel todaysShowsTable gevuld met de juiste waarden
		// Dan kan deze worden gesorteerd. Eerst de kolommen en daarna de rijen.
		// Unittest wat is de toestand van een rij of een kolom wanneer je een operatie/actie hebt uitgevoerd
		// 
		return todaysShowsTable;
	}
		
}


