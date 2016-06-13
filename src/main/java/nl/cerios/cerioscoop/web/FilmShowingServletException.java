package nl.cerios.cerioscoop.web;

public class FilmShowingServletException extends RuntimeException {

	public FilmShowingServletException(final String message) {
	    super(message);
	  }

	public FilmShowingServletException(final String message, final Throwable cause) {
	    super(message, cause);
	  }
}
