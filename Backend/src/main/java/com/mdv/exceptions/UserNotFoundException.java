package com.mdv.exceptions;

public class UserNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4592032369402915603L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
