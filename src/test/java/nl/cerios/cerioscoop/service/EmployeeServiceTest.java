package nl.cerios.cerioscoop.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import nl.cerios.cerioscoop.domain.Category;
import nl.cerios.cerioscoop.domain.Employee;
import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.util.DateUtils;
import nl.cerios.testutil.DatabaseTest;

public class EmployeeServiceTest extends DatabaseTest {

	@InjectMocks
	private EmployeeService employeeService;
	
	@InjectMocks
	private GeneralService generalService;

	private final DateUtils dateUtils = new DateUtils();
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddMovie() {
		// Get a collection of all movieID's that are currently in the database.
		final Set<BigInteger> movieIDsBefore = generalService.getMovies().stream()
				.map(Movie::getMovieId)
				.collect(Collectors.toSet());
		
		final Movie testMovie = new MovieBuilder()		//let op bij het inserten van de movie is geen movieId nodig!!
				.withCategory(Category.valueOf("COMEDY"))
				.withTitle("Gaat in DB")
				.withMinutes(90)
				.withType(3) // 3D
				.withLanguage("SQL")
				.withDescription("bagger d-film")
				.withTrailer("#")
				.build();
		
		employeeService.addMovie(testMovie);
		
		// Get a list of movies that have just been inserted in the database.
		final List<Movie> insertedMovies = generalService.getMovies();
		insertedMovies.removeIf(movie -> movieIDsBefore.contains(movie.getMovieId()));
		
		Assert.assertEquals(1,  insertedMovies.size());
		final Movie insertedMovie = insertedMovies.get(0);
		Assert.assertEquals(testMovie.getCategory(), insertedMovie.getCategory());
		Assert.assertEquals(testMovie.getTitle(), insertedMovie.getTitle());
		Assert.assertEquals(testMovie.getMinutes(), insertedMovie.getMinutes());
		Assert.assertEquals(testMovie.getMovieType(), insertedMovie.getMovieType());
		Assert.assertEquals(testMovie.getLanguage(), insertedMovie.getLanguage());
		Assert.assertEquals(testMovie.getDescription(), insertedMovie.getDescription());
		Assert.assertEquals(testMovie.getTrailer(), insertedMovie.getTrailer());
	}

	@Test
	public void testUpdateMovieFromDatabase() {
		final BigInteger idOfMovieToBeUpdated = new BigInteger("1");
		final Movie updatedTestMovie = new MovieBuilder()
				.withMovieId(idOfMovieToBeUpdated)		//bij het updaten van de movie heb je WEL dezelfde movieId nodig als de testMovie!!
				.withCategory(Category.HORROR)
				.withTitle("Aangepast in DB")
				.withMinutes(100)
				.withType(2) // 3D
				.withLanguage("SQL+")
				.withDescription("bagger b-film")
				.withTrailer("#")
				.build();
		
		final Movie movieBefore = getMovie(idOfMovieToBeUpdated);
		Assert.assertNotNull(movieBefore);
		Assert.assertNotEquals(updatedTestMovie.getCategory(), movieBefore.getCategory());
		Assert.assertNotEquals(updatedTestMovie.getTitle(), movieBefore.getTitle());
		Assert.assertNotEquals(updatedTestMovie.getMinutes(), movieBefore.getMinutes());
		Assert.assertNotEquals(updatedTestMovie.getMovieType(), movieBefore.getMovieType());
		Assert.assertNotEquals(updatedTestMovie.getLanguage(), movieBefore.getLanguage());
		Assert.assertNotEquals(updatedTestMovie.getDescription(), movieBefore.getDescription());
		Assert.assertNotEquals(updatedTestMovie.getTrailer(), movieBefore.getTrailer());
		
		employeeService.updateMovieFromDatabase(updatedTestMovie);

		final Movie movieAfter = getMovie(idOfMovieToBeUpdated);
		Assert.assertNotNull(movieAfter);
		Assert.assertEquals(updatedTestMovie.getCategory(), movieAfter.getCategory());
		Assert.assertEquals(updatedTestMovie.getTitle(), movieAfter.getTitle());
		Assert.assertEquals(updatedTestMovie.getMinutes(), movieAfter.getMinutes());
		Assert.assertEquals(updatedTestMovie.getMovieType(), movieAfter.getMovieType());
		Assert.assertEquals(updatedTestMovie.getLanguage(), movieAfter.getLanguage());
		Assert.assertEquals(updatedTestMovie.getDescription(), movieAfter.getDescription());
		Assert.assertEquals(updatedTestMovie.getTrailer(), movieAfter.getTrailer());
	}

