package br.com.alessanderleite.model;

public enum CustomMessage {
	SERVER_ERROR("An unexpected error occured. Please try again!"),
	NOT_ACCEPTABLE("Not acceptable"),
	NOT_FOUND("Not Found");
	
	private final String message;

	private CustomMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
} 
