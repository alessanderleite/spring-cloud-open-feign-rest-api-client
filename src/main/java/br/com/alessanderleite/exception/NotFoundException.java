package br.com.alessanderleite.exception;

public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -7616307155151522001L;

	public NotFoundException(String message) {
		super(message);
	}
	
	
}
