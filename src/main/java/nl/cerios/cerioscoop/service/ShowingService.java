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

import nl.cerios.cerioscoop.domain.Film;
import nl.cerios.cerioscoop.domain.OldFilm;
import nl.cerios.cerioscoop.domain.Showing;
import nl.cerios.cerioscoop.util.DateUtils;

public class ShowingService {

	private static Connection oldCon = OldDatabaseConnection.connectionDatabase();
	private static Connection con = DatabaseConnection.connectionDatabase();
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static DateUtils DU = new DateUtils();
	

	public List<Showing> getVoorstelling(){
		List<Showing> items = new ArrayList<>();
		try {stmt = oldCon.createStatement();
			rs = stmt.executeQuery("select naam, premiere_datum from film voorstellingen where voorstellingen.Datum >= '2016-04-11'");
	        while (rs.next()) {
//	        	Date sqlDatum = rs.getDate("premiere_datum");
//	        	String titel = rs.getString("naam");
//	        	Date datum = sqlDatum == null ? null : new Date(sqlDatum.getTime());
	        	items.add(new Showing());
	        	}
	        return items;
	    }catch (SQLException e) {
	    	throw new ShowingServiceException("Something went terribly wrong while retrieving the filmagenda.", e);
	    }
	}
	/**
	 * @return
	 * @Todo maak een constructor zonder parameters
	 */
	public OldFilm getFirstFilm(){
		OldFilm item = null;
		try {stmt = oldCon.createStatement();
			rs = stmt.executeQuery("select naam, premiere_datum from film, voorstellingen "
						+ "where voorstellingen.Film_ID = film.film_id "
						+ "and voorstellingen.Datum = (SELECT premiere_datum FROM voorstellingen ORDER BY datum Limit 1)"); 
			while (rs.next()) {
	        	Date sqlDatum = rs.getDate("premiere_datum");
	        	rs.getString("naam");
	        	new Date(sqlDatum.getTime());
	        	};
	        return item;
	    }catch (SQLException e) {
	    	throw new ShowingServiceException("Something went terribly wrong while retrieving the first date.", e);
	    }
	}
	
	public List<OldFilm> getOldFilms(){
		List<OldFilm> items = new ArrayList<>();
		try {stmt = oldCon.createStatement();
			rs = stmt.executeQuery("select film_id, naam, minuten, type, taal, premiere_datum, premiere_tijd, laatste_voorstelling from film"); { 
			
				while (rs.next()) {
	        	int id = rs.getInt("film_id");
	        	String naam = rs.getString("naam");
	        	int minuten = rs.getInt("minuten");
	        	int type = rs.getInt("type");
	        	String taal = rs.getString("taal");
	        	Date sqlPD = rs.getDate("premiere_datum");
	        	Time sqlPT = rs.getTime("premiere_tijd");
	        	Date sqlLV = rs.getDate("laatste_voorstelling");
	        	
	        	Date premiereDatum = sqlPD == null ? null : new Date(sqlPD.getTime());
	        	Time premiereTijd = sqlPT == null ? null : new Time(sqlPT.getTime());
	        	Date laatsteVoorstelling = sqlLV == null ? null : new Date(sqlLV.getTime());
	        	items.add(new OldFilm(id, naam, minuten, type, taal, premiereDatum, premiereTijd, laatsteVoorstelling));
	        	}
	        return items;
	      }
	    }catch (SQLException e) {
	    	throw new ShowingServiceException("Something went terribly wrong while retrieving the first date.", e);
	    }
	}
	public List<Film> getFilms(){
		List<Film> items = new ArrayList<>();
		try {stmt = con.createStatement();
			rs = stmt.executeQuery("select film_id, name, minutes, type, language from film"); { 
			
				while (rs.next()) {
	        	int filmID = rs.getInt("film_id");
	        	String name = rs.getString("name");
	        	int minutes = rs.getInt("minutes");
	        	int type = rs.getInt("type");
	        	String language = rs.getString("language");
	        	items.add(new Film(filmID, name, minutes, type, language));
	        	}
	        return items;
	      }
	    }catch (SQLException e) {
	    	throw new ShowingServiceException("Something went terribly wrong while retrieving the first date.", e);
	    }
	}
	
	public List<Showing> getShowing(){
		List<Showing> items = new ArrayList<>();
		try {stmt = con.createStatement();
			rs = stmt.executeQuery("select showing_id, film_id, room_id, premiere_date, premiere_time, last_showing_date, last_showing_time from showing"); { 
			
				while (rs.next()) {
				int showingID = rs.getInt("showing_id");
	        	int filmID = rs.getInt("film_id");
	        	int roomID = rs.getInt("room_id");
	        	Date sqlPD = rs.getDate("premiere_date");
	        	Time sqlPT = rs.getTime("premiere_time");
	        	Date sqlLSD = rs.getDate("last_showing_date");
	        	Time sqlLST = rs.getTime("last_showing_time");
	        	
	        	Date premiereDate = sqlPD == null ? null : new Date(sqlPD.getTime());
	        	Time premiereTime = sqlPT == null ? null : new Time(sqlPT.getTime());
	        	Date lastShowingDate = sqlLSD == null ? null : new Date(sqlLSD.getTime());
	        	Time lastShowingTime = sqlLST == null ? null : new Time(sqlLST.getTime());
	        	items.add(new Showing(showingID, filmID, roomID, premiereDate, premiereTime, lastShowingDate, lastShowingTime));
	        	}
	        return items;
	      }
	    }catch (SQLException e) {
	    	throw new ShowingServiceException("Something went terribly wrong while retrieving the first date.", e);
	    }
	}
	
