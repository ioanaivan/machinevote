package com.mdv.exceptions;

/**
 * @brief Exception class vote already present
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
public class VoteAlreadyFoundException extends Exception {
	/**
	 * @brief serialVersionUID
	 */
	private static final long serialVersionUID = -1213455004348099272L;

	/**
	 * @brief Constructor
	 */
	public VoteAlreadyFoundException(String message) {
		super(message);
	}
}