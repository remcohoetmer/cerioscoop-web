package nl.cerios.cerioscoop.web;

public class ShowException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ShowException(final String message) {
	    super(message);
	  }

	public ShowException(final String message, final Throwable cause) {
	    super(message, cause);
	  }
}
