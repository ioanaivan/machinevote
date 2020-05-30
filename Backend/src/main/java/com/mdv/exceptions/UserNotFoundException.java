package com.mdv.exceptions;

/**
 * @brief Exception class for user not found
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
public class UserNotFoundException extends Exception {
	/**
	 * @brief serialVersionUID
	 */
	private static final long serialVersionUID = 4592032369402915603L;

	/**
	 * @brief Constructor
	 */
	public UserNotFoundException(String message) {
		super(message);
	}
}
