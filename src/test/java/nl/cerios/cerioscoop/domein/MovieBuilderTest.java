package nl.cerios.cerioscoop.domein;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.domain.Category;
import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;

public class MovieBuilderTest {

	@Test
	public void test() {
		final Movie movie = new MovieBuilder()
			.withMovieId(BigDecimal.valueOf(12.55))
			.withTitle("kansloze titel")
			.withMinutes(12)
			.withType(3) // 3D
			.withLanguage("Fries")
			.withDescription("bagger b-film")
			.withCategory(Category.COMEDY)
			.build();

		Assert.assertNotNull(movie);
		Assert.assertEquals("kansloze titel", movie.getTitle());
		Assert.assertEquals("12.55", movie.getMovieId().toString());
		Assert.assertEquals(Category.COMEDY, movie.getCategory());
		Assert.assertEquals("Fries", movie.getLanguage());
		Assert.assertEquals(12, movie.getMinutes());
		Assert.assertEquals(3, movie.getMovieType());
		Assert.assertEquals("bagger b-film", movie.getDescription());
	}
}