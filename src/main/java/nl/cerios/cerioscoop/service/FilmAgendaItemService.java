package nl.cerios.cerioscoop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nl.cerios.cerioscoop.domain.FilmAgendaItem;
import nl.cerios.cerioscoop.util.DateUtils;

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
	
	public static List<FilmAgendaItem> getFirstFilmAgendaItems(){
	    
		try {
			Connection conn = FilmAgendaDatabaseConnection.connectionDatabase();
			Statement stmt = conn.createStatement();

			List<FilmAgendaItem> items = new ArrayList<>();
			try (ResultSet rs = stmt.executeQuery("SELECT id, datum, tijd, titel FROM FilmAgenda "
					+ "WHERE datum = (SELECT datum FROM filmagenda ORDER BY datum Limit 1)")) {
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
		    	throw new FilmAgendaServiceException("Something went terribly wrong while retrieving the first date.", e);
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

//	/**
//	 * Get a diff between two dates
//	 * @param date1 the oldest date
//	 * @param date2 the newest date
//	 * @param timeUnit the unit in which you want the diff
//	 * @return the diff value, in the provided unit
//	 * http://stackoverflow.com/questions/1555262/calculating-the-difference-between-two-java-date-instances
//	 */
//	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
//	    long diffInMillies = date2.getTime() - date1.getTime();
//	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
//	}
	
//	public static <Interval> long getJodaDateDiff() {
//		Interval interval = new Interval(oldTime, new Instant());
//		return (Long) null;
//	}
	
	public static void main(String[] args) {
		FilmAgendaItemService.getFilmAgendaItems();
		FilmAgendaItemService.getFirstFilmAgendaItems();
		FilmAgendaItemService.getCurrentSqlDate();
		FilmAgendaItemService.setFilmAgendaItems();
		FilmAgendaItemService.convertCurrentSqlDateToString();
	//	FilmAgendaItemService.getDateDiff(null, null, null);
	}
}

