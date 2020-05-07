package com.mdv.exceptions;

public class VoteAlreadyFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1213455004348099272L;

	public VoteAlreadyFoundException(String message) {
		super(message);
	}
}