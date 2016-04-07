package nl.cerios.cerioscoop.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class FilmAgendaItem<E> {

	private long id;
	private String titel;
	private Date datum;
	private Date tijd;

	public FilmAgendaItem(long id, String titel, Date datum, Date tijd) {
		this.id = id;
		this.titel = titel;
		this.datum = datum;
		this.tijd = tijd;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public Date getDatum() {
		return datum;
	}

//	public Date getFirstDate(List<FilmAgendaItem> items) {
//		int datum = items.indexOf(0);
//		System.out.println(datum);
//		return null;
//	}
	
//	public Date getEersteDatum(List<FilmAgendaItem> items) {
//		Collections.sort(items, new Comparator<Movie>()){
//			public <T> int compare(T m1, T m2) {
//		        return m1.getDate().compare(m2.getDate());
//		}
//		
//		return datum;
//	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Date getTijd() {
		return tijd;
	}

	public void setTijd(Time tijd) {
		this.tijd = tijd;
	}
	
	

}
