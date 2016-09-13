package nl.cerios.cerioscoop.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.domain.ShowsPresentationVO;
import nl.cerios.cerioscoop.util.DateUtils;


public class ObjectTest{

	private GeneralService generalService = new GeneralService();
	private final DateUtils dateUtils = new DateUtils();
	
	@Test
	public void test(){
		List<ShowsPresentationVO> todaysShowsTable = new ArrayList<ShowsPresentationVO>();
		List<ShowsPresentationVO> todaysShowsTable2 = new ArrayList<ShowsPresentationVO>();
		Assert.assertEquals(todaysShowsTable, todaysShowsTable2);
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
			final List<ShowsPresentationVO> todaysShowsTableBefore = new ArrayList<ShowsPresentationVO>();
			List<ShowsPresentationVO> todaysShowsTableAfter = new ArrayList<ShowsPresentationVO>();
			final List<Show> testShows = new ArrayList<>();
			final List<Movie> testMovies = new ArrayList<>();		
			
		//checken van de huidige staat van de tabel (leeg)
			Assert.assertEquals(todaysShowsTableBefore, generalService.generateShowTable(testShows, testMovies));
			Assert.assertEquals(0, todaysShowsTableBefore.size());
	        
			for (ShowsPresentationVO showsPresentationVOBefore : todaysShowsTableAfter){
				Assert.assertEquals(0, showsPresentationVOBefore.getMovieId());
				Assert.assertEquals(testShows, showsPresentationVOBefore.getShows());
			}
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
			todaysShowsTableAfter = generalService.generateShowTable(testShows, testMovies);
			Assert.assertNotEquals(todaysShowsTableBefore, todaysShowsTableAfter);
			Assert.assertEquals(3, todaysShowsTableAfter.size());
		//generateShowTable() aanroepen met de testdata
			
		//checken of de movie al bestaat
//			ShowsPresentationVO showsPresentationVOOne = null;
//			for (ShowsPresentationVO showsPresentationVOAfter : todaysShowsTableAfter){
//				showsPresentationVOOne = showsPresentationVOAfter.getMovieId();
//			}
			//Assert.assertEquals(1, showsPresentationVOAfter.getMovieId());
			//Assert.assertEquals(testShows.size(), showsPresentationVOAfter.getShows().size());
		//checken van de uiteindelijke staat van de tabel
			//Assert.assertEquals(testShows	
		
//		    @Test
//		    public void testUpdateMovieFromDatabase() {
//		        final BigInteger idOfMovieToBeUpdated = new BigInteger("1");
//		        final Movie updatedTestMovie = new MovieBuilder()
//		                .withMovieId(idOfMovieToBeUpdated)        //bij het updaten van de movie heb je WEL dezelfde movieId nodig als de testMovie!!
//		                .withCategory(Category.HORROR)
//		                .withTitle("Aangepast in DB")
//		                .withMinutes(100)
//		                .withType(2) // 3D
//		                .withLanguage("SQL+")
//		                .withDescription("bagger b-film")
//		                .withTrailer("#")
//		                .withCover("/images/cover.jpg")
//		                .build();
//		        
//		        final Movie movieBefore = getMovie(idOfMovieToBeUpdated);
//		        Assert.assertNotNull(movieBefore);
//		        Assert.assertNotEquals(updatedTestMovie.getCategory(), movieBefore.getCategory());
//		        Assert.assertNotEquals(updatedTestMovie.getTitle(), movieBefore.getTitle());
//		        Assert.assertNotEquals(updatedTestMovie.getMinutes(), movieBefore.getMinutes());
//		        Assert.assertNotEquals(updatedTestMovie.getMovieType(), movieBefore.getMovieType());
//		        Assert.assertNotEquals(updatedTestMovie.getLanguage(), movieBefore.getLanguage());
//		        Assert.assertNotEquals(updatedTestMovie.getDescription(), movieBefore.getDescription());
//		        Assert.assertNotEquals(updatedTestMovie.getTrailer(), movieBefore.getTrailer());
//		        Assert.assertNotEquals(updatedTestMovie.getCover(), movieBefore.getCover());
//		        
//		        employeeService.updateMovieFromDatabase(updatedTestMovie);

			
		}
}
