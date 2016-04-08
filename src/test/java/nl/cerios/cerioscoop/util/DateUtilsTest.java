package nl.cerios.cerioscoop.util;

import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void testGetDaysBetween() throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		String inputString1 = "23-01-1997";
		String inputString2 = "27-01-1997";
		Date date1 = myFormat.parse(inputString1);
		Date date2 = myFormat.parse(inputString2);
		
		int daysBetween = DateUtils.getDaysBetween(date1, date2);
		Assert.assertEquals(4, daysBetween);
		daysBetween = DateUtils.getDaysBetween(date2, date1);
		Assert.assertEquals(4, daysBetween);
		daysBetween = DateUtils.getDaysBetween(date1, date1);
		Assert.assertEquals(0, daysBetween);
	}

}
