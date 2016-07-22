package nl.cerios.cerioscoop.service;

public class MovieNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public MovieNotFoundException(final String message) {
		super(message);
	}

	public MovieNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
