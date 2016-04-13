package nl.cerios.cerioscoop.web;

public class MyFirstServletException extends RuntimeException {

	public MyFirstServletException(String message) {
	    super(message);
	  }

	public MyFirstServletException(String message, Throwable cause) {
	    super(message, cause);
	  }
}
