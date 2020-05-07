/*
 * Class in charge of exposing REST endpoints
 */

package com.mdv.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mdv.exceptions.NoActionFoundException;
import com.mdv.exceptions.UserAlreadyFoundException;
import com.mdv.exceptions.UserMultipleRecordsException;
import com.mdv.exceptions.UserNotFoundException;
import com.mdv.exceptions.VoteAlreadyFoundException;
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
	UserIdentifier createUser(@RequestBody User user) throws UserAlreadyFoundException, UserMultipleRecordsException {
		log.info("POST Request to /createUser received with data : " + "firstName: " + user.getFirstName() + " name: "
				+ user.getName() + " location: " + user.getLocation() + " nationalcardId: " + user.getNationalCardId()
				+ " securityCardId: " + user.getSecurityCardId());
		return userService.createUser(user);
	}

	@CrossOrigin
	@PostMapping("/authUser")
	void authUser(@RequestBody UserIdentifierImpl userIdentifier)
			throws UserNotFoundException, UserMultipleRecordsException, NoActionFoundException {
		log.info("POST Request to /authUser received with data : " + "id: " + userIdentifier.getId());
		userService.authUser(userIdentifier);
	}

	@CrossOrigin
	@PostMapping("/vote")
	void vote(@RequestBody VoteIdentifier voteIdentifier)
			throws NoActionFoundException, UserMultipleRecordsException, VoteAlreadyFoundException {
		log.info("POST Request to /vote received with data : " + "id: " + voteIdentifier.getId());
		userService.createVote(voteIdentifier);
	}

	@CrossOrigin
	@GetMapping(value = "/test", produces = "text/plain")
	String test() {
		log.info("Test attempt");
		return "{\"success\":1}";

	}
}
