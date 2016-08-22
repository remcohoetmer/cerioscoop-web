package nl.cerios.cerioscoop.domain;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.util.DateUtils;

public class ShowTest {

	@Test
	public void testInstantiateShow() throws ParseException {
		final Show show = new Show();
		final DateUtils dateUtils = new DateUtils();
		
			show.setShowId(99);		
			show.setMovieId(10);
			show.setRoomId(2);
			show.setShowDate(dateUtils.convertUtilDateToSqlDate(dateUtils.toDate(dateUtils.toDateFormat("09-06-2016"))));
			show.setShowTime(dateUtils.convertUtilDateToSqlTime(dateUtils.toTime(dateUtils.toTimeFormat("20:00:00"))));
				
			Assert.assertNotNull(show);
			Assert.assertEquals(99, show.getShowId());
			Assert.assertEquals(10, show.getMovieId());
			Assert.assertEquals(2, show.getRoomId());
			Assert.assertEquals("2016-09-06", show.getShowDate().toString());  //The actual object is in java.sql.Date!
			Assert.assertEquals("20:00:00", show.getShowTime().toString());
	}

}
