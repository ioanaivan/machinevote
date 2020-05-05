
package com.mdv.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mdv.model.ErrorMessage;

@RestControllerAdvice
public class ExceptionsHandler {

	private static Logger log = LoggerFactory.getLogger(ExceptionsHandler.class);

	@ExceptionHandler(UserAlreadyFoundException.class)
	ResponseEntity<ErrorMessage> handleUserAlreadyFoundException(UserAlreadyFoundException ex) {
		log.info("Handling exception : " + ex.getMessage());
		ErrorMessage em = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(em, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserMultipleRecordsException.class)
	ResponseEntity<ErrorMessage> handleUserMultipleRecordsException(UserMultipleRecordsException ex) {
		log.info("Handling exception : " + ex.getMessage());
		ErrorMessage em = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(em, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundException ex) {
		log.info("Handling exception : " + ex.getMessage());
		ErrorMessage em = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(em, HttpStatus.BAD_REQUEST);
	}
}