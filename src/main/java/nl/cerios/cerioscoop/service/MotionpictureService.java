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
import nl.cerios.cerioscoop.domain.Voorstelling;
import nl.cerios.cerioscoop.util.DateUtils;

public class MotionpictureService {

	private static Connection oldCon = OldDatabaseConnection.connectionDatabase();
	private static Connection con = DatabaseConnection.connectionDatabase();
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static DateUtils DU = new DateUtils();
	

	public List<Voorstelling> getVoorstelling(){
		List<Voorstelling> items = new ArrayList<>();
		try {stmt = oldCon.createStatement();
			rs = stmt.executeQuery("select naam, premiere_datum from film voorstellingen where voorstellingen.Datum >= '2016-04-11'");
	        while (rs.next()) {
//	        	Date sqlDatum = rs.getDate("premiere_datum");
//	        	String titel = rs.getString("naam");
//	        	Date datum = sqlDatum == null ? null : new Date(sqlDatum.getTime());
	        	items.add(new Voorstelling());
	        	}
	        return items;
	    }catch (SQLException e) {
	    	throw new MotionpictureServiceException("Something went terribly wrong while retrieving the filmagenda.", e);
	    }
	}
	/**
	 * @return
	 * @Todo maak een constructor zonder parameters
	 */
	public Film getFirstFilm(){
		Film item = null;
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
	    	throw new MotionpictureServiceException("Something went terribly wrong while retrieving the first date.", e);
	    }
	}
	
	public List<Film> getFilms(){
		List<Film> items = new ArrayList<>();
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
	        	items.add(new Film(id, naam, minuten, type, taal, premiereDatum, premiereTijd, laatsteVoorstelling));
	        	}
	        return items;
	      }
	    }catch (SQLException e) {
	    	throw new MotionpictureServiceException("Something went terribly wrong while retrieving the first date.", e);
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
			throw new MotionpictureServiceException("Something went wrong while inserting the filmagenda items.", e);
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
	    	throw new MotionpictureServiceException("Something went wrong while inserting the filmagenda items.", e);
	    }
	}
	
	public void registerMotionpicture(int filmID, int roomID, Date premiereDate, Time premiereTime, Date last_motionpicture_date, Time last_motionpicture_time){		
		try (PreparedStatement ps = con.prepareStatement("INSERT INTO motionpicture (film_id, room_id, premiere_date, premiere_time, last_motionpicture_date, last_motionpicture_time) "
														+ "values (?,?,?,?,?,?);")) {       
        	ps.setInt(1, filmID);
        	ps.setInt(2, roomID);
        	ps.setDate(3, premiereDate);
        	ps.setTime(4, premiereTime);
        	ps.setDate(5, last_motionpicture_date);
        	ps.setTime(6, last_motionpicture_time);
        	ps.executeUpdate();
        	System.out.println("Data inserted.");
	    }catch (SQLException e) {
	    	throw new MotionpictureServiceException("Something went wrong while inserting the filmagenda items.", e);
	    }
	}
	/**
	 * Returns a first film record of the type FilmAgendaItem.
	 * 
	 * @return eerstVolgendeFilm
	 */
	public Film getFirstFilmAfterCurrentDate(){
		List<Film> items = getFilms();
		Film eerstVolgendeFilm = null;
		
		for (Film eerstVolgendeFilmKandidaat : items) {
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
	
	public static void main(String[] args) {
		//MotionpictureService MPS = new MotionpictureService();
		//System.out.println(VS.getVoorstelling());
		//MPS.registerOldFilm("Lion King2", 90, 2, null, null, null, null);
		//MPS.registerFilm("Lion King", 90, 2, "Nederlands");
		//MPS.registerMotionpicture(1, 2, DU.getCurrentSqlDate(), DU.getCurrentSqlTime(), DU.getCurrentSqlDate(), DU.getCurrentSqlTime());
		//DU.convertCurrentSqlDateToString();
		//System.out.println(VS.getFirstFilmAfterCurrentDate());
	}
}

