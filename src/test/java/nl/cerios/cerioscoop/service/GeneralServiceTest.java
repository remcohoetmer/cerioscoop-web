package nl.cerios.cerioscoop.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.domain.Category;
import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Employee;
import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Showing;
import nl.cerios.cerioscoop.domain.ShowingBuilder;
import nl.cerios.cerioscoop.util.DateUtils;

public class GeneralServiceTest {
	private final GeneralService generalService = new GeneralService();
	private final DateUtils dateUtils = new DateUtils();
	
	@Test
	public void testGetFirstShowAfterCurrentDate() throws ParseException{
	//Shows	
		final Showing showOne = new ShowingBuilder()
				.withShowingId(BigInteger.valueOf(1))
				.withMovieTitle("showOne")
				.withRoomName("Yellow room")
				.withShowingDate(dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("08-01-2020"))))
				.withShowingTime(dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))))
				.build();	
		
		final Showing showTwo = new ShowingBuilder()
				.withShowingId(BigInteger.valueOf(2))
				.withMovieTitle("showTwo")
				.withRoomName("Yellow room")
				.withShowingDate(dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("07-23-2020"))))
				.withShowingTime(dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))))
				.build();	
				
		final Showing showThree = new ShowingBuilder()
				.withShowingId(BigInteger.valueOf(3))
				.withMovieTitle("showThree")
				.withRoomName("Yellow room")
				.withShowingDate(dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-03-2020"))))
				.withShowingTime(dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))))
				.build();	
		
		
	//Putting all movies in a list
		final List<Showing> listOfShows = new ArrayList<>();
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
				.withTitle("top titel")
				.withMinutes(98)
				.withType(3) // 3D
				.withLanguage("Fries")
				.withDescription("bagger v-film")
				.withCategory(Category.COMEDY)
				.build();
		final Movie movieTwo = new MovieBuilder()
				.withMovieId(BigInteger.valueOf(2))
				.withTitle("lekkere titel")
				.withMinutes(453)
				.withType(3) // 3D
				.withLanguage("Grieks")
				.withDescription("bagger v-film")
				.withCategory(Category.COMEDY)
				.build();
		final Movie movieThree = new MovieBuilder()
				.withMovieId(BigInteger.valueOf(3))
				.withTitle("keke titel")
				.withMinutes(9)
				.withType(3) // 3D
				.withLanguage("Twents")
				.withDescription("bagger v-film")
				.withCategory(Category.COMEDY)
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
	public void testAuthenticateCustomer() throws ParseException {
	//Customers
		final Customer customerOne = new Customer(0, "Bauke", "Mollema", "BM", "BM123", "bauke@mollema.com",
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
		
		final Customer customerTwo = new Customer(1, "Tom", "Dumoulin", "TD", "TD123", "tom@dumoulin.com",
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
		
		final Customer customerThree = new Customer(2, "Stef", "Clement", "SC", "SC123", "stef@clement.com",
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
	
	//The no-customer test-user
		final Customer noCustomer = new Customer(3, "Chris", "Froome", "CF", "CF123", "chris@froome.com",
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
		
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
	public void testAuthenticateEmployee() throws ParseException {
	//Employee
		final Employee employeeOne = new Employee(0, "Wout", "Poels", "WP", "WP123", "wout@poels.com",
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
		
		
		final Employee employeeTwo = new Employee(1, "Wilco", "Kelderman", "WK", "WK123", "wilco@kelderman.com",
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
		
		
		final Employee employeeThree = new Employee(2, "Laurens", "tenDam", "LTD", "LTD123", "laurens@tendam.com",
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
		
	//The no-employee test-user
		final Employee noEmployee = new Employee(3, "Tom", "Slagter", "TS", "TS123", "tom@slagter.com",
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
		
	//Putting all customers in a list
		final List<Employee> dbEmployees = new ArrayList<>();
		dbEmployees.add(0, employeeOne);
		dbEmployees.add(1, employeeTwo);
		dbEmployees.add(2, employeeThree);
		
	//Authentication control 
		Assert.assertEquals(employeeOne ,generalService.authenticateEmployee(employeeOne, dbEmployees));
		Assert.assertEquals(employeeTwo ,generalService.authenticateEmployee(employeeTwo, dbEmployees));
		Assert.assertEquals(employeeThree ,generalService.authenticateEmployee(employeeThree, dbEmployees));
		Assert.assertNotEquals(noEmployee ,generalService.authenticateEmployee(noEmployee, dbEmployees));
	}
}
