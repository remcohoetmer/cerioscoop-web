package nl.cerios.cerioscoop.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.testutil.DatabaseIT;

public class GeneralServiceIT extends DatabaseIT {

	@InjectMocks
	private GeneralService generalService;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetMovies() {
		final List<Movie> movies = generalService.getMovies();

		Assert.assertNotNull(movies);
		Assert.assertEquals(3, movies.size());
	}
}
