package com.mdv.exceptions;

/**
 * @brief Exception class for multiple registration found
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
public class UserMultipleRecordsException extends Exception {
	/**
	 * @brief serialVersionUID
	 */
	private static final long serialVersionUID = 7600214895812685803L;

	/**
	 * @brief Constructor
	 */
	public UserMultipleRecordsException(String message) {
		super(message);
	}
}
