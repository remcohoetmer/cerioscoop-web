package nl.cerios.cerioscoop.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.domain.ShowPresentation;
import nl.cerios.cerioscoop.domain.ShowPresentationBuilder;
import nl.cerios.cerioscoop.domain.ShowsPresentationVO;
import nl.cerios.cerioscoop.util.DateUtils;
import nl.cerios.testutil.DatabaseTest;

public class GeneralServiceTest extends DatabaseTest {

	@InjectMocks
	private GeneralService generalService;
	private final DateUtils dateUtils = new DateUtils();

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetMovies() {
		final List<Movie> movies = generalService.getMovies();

		Assert.assertNotNull(movies);
		Assert.assertEquals(7, movies.size());
	}
	
	@Test
	public void testGetShows() {
		final List<Show> shows = generalService.getShows();

		Assert.assertNotNull(shows);
		Assert.assertEquals(7, shows.size());
	}
		
	@Test
	public void testGetCustomers() {
		final List<Customer> customers = generalService.getCustomers();

		Assert.assertNotNull(customers);
		Assert.assertEquals(4, customers.size());
	}
	
	@Test
	public void testGetShowings() {
		final List<ShowPresentation> showings = generalService.getShowings();

		Assert.assertNotNull(showings);
		Assert.assertEquals(7, showings.size());
	}
	
	
	@Test
	public void testGetFirstShowAfterCurrentDate() throws ParseException{
	//Shows	
		final ShowPresentation showOne = new ShowPresentationBuilder()
				.withShowingId(BigInteger.valueOf(1))
				.withMovieTitle("showOne")
				.withShowingDate(dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("08-01-2020"))))
				.withShowingTime(dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))))
				.build();	
		
		final ShowPresentation showTwo = new ShowPresentationBuilder()
				.withShowingId(BigInteger.valueOf(2))
				.withMovieTitle("showTwo")
				.withShowingDate(dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("07-23-2020"))))
				.withShowingTime(dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))))
				.build();	
				
		final ShowPresentation showThree = new ShowPresentationBuilder()
				.withShowingId(BigInteger.valueOf(3))
				.withMovieTitle("showThree")
				.withShowingDate(dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-03-2020"))))
				.withShowingTime(dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))))
				.build();	
		
		
	//Putting all movies in a list
		final List<ShowPresentation> listOfShows = new ArrayList<>();
		listOfShows.add(0, showOne);
		listOfShows.add(1, showTwo);
		listOfShows.add(2, showThree);
		
	//First show after the current date control 
		Assert.assertNotEquals(showOne.getShowingDate() ,generalService.getFirstShowAfterCurrentDate(listOfShows).getShowingDate());
		Assert.assertEquals(showTwo.getShowingDate() ,generalService.getFirstShowAfterCurrentDate(listOfShows).getShowingDate());
		Assert.assertNotEquals(showThree.getShowingDate() ,generalService.getFirstShowAfterCurrentDate(listOfShows).getShowingDate());
	}
	@Test
	public void testGetMovieByMovieId() throws MovieNotFoundException{
	//Movies	
		final Movie movieOne = new MovieBuilder()
				.withMovieId(BigInteger.valueOf(1))
				.withMovieTitle("top titel")
				.withMovieDescription("bagger v-film")
				.build();
		final Movie movieTwo = new MovieBuilder()
				.withMovieId(BigInteger.valueOf(2))
				.withMovieTitle("lekkere titel")
				.withMovieDescription("bagger v-film")
				.build();
		final Movie movieThree = new MovieBuilder()
				.withMovieId(BigInteger.valueOf(3))
				.withMovieTitle("keke titel")
				.withMovieDescription("bagger v-film")
				.build();
		
	//Putting all movies in a list
		final List<Movie> listOfMovies = new ArrayList<>();
		listOfMovies.add(0, movieOne);
		listOfMovies.add(1, movieTwo);
		listOfMovies.add(2, movieThree);
		
	//Movie control 
			Assert.assertEquals(movieOne ,generalService.getMovieByMovieId(movieOne.getMovieId().intValue(), listOfMovies));
			Assert.assertEquals(movieTwo ,generalService.getMovieByMovieId(movieTwo.getMovieId().intValue(), listOfMovies));
			Assert.assertEquals(movieThree ,generalService.getMovieByMovieId(movieThree.getMovieId().intValue(), listOfMovies));
	}
	
	@Test
	public void testRegisterCustomer() throws ParseException {
		final int idOfCustomerToBeRegistered = 4;
		final Customer customerOne = new Customer(idOfCustomerToBeRegistered, "Michael", "Boogerd", "MB", "MB123", "michael@boogerd.com");
		
		final Customer customerBefore = getCustomer(idOfCustomerToBeRegistered);
		Assert.assertNull(customerBefore);
		
		generalService.registerCustomer(customerOne);

		final Customer customerAfter = getCustomer(idOfCustomerToBeRegistered);
		Assert.assertNotNull(customerAfter);
		Assert.assertEquals(customerOne.getFirstName(), customerAfter.getFirstName());
		Assert.assertEquals(customerOne.getLastName(), customerAfter.getLastName());
		Assert.assertEquals(customerOne.getUsername(), customerAfter.getUsername());
		Assert.assertEquals(customerOne.getPassword(), customerAfter.getPassword());
		Assert.assertEquals(customerOne.getEmail(), customerAfter.getEmail());
	}
	
	@Test
	public void testAuthenticateCustomer() throws ParseException {
	//Customers
		final Customer customerOne = new Customer(0, "Bauke", "Mollema", "BM", "BM123", "bauke@mollema.com");
		
		final Customer customerTwo = new Customer(1, "Tom", "Dumoulin", "TD", "TD123", "tom@dumoulin.com");
		
		final Customer customerThree = new Customer(2, "Stef", "Clement", "SC", "SC123", "stef@clement.com");
	
	//The no-customer test-user
		final Customer noCustomer = new Customer(3, "Chris", "Froome", "CF", "CF123", "chris@froome.com");
		
	//Putting all customers in a list
		final List<Customer> dbCustomers = new ArrayList<>();
		dbCustomers.add(0, customerOne);
		dbCustomers.add(1, customerTwo);
		dbCustomers.add(2, customerThree);
		
	//Authentication control 
		Assert.assertEquals(customerOne ,generalService.authenticateCustomer(customerOne, dbCustomers));
		Assert.assertEquals(customerTwo ,generalService.authenticateCustomer(customerTwo, dbCustomers));
		Assert.assertEquals(customerThree ,generalService.authenticateCustomer(customerThree, dbCustomers));
		Assert.assertNotEquals(noCustomer ,generalService.authenticateCustomer(noCustomer, dbCustomers));
	}
	
	@Test
	public void testAuthenticateUser() {
		final Customer testUser = null;
		Assert.assertEquals(false, generalService.authenticateUser(testUser));
		final Customer testCustomer = new Customer(1, "Marcel", "Groothuis", "Manollo7G", "secret", "mjg@cerios.nl");
		Assert.assertEquals(true, generalService.authenticateUser(testCustomer));
	}
	
	@Test 
	public void testGenerateShowTable() throws ParseException, MovieNotFoundException {
	//Shows
		final Show showOne = new Show(0, 1, 
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("12-09-2016"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("18:00:00"))));
		final Show showTwo = new Show(0, 2, 
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("12-09-2016"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("19:00:00"))));
		final Show showThree = new Show(0, 3, 
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("12-09-2016"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
		final Show showFour = new Show(0, 2, 
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("12-09-2016"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("10:00:00"))));
		final Show showFive = new Show(0, 1, 
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("12-09-2016"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
	//Movies	
		final Movie movieOne = new MovieBuilder()
				.withMovieId(BigInteger.valueOf(1))
				.withMovieTitle("top titel")
				.withMovieDescription("bagger v-film")
				.build();
		final Movie movieTwo = new MovieBuilder()
				.withMovieId(BigInteger.valueOf(2))
				.withMovieTitle("lekkere titel")
				.withMovieDescription("bagger v-film")
				.build();
		final Movie movieThree = new MovieBuilder()
				.withMovieId(BigInteger.valueOf(3))
				.withMovieTitle("keke titel")
				.withMovieDescription("bagger v-film")
				.build();
		
	//Lijsten maken objecten gelijkheid, gelijk is als ze dezelfde instance zijn
		final List<ShowsPresentationVO> todaysShowsTable = new ArrayList<ShowsPresentationVO>();
		final List<Show> testShows = new ArrayList<>();
		final List<Movie> testMovies = new ArrayList<>();
		
	//checken van de huidige staat van de tabel (leeg)
		Assert.assertEquals(todaysShowsTable, generalService.generateShowTable(testShows, testMovies));
		
		
	//shows lijst vullen
		testShows.add(0, showOne);	
		testShows.add(1, showTwo);	
		testShows.add(2, showThree);	
		testShows.add(3, showFour);	
		testShows.add(4, showFive);	
		
	//movies lijst vullen
		testMovies.add(0, movieOne);
		testMovies.add(1, movieTwo);
		testMovies.add(2, movieThree);
		
	//checken of de tabel niet leeg is!
		Assert.assertNotEquals(todaysShowsTable, generalService.generateShowTable(testShows, testMovies));
		
	//generateShowTable() aanroepen met de testdata
		
	//checken of de movie al bestaat
		
	
	//checken van de uiteindelijke staat van de tabel
		//Assert.assertEquals(testShows	
	
		
	}
	
	
	private Customer getCustomer(final int customerID) {
		return generalService.getCustomers().stream()
				.filter(c -> c.getCustomerId() == customerID)
				.findAny()
				.orElse(null);
	}
}
