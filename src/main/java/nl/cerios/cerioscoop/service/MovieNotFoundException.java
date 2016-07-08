package nl.cerios.cerioscoop.service;

public class MovieNotFoundException extends Exception {
	
	public MovieNotFoundException(final String message) {
		super(message);
	}

	public MovieNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
