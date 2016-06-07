package nl.cerios.cerioscoop.domain;

public class Film {
	private int filmID;
	private String name;
	private int minutes;
	private int type;
	private String language;
	
	public Film() {
		
	}

	public Film(int filmID, String name, int minutes, int type, String language) {
		this.filmID = filmID;
		this.name = name;
		this.minutes = minutes; 
		this.type = type;
		this.language = language;
	}
	
	public int getFilmID() {
		return filmID;
	}

	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
