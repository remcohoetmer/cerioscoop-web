package nl.cerios.cerioscoop.service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.text.ParseException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import nl.cerios.cerioscoop.domain.Category;
import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.util.DateUtils;
import nl.cerios.testutil.DatabaseTest;

public class EmployeeServiceTest extends DatabaseTest {

	@InjectMocks
	private EmployeeService employeeService;

	private final DateUtils dateUtils = new DateUtils();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private PrintStream sysOut;
	private PrintStream sysErr;
	private final Movie updatedTestMovie = new MovieBuilder()
			.withMovieId(BigInteger.valueOf(4))				//bij het updaten van de movie heb je WEL dezelfde movieId nodig als de testMovie!!
			.withCategory(Category.ACTION)
			.withTitle("Aangepast in DB")
			.withMinutes(100)
			.withType(2) // 3D
			.withLanguage("SQL+")
			.withDescription("bagger b-film")
			.withTrailer("#")
			.build();
	private Show updatedTestShow;
	

	@Before
	public void setUpStreams() {
		sysOut = System.out;
		sysErr = System.err;
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@Before
	public void createTestShow() throws ParseException {
		updatedTestShow = new Show(1,1,1, 
			dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))),
			dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
	}
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddMovie() {
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
		Assert.assertEquals("Data inserted.", outContent.toString().trim());
	}

	@Test
	public void testUpdateMovieFromDatabase() {
		employeeService.updateMovieFromDatabase(updatedTestMovie);
		Assert.assertNotNull(updatedTestMovie);
		Assert.assertEquals("Movie is updated.", outContent.toString().trim());
	}

	@Test
	public void testDeleteMovieFromDatabase() {
		employeeService.deleteMovieFromDatabase(updatedTestMovie.getMovieId().intValue());
		Assert.assertNotNull(updatedTestMovie);
		Assert.assertEquals("Movie is deleted.", outContent.toString().trim());
	}
	
	@Test
	public void testUpdateShowFromDatabase() throws ParseException{
		employeeService.updateShowFromDatabase(updatedTestShow);
		Assert.assertNotNull(updatedTestShow);
		Assert.assertEquals("Show is updated.", outContent.toString().trim());
	}
	
	@Test
	public void testDeleteShowFromDatabase() throws ParseException{
		employeeService.deleteShowFromDatabase(updatedTestShow.getShowId());
		Assert.assertNotNull(updatedTestShow);
		Assert.assertEquals("Show is deleted.", outContent.toString().trim());
	}

	@After
	public void cleanUpStreams() {
		System.setOut(sysOut);
		System.setErr(sysErr);
	}
}
