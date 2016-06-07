package nl.cerios.cerioscoop.service;

public class ShowingServiceException extends RuntimeException {

	public ShowingServiceException(String message) {
		super(message);
	}

	public ShowingServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
