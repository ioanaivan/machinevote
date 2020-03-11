package com.mdv.controllers;

import org.springframework.validation.BindingResult;
import java.lang.Exception;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdv.model.User;
import com.mdv.services.UserIdentifier;
import com.mdv.services.UserIdentifierImpl;
import com.mdv.services.UserService;
import com.mdv.repository.UserServiceJDBCTemplate;

@RestController
public class UserRESTController {
	
	BindingResult result;
	
	private Logger log = LoggerFactory.getLogger(UserRESTController.class);
	
    @PostMapping("/createUser")
    UserIdentifier createUser(@RequestBody User user) {
   	        log.info("POST Request to /createUser received with data : " + "firstName: " + user.getFirstName() + " name: " + user.getName() + " location: " + user.getLocation() + " nationalcardId: " + user.getNationalCardId() + " securityCardId: " + user.getSecurityCardId());
            return userService.createUser(user);
    }

    	
    
    @GetMapping(value="/test", produces="text/plain")
    String test() {
    	log.info("This is an info log entry");
    	return "{\"success\":1}";
    }
}

