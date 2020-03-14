package com.mdv.services;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdv.model.*;
import com.mdv.repository.*;
import com.mdv.services.*;

import org.springframework.validation.BindingResult;
import java.lang.Exception;

@Service
public class UserServicesImpl implements UserService {
	
	BindingResult result;
	
	@Autowired
	private UserServiceJDBCTemplate userJDBC ;

	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	public UserIdentifier createUser(User user) {
		log.info("User creation for user: " + user.getFirstName() + "name : " + user.getName());
		
		//User exist = userJDBC.findByIdCard(user.getNationalCardId());
		
		String exist = userJDBC.findByIdCard(user.getNationalCardId());
		
		if (exist == null)
		{	
		UserIdentifier userIdent = new UserIdentifierImpl();
		String idGen = userIdent.generateId();
		String codeGen = userIdent.generateCode();
		user.setId(idGen);
		user.setCode(codeGen);
		// TODO setCode when DB ready
	
		userJDBC.createUser(user);
		return userIdent;
		}
		return null;
	
}
}

