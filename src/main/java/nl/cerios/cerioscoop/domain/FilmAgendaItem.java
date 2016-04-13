package nl.cerios.cerioscoop.domain;

import java.sql.Time;
import java.util.Date;

public class FilmAgendaItem {

	private long id;
	private String titel;
	private Date datum;
	private Date tijd;

	public FilmAgendaItem(String titel, Date datum, Date tijd) {
		this.titel = titel;
		this.datum = datum;
		this.tijd = tijd;
	}
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
