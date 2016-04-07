package nl.cerios.cerioscoop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nl.cerios.cerioscoop.domain.FilmAgendaItem;

public class FilmAgendaItemService {

	public static List<FilmAgendaItem> getFilmAgendaItems(){
		    
		try {
			Connection conn = FilmAgendaDatabaseConnection.connectionDatabase();
			Statement stmt = conn.createStatement();

			List<FilmAgendaItem> items = new ArrayList<>();
			try (ResultSet rs = stmt.executeQuery("SELECT id, datum, tijd, titel FROM FilmAgenda")) {

		        while (rs.next()) {
		        	long id = rs.getLong("id");
		        	Date sqlDatum = rs.getDate("datum");
		        	Date sqlTijd = rs.getTime("tijd");
		        	String titel = rs.getString("titel");
		        	
		        	Date datum = sqlDatum == null ? null : new Date(sqlDatum.getTime());
		        	Date tijd = sqlTijd == null ? null : new Date(sqlTijd.getTime());
		        	items.add(new FilmAgendaItem(id, titel, datum, tijd));
		        	}
		        return items;
		      }
		    }catch (SQLException e) {
		    	throw new FilmAgendaServiceException("Something went terribly wrong while retrieving the filmagenda.", e);
		    }
	}
	
	public static void setFilmAgendaItems(){
		java.sql.Date date = getCurrentSqlDate();
		java.sql.Time time = getCurrentSqlTime();
		
		try {
			Connection conn = FilmAgendaDatabaseConnection.connectionDatabase();
					
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO filmagenda (titel, datum, tijd) values (?,?,?);")) {       
				ps.setString(1, "Nieuwe Film");
	        	ps.setDate(2, date);
	        	ps.setTime(3, time);
	        	ps.executeUpdate();
	        	System.out.println("Data inserted.");
		      }
		    }catch (SQLException e) {
		    	throw new FilmAgendaServiceException("Something went wrong while inserting the filmagenda items.", e);
		    }
	}
	
	public static Date getDateBetween() {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		String inputString1 = "23 01 1997";
		String inputString2 = "27 04 2000";

		try {
		    Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    long diff = date2.getTime() - date1.getTime();
		    System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
				
		return null;
	}
	
	/**
	 * Get a diff between two dates
	 * @param date1 the oldest date
	 * @param date2 the newest date
	 * @param timeUnit the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
//	public static <Interval> long getJodaDateDiff() {
//		Interval interval = new Interval(oldTime, new Instant());
//		return (Long) null;
//	}

	public static String convertCurrentSqlDateToString() {
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
	public static java.sql.Date getCurrentSqlDate() {
		java.util.Date date = Calendar.getInstance().getTime();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}
	
	public static java.sql.Time getCurrentSqlTime() {
		java.util.Date date = new java.util.Date();
		Time sqlTime = new Time(date.getTime());
		return sqlTime;
	}

	public static void main(String[] args) {
		FilmAgendaItemService.getFilmAgendaItems();
		FilmAgendaItemService.getCurrentSqlDate();
		FilmAgendaItemService.setFilmAgendaItems();
		FilmAgendaItemService.convertCurrentSqlDateToString();
		FilmAgendaItemService.getDateBetween();
	//	FilmAgendaItemService.getDateDiff(null, null, null);
	}
}

