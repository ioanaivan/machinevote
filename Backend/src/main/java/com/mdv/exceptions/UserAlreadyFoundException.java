package com.mdv.exceptions;

public class UserAlreadyFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4952438891307447502L;

	public UserAlreadyFoundException(String message) {
		super(message);
	}
}
