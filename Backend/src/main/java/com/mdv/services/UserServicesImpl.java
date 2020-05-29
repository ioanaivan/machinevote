/*
 * Class containing services used for processing
 */

package com.mdv.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdv.clients.GovClient;
import com.mdv.exceptions.NoActionFoundException;
import com.mdv.exceptions.UserAlreadyFoundException;
import com.mdv.exceptions.UserMultipleRecordsException;
import com.mdv.exceptions.UserNotFoundException;
import com.mdv.exceptions.VoteAlreadyFoundException;
import com.mdv.model.*;
import com.mdv.repository.*;

@Service
public class UserServicesImpl implements UserService {

	@Autowired
	private UserServiceJDBCTemplate userJDBC;

	@Autowired
	private GovClient govClient;

	private static Logger log = LoggerFactory.getLogger(UserService.class);

	@Override
	public UserIdentifier createUser(User user)
			throws UserAlreadyFoundException, UserMultipleRecordsException, UserNotFoundException {
		log.info("User creation for user firstName: " + user.getFirstName() + ", name : " + user.getName());

		// Check if user is a real person - present in Gov DB
		String response = govClient.sendGetUser(user);

		if (response != null && !response.isEmpty() && !response.equals("User found")) {
			log.info("User creation FAILED for user firstName: " + user.getFirstName() + ", name : " + user.getName()
					+ " with error: " + response);
			userJDBC.saveAction("Register", "FAILED", response, null);
			throw new UserNotFoundException(response);
		}

		// Check if user already registered - present in DB
		userJDBC.findByIdCard(user.getNationalCardId());

		// Generate user identifier and password before store in DB
		UserIdentifier userIdent = new UserIdentifierImpl();
		String idGen = userIdent.generateId();
		String codeGen = userIdent.generateCode();

		// Set data for DB save with encrypted pass
		String enCode = userIdent.encryptCode(codeGen);
		userIdent.setId(idGen);
		userIdent.setCode(enCode);

		// Create user in DB
		userJDBC.createUser(user, userIdent);

		// Successful registration
		userJDBC.saveAction("Register", "SUCCESS", "NULL", idGen);

		// Set plain code to send back to client
		userIdent.setCode(codeGen);

		return userIdent;
	}

	@Override
	public void authUser(UserIdentifier userIdentifier)
			throws UserNotFoundException, UserMultipleRecordsException, NoActionFoundException {
		log.info("User authentification for user id: " + userIdentifier.getId());

		// Search user code by id
		String codeDB = userJDBC.findById(userIdentifier.getId());

		String decryptedCodeDB = userIdentifier.decryptCode(codeDB);

		// Check authentication condition : code match avec db code
		if (!decryptedCodeDB.equals(userIdentifier.getCode())) {
			log.info("Code doesn't match. Login failed.");
			userJDBC.saveAction("Authenticate", "FAILED", "Code doesn't match.", userIdentifier.getId());
			throw new UserNotFoundException("User not found. Please register.");
		}

		// Check authentication condition : successful registration
		if (!userJDBC.getAction(userIdentifier.getId(), "Register", "SUCCESS").equals("")) {

			// Successful authentication
			userJDBC.saveAction("Authenticate", "SUCCESS", "NULL", userIdentifier.getId());
		} else {
			log.info("No registration found for user: " + userIdentifier.getId());
			userJDBC.saveAction("Authenticate", "FAILED", "No registration found.", userIdentifier.getId());
			throw new NoActionFoundException("User not registered. Please register first.");
		}
	}

	@Override
	public void createVote(VoteIdentifier voteIdentifier)
			throws NoActionFoundException, UserMultipleRecordsException, VoteAlreadyFoundException {
		log.info("Voting for user id: " + voteIdentifier.getId());

		// Check vote conditions: successful authentication and no previous vote present
		if (userJDBC.getAction(voteIdentifier.getId(), "Authenticate", "SUCCESS").equals("")) {
			throw new NoActionFoundException("User not authenticated. Please authenticate first.");
		} else {
			if (!userJDBC.getAction(voteIdentifier.getId(), "Vote", "SUCCESS").equals("")) {
				throw new VoteAlreadyFoundException(
						"You have already voted. To log a complain, please call customer support.");
			} else {
				// Sucessful vote
				userJDBC.createVote(voteIdentifier);
				userJDBC.saveAction("Vote", "SUCCESS", "NULL", voteIdentifier.getId());
			}
		}
	}
}
