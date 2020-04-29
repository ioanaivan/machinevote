/*
 * Class containing services used for processing
 */

package com.mdv.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdv.exceptions.UserAlreadyFoundException;
import com.mdv.exceptions.UserMultipleRecordsException;
import com.mdv.exceptions.UserNotFoundException;
import com.mdv.model.*;
import com.mdv.repository.*;

@Service
public class UserServicesImpl implements UserService {

	@Autowired
	private UserServiceJDBCTemplate userJDBC;

	private static Logger log = LoggerFactory.getLogger(UserService.class);

	@Override
	public UserIdentifier createUser(User user) throws UserAlreadyFoundException, UserMultipleRecordsException {
		log.info("User creation for user firstName: " + user.getFirstName() + ", name : " + user.getName());

		// Check if user is a real person - present in Gov DB
		// TODO

		// Check if user already registered - present in MDV DB
		userJDBC.findByIdCard(user.getNationalCardId());

		UserIdentifier userIdent = new UserIdentifierImpl();

		// generate user identifier and password before store in DB
		String idGen = userIdent.generateId();
		String codeGen = userIdent.generateCode();
		userIdent.setId(idGen);
		userIdent.setCode(codeGen);

		userJDBC.createUser(user, userIdent);

		// userJDBC.registerAction("Registration", "0", "NULL", idGen);
		return userIdent;
	}

	@Override
	public void authUser(UserIdentifier userIdentifier) throws UserNotFoundException, UserMultipleRecordsException {
		log.info("User authentification for user id: " + userIdentifier.getId());
		userJDBC.findByIdCardSecuCard(userIdentifier);

		userJDBC.isRegistered(userIdentifier);

		// userJDBC.registerAction("Authentification", "0", "NULL", idGen);
	}

	@Override
	public void registerVote(VoteIdentifier voteIdentifier) {
		log.info("Voting for user id: " + voteIdentifier.getId());
		userJDBC.registerVote(voteIdentifier);
	}
}
