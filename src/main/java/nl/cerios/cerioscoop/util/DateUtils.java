package nl.cerios.cerioscoop.util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import nl.cerios.cerioscoop.domain.FilmAgendaItem;
import nl.cerios.cerioscoop.service.FilmAgendaItemService;

/**
 * @author rsanders
 *
 *	http://stackoverflow.com/questions/4216745/java-string-to-date-conversion
 */
public class DateUtils {
	private static final String DATE_FORMAT = "dd-MM-yyyy";
	private static final String DATE_FORMAT2 = "dd MMMMM";
	private static final String DATE_FORMAT3 = "yyyy-MM-dd";
	private static final String TIME_FORMAT = "HH:mm";
	
	public static int getDaysBetween(Date startDate, Date endDate) {
		long diff = Math.abs(endDate.getTime() - startDate.getTime());
		long daysBetween = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		System.out.println("Days: " + daysBetween);

		return (int) daysBetween;
	}
	/**
	 * Returns a first film record of the type FilmAgendaItem.
	 * 
	 * @return eerstVolgendeFilm
	 */
	public FilmAgendaItem getFirstFilmAfterCurrentDate(){
		List<FilmAgendaItem> items = FilmAgendaItemService.getFilmAgendaItems2();
		FilmAgendaItem eerstVolgendeFilm = null;
		
		for (FilmAgendaItem eerstVolgendeFilmKandidaat : items) {
			if(eerstVolgendeFilmKandidaat.getDatum().after(getCurrentSqlDate())){	
				if(eerstVolgendeFilm == null){			//hier wordt voor 1x eerstVolgendeFilm gevuld					
					eerstVolgendeFilm = eerstVolgendeFilmKandidaat;
				}
				else if(eerstVolgendeFilmKandidaat.getDatum().before(eerstVolgendeFilm.getDatum())){
					eerstVolgendeFilm = eerstVolgendeFilmKandidaat;			
				}
			}
		}
		return eerstVolgendeFilm;
	}
	
	public String convertCurrentSqlDateToString() {
		DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		String text = df.format(getCurrentSqlDate());
		System.out.println(text);
		return text;
	}
	/**
	 * http://www.java2s.com/Code/JavaAPI/java.sql/PreparedStatementsetTimeintparameterIndexTimex.htm
	 * 
	 * @return
	 */
	public java.sql.Date getCurrentSqlDate() {
		java.util.Date date = Calendar.getInstance().getTime();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}
	public java.sql.Time getCurrentSqlTime() {
		java.util.Date date = new java.util.Date();
		Time sqlTime = new Time(date.getTime());
		return sqlTime;
	}
	
	/**
	 * It returns a date object converted into a string with format "dd-MM-yyyy".
	 * 
	 * @param datum
	 * @return String "dd-MM-yyyy"
	 */
	public String format(Date datum) {
	    return datum != null ? new SimpleDateFormat(DATE_FORMAT).format(datum) : "onbekend";
	 }
	
	/**
	 * It returns a date object converted into a string with format "dd MMMMM".
	 * 
	 * @param datum
	 * @return String "dd MMMMM"
	 */
	public String format2(Date datum) {
	    return datum != null ? new SimpleDateFormat(DATE_FORMAT2).format(datum) : "onbekend";
	 }
	
	/**
	 * It returns a date object converted into a string with format "dd MMMMM".
	 * 
	 * @param datum
	 * @return String "dd MMMMM"
	 */
	public String format3(Date datum) {
	    return datum != null ? new SimpleDateFormat(DATE_FORMAT3, Locale.FRANCE).format(datum) : "onbekend";
	 }
	
	/**
	 * It returns a time object converted into a string with format "HH:mm".
	 * 
	 * @param tijd
	 * @return String "HH:mm"
	 */
	public String formatTijd(Date tijd) {
	    return tijd != null ? new SimpleDateFormat(TIME_FORMAT).format(tijd) : "onbekend";
	 }
	
	public String getDate() {
		return new SimpleDateFormat("d MMMM, HH:mm").format(new Date());
	}
  
}
