package it.prova.raccoltafilm.exceptions;

public class FilmAssociatiException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public FilmAssociatiException(String message) {
		super(message);
	}
}