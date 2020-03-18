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

import com.mdv.model.User;
import com.mdv.model.UserIdentifier;
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
		return userService.createUser(user);
	}

	@CrossOrigin
	@GetMapping(value = "/test", produces = "text/plain")
	String test() {
		log.info("This is an info log entry");
		return "{\"success\":1}";
	}
}
