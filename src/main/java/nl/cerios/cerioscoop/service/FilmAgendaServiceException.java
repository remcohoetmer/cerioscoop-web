package nl.cerios.cerioscoop.service;

public class FilmAgendaServiceException extends RuntimeException {

  public FilmAgendaServiceException(String message) {
    super(message);
  }

  public FilmAgendaServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