	public void registerOldFilm(String nieuweFilmNaam, int minuten, int type, String taal, Date premiereDatum, Time premiereTijd, Date laatsteVoorstelling){		
		try (PreparedStatement ps = oldCon.prepareStatement("INSERT INTO film (naam, minuten, type, taal, premiere_datum, premiere_tijd,laatste_voorstelling) "
														+ "values (?,?,?,?,?,?,?);")) {
			ps.setString(1, nieuweFilmNaam);
			ps.setInt(2, minuten);
			ps.setInt(3, type);
			ps.setString(4, taal);
			ps.setDate(5, premiereDatum);
			ps.setTime(6, premiereTijd);
			ps.setDate(7, laatsteVoorstelling);
			ps.executeUpdate();
			System.out.println("Data inserted.");
		} catch (SQLException e) {
			throw new ShowingServiceException("Something went wrong while inserting the filmagenda items.", e);
		}
	}

	public void registerFilm(String newFilmName, int minutes, int type, String language) {
		try (PreparedStatement ps = con.prepareStatement("INSERT INTO film (name, minutes, type, language) values (?,?,?,?);")) {
			ps.setString(1, newFilmName);
			ps.setInt(2, minutes);
			ps.setInt(3, type);
			ps.setString(4, language);
			ps.executeUpdate();
			System.out.println("Data inserted.");
	    }catch (SQLException e) {
	    	throw new ShowingServiceException("Something went wrong while inserting the filmagenda items.", e);
	    }
	}
	
	public void registerShowing(int filmID, int roomID, Date premiereDate, Time premiereTime, Date last_showing_date, Time last_showing_time){		
		try (PreparedStatement ps = con.prepareStatement("INSERT INTO showing (film_id, room_id, premiere_date, premiere_time, last_showing_date, last_showing_time) "
														+ "values (?,?,?,?,?,?);")) {       
        	ps.setInt(1, filmID);
        	ps.setInt(2, roomID);
        	ps.setDate(3, premiereDate);
        	ps.setTime(4, premiereTime);
        	ps.setDate(5, last_showing_date);
        	ps.setTime(6, last_showing_time);
        	ps.executeUpdate();
        	System.out.println("Data inserted.");
	    }catch (SQLException e) {
	    	throw new ShowingServiceException("Something went wrong while inserting the filmagenda items.", e);
	    }
	}
	/**
	 * Returns a first film record of the type FilmAgendaItem.
	 * 
	 * @return eerstVolgendeFilm
	 */
	public OldFilm getFirstFilmAfterCurrentDate(){
		List<OldFilm> items = getOldFilms();
		OldFilm eerstVolgendeFilm = null;
		
		for (OldFilm eerstVolgendeFilmKandidaat : items) {
			if(eerstVolgendeFilmKandidaat.getPremiereDatum().after(DU.getCurrentSqlDate())){	
				if(eerstVolgendeFilm == null){			//hier wordt voor 1x eerstVolgendeFilm gevuld					
					eerstVolgendeFilm = eerstVolgendeFilmKandidaat;
				}
				else if(eerstVolgendeFilmKandidaat.getPremiereDatum().before(eerstVolgendeFilm.getPremiereDatum())){
					eerstVolgendeFilm = eerstVolgendeFilmKandidaat;			
				}
			}
		}
		System.out.println(eerstVolgendeFilm);
		return eerstVolgendeFilm;
	}
	
	/**
	 * Returns a first showing record.
	 * 
	 * @return firstShowing
	 */
	public Showing getFirstShowingAfterCurrentDate(){
		List<Showing> items = getShowing();
		Showing firstShowing = null;
		
		for (Showing firstShowingCandidate : items) {
			if(firstShowingCandidate.getPremiereDate().after(DU.getCurrentSqlDate())){	
				if(firstShowing == null){			//hier wordt voor 1x eerstVolgendeFilm gevuld					
					firstShowing = firstShowingCandidate;
				}
				else if(firstShowingCandidate.getPremiereDate().before(firstShowing.getPremiereDate())){
					firstShowing = firstShowingCandidate;			
				}
			}
		}
		System.out.println(firstShowing);
		return firstShowing;
	}
	
	public static void main(String[] args) {
		//ShowingService SS = new ShowingService();
		//SS.registerOldFilm("Lion King2", 90, 2, null, null, null, null);
		//SS.registerFilm("Lion King", 90, 2, "Nederlands");
		//SS.registerShowing(1, 2, DU.getCurrentSqlDate(), DU.getCurrentSqlTime(), DU.getCurrentSqlDate(), DU.getCurrentSqlTime());
		//DU.convertCurrentSqlDateToString();
		//System.out.println(SS.getFirstFilmAfterCurrentDate());
		//System.out.println(SS.getOldFilms());
		//System.out.println(SS.getShowing());
		//System.out.println(SS.getFirstShowingAfterCurrentDate());
	}
}

