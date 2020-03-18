package com.mdv.services;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdv.model.User;
import com.mdv.repository.UserServiceJDBCTemplate;

@Service
public class UserServicesImpl implements UserService {
	
	@Autowired
	private UserServiceJDBCTemplate userJDBC ;

	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	public UserIdentifier createUser(User user) {
		log.info("User creation for user: " + user.getFirstName() + "name : " + user.getName());
		
		UserIdentifier userIdent = new UserIdentifierImpl();
		String idGen = userIdent.generateId();
		String codeGen = userIdent.generateCode();
		user.setId(idGen);
		// TODO setCode when DB ready
		userJDBC.createUser(user);
		return userIdent;
	}
}
