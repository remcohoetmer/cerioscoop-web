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
		Assert.assertEquals(3, customers.size());
	}	
	
	@Test
	public void testGetFirstShowforToday() throws ParseException{
	//Shows	
		final Show showOne = new Show(0, 1, 
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("07-20-2020"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
		final Show showTwo = new Show(0, 2, 
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("07-23-2020"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
		final Show showThree = new Show(0, 3, 
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-03-2020"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));	
		
	//Putting all movies in a list
		final List<Show> listOfShows = new ArrayList<>();
		listOfShows.add(0, showOne);
		listOfShows.add(1, showTwo);
		listOfShows.add(2, showThree);
		
	//First show after the current date control 
		Assert.assertEquals(showOne.getShowDate() ,generalService.getFirstShowforToday(listOfShows).getShowDate());
		Assert.assertNotEquals(showTwo.getShowDate() ,generalService.getFirstShowforToday(listOfShows).getShowDate());
		Assert.assertNotEquals(showThree.getShowDate() ,generalService.getFirstShowforToday(listOfShows).getShowDate());
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
			
		//Lijsten maken
			final List<ShowsPresentationVO> todaysShowsTableBefore = new ArrayList<ShowsPresentationVO>();
			List<ShowsPresentationVO> todaysShowsTableAfter = new ArrayList<ShowsPresentationVO>();
			final List<Show> testShows = new ArrayList<>();
			final List<Movie> testMovies = new ArrayList<>();		
		
			List<Show> movieOneShows = new ArrayList<>();
			List<Show> movieTwoShows = new ArrayList<>();
			List<Show> movieThreeShows = new ArrayList<>();        
			ShowsPresentationVO showsPresentationVORowOne = null;
			ShowsPresentationVO showsPresentationVORowTwo = null;
			ShowsPresentationVO showsPresentationVORowThree = null;
			
		//Before: de todaysShowsTable vullen met de lege shows en movies.
			todaysShowsTableAfter = generalService.generateShowTable(testShows, testMovies);
		
		//Before checken van de huidige staat van de todaysShowsTable (leeg)
			for (ShowsPresentationVO showsPresentationVOBefore : todaysShowsTableBefore){
				if(movieOne.getMovieId().intValue() == showsPresentationVOBefore.getMovieId()){
					showsPresentationVORowOne = showsPresentationVOBefore;
					}else if(movieTwo.getMovieId().intValue() == showsPresentationVOBefore.getMovieId()){
						showsPresentationVORowTwo = showsPresentationVOBefore;
					}else if(movieThree.getMovieId().intValue() == showsPresentationVOBefore.getMovieId()){
						showsPresentationVORowThree = showsPresentationVOBefore;
					}
			}
			
			Assert.assertEquals(todaysShowsTableBefore, generalService.generateShowTable(testShows, testMovies));
			Assert.assertEquals(testMovies.size(), todaysShowsTableBefore.size());
			Assert.assertNull(showsPresentationVORowOne);
			Assert.assertNull(showsPresentationVORowTwo);
			Assert.assertNull(showsPresentationVORowThree);
			
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
			
		//De todaysShowsTable vullen met de shows en movies.
			todaysShowsTableAfter = generalService.generateShowTable(testShows, testMovies);
			
		//checken of de tabel niet leeg is!
			Assert.assertNotEquals(todaysShowsTableBefore, todaysShowsTableAfter);
			Assert.assertEquals(testMovies.size(), todaysShowsTableAfter.size());
		
		//generateShowTable() aanroepen met de testdata
			for (Show show : testShows){
				if(show.getMovieId() == movieOne.getMovieId().intValue()){
					movieOneShows.add(show);
				}else if(show.getMovieId() == movieTwo.getMovieId().intValue()){
					movieTwoShows.add(show);
				}else if(show.getMovieId() == movieThree.getMovieId().intValue()){
					movieThreeShows.add(show);
				}
			}
		//checken of de movie al bestaat
			for (ShowsPresentationVO showsPresentationVOAfter : todaysShowsTableAfter){
				if(movieOne.getMovieId().intValue() == showsPresentationVOAfter.getMovieId()){
				showsPresentationVORowOne = showsPresentationVOAfter;
				}else if(movieTwo.getMovieId().intValue() == showsPresentationVOAfter.getMovieId()){
					showsPresentationVORowTwo = showsPresentationVOAfter;
				}else if(movieThree.getMovieId().intValue() == showsPresentationVOAfter.getMovieId()){
					showsPresentationVORowThree = showsPresentationVOAfter;
				}
			}
			Assert.assertEquals(movieOne.getMovieId().intValue(), showsPresentationVORowOne.getMovieId());
			Assert.assertEquals(movieOne.getTitle(), showsPresentationVORowOne.getMovieTitle());
			Assert.assertEquals(movieOneShows.size(), showsPresentationVORowOne.getShows().size());				
			Assert.assertEquals(movieTwo.getMovieId().intValue(), showsPresentationVORowTwo.getMovieId());
			Assert.assertEquals(movieTwo.getTitle(), showsPresentationVORowTwo.getMovieTitle());
			Assert.assertEquals(movieTwoShows.size(), showsPresentationVORowTwo.getShows().size());
			Assert.assertEquals(movieThree.getMovieId().intValue(), showsPresentationVORowThree.getMovieId());
			Assert.assertEquals(movieThree.getTitle(), showsPresentationVORowThree.getMovieTitle());
			Assert.assertEquals(movieThreeShows.size(), showsPresentationVORowThree.getShows().size());
		}
	
	
	private Customer getCustomer(final int customerID) {
		return generalService.getCustomers().stream()
				.filter(c -> c.getCustomerId() == customerID)
				.findAny()
				.orElse(null);
	}
}
