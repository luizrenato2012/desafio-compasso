package br.com.lrsantos.application.exception;

public class RequestInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequestInvalidoException(String message) {
		super(message);
	}

	
}
