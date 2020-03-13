package com.mdv.services;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdv.model.User;
import com.mdv.repository.UserServiceJDBCTemplate;

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
		
		UserIdentifier userIdent = new UserIdentifierImpl();
		String idGen = userIdent.generateId();
		String codeGen = userIdent.generateCode();
		user.setId(idGen);
		user.setCode(codeGen);
		// TODO setCode when DB ready
		
		//Search for the given Nom and Prenom.
				//User exist = userJDBC.findByFirstNameAndName(user.getFirstName(), user.getName());
				
				//Reject the registration if Nom and Prenom exist.
					 //if (exist != null) {
						 //result.reject("Utilisateur existe");
					 //}	
		userJDBC.createUser(user);
		return userIdent;
	
}
}