	@Test
	public void testDeleteMovieFromDatabase() {
		final List<Movie> moviesBefore = generalService.getMovies();
		Assert.assertFalse(moviesBefore.isEmpty());
		final BigInteger idOfMovieToBeDeleted = moviesBefore.get(0).getMovieId();
		
		employeeService.deleteMovieFromDatabase(idOfMovieToBeDeleted.intValue());
		
		final List<Movie> moviesAfter = generalService.getMovies();
		Assert.assertEquals(moviesBefore.size() - 1, moviesAfter.size());
		
		final Movie movieAfter = getMovie(idOfMovieToBeDeleted);
		Assert.assertNull(movieAfter);
	}
	
	@Test
	public void testUpdateShowFromDatabase() throws ParseException {
		final int idOfShowToBeUpdated = 1;
		final Show updatedTestShow = new Show(idOfShowToBeUpdated,2,3, 
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("21:00:00"))),0);
		
		final Show showBefore = getShow(idOfShowToBeUpdated);
		Assert.assertNotNull(showBefore);
		Assert.assertNotEquals(updatedTestShow.getMovieId(), showBefore.getMovieId());
		Assert.assertNotEquals(updatedTestShow.getRoomId(), showBefore.getRoomId());
		Assert.assertNotEquals(updatedTestShow.getShowDate(), showBefore.getShowDate());
		Assert.assertNotEquals(updatedTestShow.getShowTime(), showBefore.getShowTime());
		
		employeeService.updateShowFromDatabase(updatedTestShow);

		final Show showAfter = getShow(idOfShowToBeUpdated);
		Assert.assertNotNull(showAfter);
		Assert.assertEquals(updatedTestShow.getMovieId(), showAfter.getMovieId());
		Assert.assertEquals(updatedTestShow.getRoomId(), showAfter.getRoomId());
		Assert.assertEquals(updatedTestShow.getShowDate(), showAfter.getShowDate());
		// Compare java.sql.Time objects by their String values, to prevent differences in milliseconds from failing the test.
		Assert.assertEquals(updatedTestShow.getShowTime().toString(), showAfter.getShowTime().toString());
	}
	@Test
	public void testNewEmployee() throws ParseException {
		final int idOfEmployeeToBeRegistered = 2;
		final Employee employeeOne = new Employee(idOfEmployeeToBeRegistered, "Eddy", "Merckx ", "EM", "EM123", "eddy@merckx.com",
				dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("15-09-2017"))),
				dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
		
		final Employee employeeBefore = getEmployee(idOfEmployeeToBeRegistered);
		Assert.assertNull(employeeBefore);
		
		employeeService.newEmployee(employeeOne);

		final Employee employeeAfter = getEmployee(idOfEmployeeToBeRegistered);
		Assert.assertNotNull(employeeAfter);
		Assert.assertEquals(employeeOne.getFirstName(), employeeAfter.getFirstName());
		Assert.assertEquals(employeeOne.getLastName(), employeeAfter.getLastName());
		Assert.assertEquals(employeeOne.getUsername(), employeeAfter.getUsername());
		Assert.assertEquals(employeeOne.getPassword(), employeeAfter.getPassword());
		Assert.assertEquals(employeeOne.getEmail(), employeeAfter.getEmail());
		Assert.assertEquals(employeeOne.getCreateDate(), employeeAfter.getCreateDate());
		// Compare java.sql.Time objects by their String values, to prevent differences in milliseconds from failing the test.
		Assert.assertEquals(employeeOne.getCreateTime().toString(), employeeAfter.getCreateTime().toString());
	}
	
	
	@Test
	public void testDeleteShowFromDatabase() {
		final int idOfShowToBeDeleted = 1;
		
		final Show showBefore = getShow(idOfShowToBeDeleted);
		Assert.assertNotNull(showBefore);
		
		employeeService.deleteShowFromDatabase(idOfShowToBeDeleted);

		final Show showAfter = getShow(idOfShowToBeDeleted);
		Assert.assertNull(showAfter);
	}

	private Movie getMovie(final BigInteger movieID) {
		return generalService.getMovies().stream()
				.filter(m -> m.getMovieId().equals(movieID))
				.findAny()
				.orElse(null);
	}

	private Show getShow(final int showID) {
		return generalService.getShows().stream()
				.filter(s -> s.getShowId() == showID)
				.findAny()
				.orElse(null);
	}
	private Employee getEmployee(final int employeeID) {
		return generalService.getEmployees().stream()
				.filter(e -> e.getEmployeeId() == employeeID)
				.findAny()
				.orElse(null);
	}
}
