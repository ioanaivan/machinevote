package com.mdv.exceptions;

/**
 * @brief Exception class for no action found
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
public class NoActionFoundException extends Exception {
	/**
	 * @brief serialVersionUID
	 */
	private static final long serialVersionUID = -1289673284631892371L;

	/**
	 * @brief Constructor
	 */
	public NoActionFoundException(String message) {
		super(message);
	}
}
