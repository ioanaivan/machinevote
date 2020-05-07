package com.mdv.model;

public class ErrorMessage {

	public String getMessage() {
		return message;
	}

	public ErrorMessage(String message) {
		super();
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	String message;

}
