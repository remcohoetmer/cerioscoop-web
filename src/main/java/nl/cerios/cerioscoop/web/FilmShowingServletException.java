package nl.cerios.cerioscoop.web;

public class FilmShowingServletException extends RuntimeException {

	public FilmShowingServletException(String message) {
	    super(message);
	  }

	public FilmShowingServletException(String message, Throwable cause) {
	    super(message, cause);
	  }
}
