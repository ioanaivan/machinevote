/**
 * @package com.mdv.exceptions
 * @brief Exception handler and custom exceptions
 */
package com.mdv.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mdv.model.ErrorMessage;

/**
 * @brief Class to handle business exceptions
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
@RestControllerAdvice
public class ExceptionsHandler {

	/**
	 * @brief Logger for the ExceptionsHandler class
	 */
	private static Logger log = LoggerFactory.getLogger(ExceptionsHandler.class);

	/**
	 * @brief Handles exception for case when user is already registered
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 * 
	 * @param UserAlreadyFoundException exception for user already registered
	 * @return <b>ResponseEntity &lt ErrorMessage &gt</b> a Http response with
	 *         status 400 and error message
	 */
	@ExceptionHandler(UserAlreadyFoundException.class)
	ResponseEntity<ErrorMessage> handleUserAlreadyFoundException(UserAlreadyFoundException ex) {
		log.info("Handling exception : " + ex.getMessage());
		ErrorMessage em = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(em, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @brief Handles exception for case when multiple users are registered. As the
	 *        application does not allow it, this is a fraud case
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 * 
	 * @param UserMultipleRecordsException exception for multiple records found for
	 *                                     same user
	 * @return <b>ResponseEntity &lt ErrorMessage &gt</b> a Http response with
	 *         status 500 and error message
	 */
	@ExceptionHandler(UserMultipleRecordsException.class)
	ResponseEntity<ErrorMessage> handleUserMultipleRecordsException(UserMultipleRecordsException ex) {
		log.info("Handling exception : " + ex.getMessage());
		ErrorMessage em = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(em, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * @brief Handles exception for case when user is not found in government
	 *        solution
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 * 
	 * @param UserNotFoundException exception for user not found
	 * @return <b>ResponseEntity &lt ErrorMessage &gt</b> a Http response with
	 *         status 400 and error message
	 */
	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundException ex) {
		log.info("Handling exception : " + ex.getMessage());
		ErrorMessage em = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(em, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @brief Handles exception for case when action searched is not present
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 * 
	 * @param NoActionFoundException exception for user not found
	 * @return <b>ResponseEntity &lt ErrorMessage &gt</b> a Http response with
	 *         status 400 and error message
	 */
	@ExceptionHandler(NoActionFoundException.class)
	ResponseEntity<ErrorMessage> handleNoActionFoundException(NoActionFoundException ex) {
		log.info("Handling exception : " + ex.getMessage());
		ErrorMessage em = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(em, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @brief Handles exception for case when vote is already present
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 * 
	 * @param VoteAlreadyFoundException exception for vote already present
	 * @return <b>ResponseEntity &lt ErrorMessage &gt</b> a Http response with
	 *         status 400 and error message
	 */
	@ExceptionHandler(VoteAlreadyFoundException.class)
	ResponseEntity<ErrorMessage> handleVoteAlreadyFoundException(VoteAlreadyFoundException ex) {
		log.info("Handling exception : " + ex.getMessage());
		ErrorMessage em = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(em, HttpStatus.BAD_REQUEST);
	}
}