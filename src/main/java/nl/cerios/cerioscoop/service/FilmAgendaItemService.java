package nl.cerios.cerioscoop.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import nl.cerios.cerioscoop.domain.FilmAgendaItem;
import nl.cerios.cerioscoop.util.DateUtils;

public class FilmAgendaItemService {

	public static Connection conn = FilmAgendaDatabaseConnection.connectionDatabase();
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static DateUtils DU = new DateUtils();
	

	public static List<FilmAgendaItem> getFilmAgendaItems(){
		List<FilmAgendaItem> items = new ArrayList<>();
		try {stmt = conn.createStatement();
			rs = stmt.executeQuery("select naam, datum from film, "
						+ "voorstellingen where voorstellingen.Film_ID = film.film_id "
						+ "and voorstellingen.Datum >= '2016-04-11'");
	        while (rs.next()) {
	        	Date sqlDatum = rs.getDate("Datum");
	        	String titel = rs.getString("naam");
	        	Date datum = sqlDatum == null ? null : new Date(sqlDatum.getTime());
	        	items.add(new FilmAgendaItem(titel, datum, null));
	        	}
	        return items;
	    }catch (SQLException e) {
	    	throw new FilmAgendaServiceException("Something went terribly wrong while retrieving the filmagenda.", e);
	    }
	}
	/**
	 * @return
	 * @Todo maak een constructor zonder parameters
	 */
	public static FilmAgendaItem getFirstFilmAgendaItem(){
		FilmAgendaItem item = null;
		try {stmt = conn.createStatement();
			rs = stmt.executeQuery("select naam, datum from film, voorstellingen "
						+ "where voorstellingen.Film_ID = film.film_id "
						+ "and voorstellingen.Datum = (SELECT datum FROM voorstellingen ORDER BY datum Limit 1)"); 
			while (rs.next()) {
	        	Date sqlDatum = rs.getDate("Datum");
	        	rs.getString("naam");
	        	new Date(sqlDatum.getTime());
	        	};
	        return item;
	    }catch (SQLException e) {
	    	throw new FilmAgendaServiceException("Something went terribly wrong while retrieving the first date.", e);
	    }
	}
	
	public static List<FilmAgendaItem> getFirstFilmAgendaItems(){
		List<FilmAgendaItem> items = new ArrayList<>();
		try {stmt = conn.createStatement();
			rs = stmt.executeQuery("select naam, datum from film, voorstellingen "
						+ "where voorstellingen.Film_ID = film.film_id "
						+ "and voorstellingen.Datum = (SELECT datum FROM voorstellingen ORDER BY datum Limit 1)"); 
	        while (rs.next()) {
	        	Date sqlDatum = rs.getDate("Datum");
	        	String titel = rs.getString("naam");
	        	Date datum = sqlDatum == null ? null : new Date(sqlDatum.getTime());
	        	items.add(new FilmAgendaItem(titel, datum, null));
	        	}
	        return items;
	    }catch (SQLException e) {
	    	throw new FilmAgendaServiceException("Something went terribly wrong while retrieving the first date.", e);
	    }
	}
	
	public static List<FilmAgendaItem> getFilmAgendaItems2(){
		List<FilmAgendaItem> items = new ArrayList<>();
		try {stmt = conn.createStatement();
			rs =  stmt.executeQuery("select id, titel, datum ,tijd from filmagenda"); {
			
			while (rs.next()) {
	        	long id = rs.getLong("id");
	        	Date sqlDatum = rs.getDate("datum");
	        	Time sqlTijd = rs.getTime("tijd");
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
	
	public static List<FilmAgendaItem> getFirstFilmAgendaItems2(){
		List<FilmAgendaItem> items = new ArrayList<>();
		try {stmt = conn.createStatement();
			rs = stmt.executeQuery("select id, titel, datum ,tijd from filmagenda "
					+ "where datum = (SELECT datum FROM filmagenda ORDER BY datum Limit 1)"); { 
			while (rs.next()) {
	        	long id = rs.getLong("id");
	        	Date sqlDatum = rs.getDate("datum");
	        	Time sqlTijd = rs.getTime("tijd");
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
	
	public static void registerFilm(String nieuweFilmNaam, int minuten, int type, String taal, Date premiereDatum, Date laatsteVoorstelling){		
		try (PreparedStatement ps = conn.prepareStatement("INSERT INTO film (naam, minuten, type, taal, premiere_datum, laatste_voorstelling) "
														+ "values (?,?,?,?,?,?);")) {       
			ps.setString(1, nieuweFilmNaam);
        	ps.setInt(2, minuten);
        	ps.setInt(3, type);
        	ps.setString(4, taal);
        	ps.setDate(5, premiereDatum);
        	ps.setDate(6, laatsteVoorstelling);
        	ps.executeUpdate();
        	System.out.println("Data inserted.");
	    }catch (SQLException e) {
	    	throw new FilmAgendaServiceException("Something went wrong while inserting the filmagenda items.", e);
	    }
	}
	
	public static void main(String[] args) {
		FilmAgendaItemService.getFirstFilmAgendaItem();
		System.out.println("Datum eerstVolgendeFilm: "+getFirstFilmAgendaItem().getDatum());
//		FilmAgendaItemService.getFilmAgendaItems();
//		FilmAgendaItemService.getFirstFilmAgendaItems();
		FilmAgendaItemService.DU.getCurrentSqlDate();
//		FilmAgendaItemService.registerFilm("American Pie");
//		FilmAgendaItemService.DU.convertCurrentSqlDateToString();
		FilmAgendaItem eerstVolgendeFilm = DU.getFirstFilmAfterCurrentDate();
		System.out.println("Datum eerstVolgendeFilm: "+eerstVolgendeFilm.getTitel()
						+" "+DU.format(eerstVolgendeFilm.getDatum())+" om "+DU.formatTijd(eerstVolgendeFilm.getTijd()));
//		FilmAgendaItemService.getDateDiff(null, null, null);
	}
}

