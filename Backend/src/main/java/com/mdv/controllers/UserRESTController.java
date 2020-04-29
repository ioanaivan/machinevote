/*
 * Class in charge of exposing REST endpoints
 */

package com.mdv.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mdv.exceptions.UserAlreadyFoundException;
import com.mdv.exceptions.UserMultipleRecordsException;
import com.mdv.exceptions.UserNotFoundException;
import com.mdv.model.User;
import com.mdv.model.UserIdentifier;
import com.mdv.model.UserIdentifierImpl;
import com.mdv.model.VoteIdentifier;
import com.mdv.services.UserService;

@RestController
public class UserRESTController {

	@Autowired
	public UserService userService;

	private Logger log = LoggerFactory.getLogger(UserRESTController.class);

	@CrossOrigin
	@PostMapping("/createUser")
	UserIdentifier createUser(@RequestBody User user) {
		log.info("POST Request to /createUser received with data : " + "firstName: " + user.getFirstName() + " name: "
				+ user.getName() + " location: " + user.getLocation() + " nationalcardId: " + user.getNationalCardId()
				+ " securityCardId: " + user.getSecurityCardId());
		try {
			return userService.createUser(user);
		} catch (UserAlreadyFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already	registered with this ID card", ex);
		} catch (UserMultipleRecordsException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Multiple registration found.", e);
		}
	}

	@CrossOrigin
	@PostMapping("/authUser")
	void authUser(@RequestBody UserIdentifierImpl userIdentifier) {
		log.info("POST Request to /authUser received with data : " + "id: " + userIdentifier.getId());
		try {
			userService.authUser(userIdentifier);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No user found for the id and code provided", ex);
		} catch (UserMultipleRecordsException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Multiple registration found.", e);
		}
	}

	@CrossOrigin
	@PostMapping("/vote")
	void vote(@RequestBody VoteIdentifier voteIdentifier) {
		log.info("POST Request to /vote received with data : " + "id: " + voteIdentifier.getId());
		userService.registerVote(voteIdentifier);
	}

	@CrossOrigin
	@GetMapping(value = "/test", produces = "text/plain")
	String test() {
		log.info("Test attempt");
		return "{\"success\":1}";

	}
}
