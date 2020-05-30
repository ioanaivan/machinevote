package com.mdv.exceptions;

/**
 * @brief Exception class for user already found
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
public class UserAlreadyFoundException extends Exception {
	/**
	 * @brief serialVersionUID
	 */
	private static final long serialVersionUID = 4952438891307447502L;

	/**
	 * @brief Constructor
	 */
	public UserAlreadyFoundException(String message) {
		super(message);
	}
}
