
package com.mdv.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
	/*
	 * 
	 * @ExceptionHandler(UserAlreadyFoundException.class)
	 * // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) public
	 * ResponseEntity<String> handleCustomException(UserAlreadyFoundException ex) {
	 * return new ResponseEntity<String>(ex.getMessage(),
	 * HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * @ExceptionHandler(UserMultipleRecordsException.class)
	 * 
	 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) public
	 * UserMultipleRecordsException
	 * handleCustomException(UserMultipleRecordsException ex) { return ex; }
	 * 
	 * @ExceptionHandler(UserNotFoundException.class)
	 * 
	 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) public
	 * UserNotFoundException handleCustomException(UserNotFoundException ex) {
	 * return ex; }
	 */
}