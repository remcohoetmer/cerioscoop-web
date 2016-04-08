package nl.cerios.cerioscoop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

	public static int getDaysBetween(Date startDate, Date endDate) {
		long diff = Math.abs(endDate.getTime() - startDate.getTime());
		long daysBetween = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		System.out.println("Days: " + daysBetween);

		return (int) daysBetween;
	}
  
}
