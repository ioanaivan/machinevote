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
	public UserIdentifier createUser(User user) throws UserAlreadyFoundException, UserMultipleRecordsException {
		log.info("User creation for user firstName: " + user.getFirstName() + ", name : " + user.getName());

		// Vérifier que l'utilisateur est une personne qui existe dans Gov DB
		String response = govClient.sendGetUser(user);

		// Vérifier que l'utilisateur est enregistré dans MDV DB
		userJDBC.findByIdCard(user.getNationalCardId());

		// Générer un identificateur et un code avant de stocker dans la base de données
		UserIdentifier userIdent = new UserIdentifierImpl();
		String idGen = userIdent.generateId();
		String codeGen = userIdent.generateCode();

		log.info("Generated code: " + codeGen);

		// Crypter le code
		String enCode = userIdent.encryptCode(codeGen);
		log.info("Encoded: " + enCode);

		// Stocker l'identificateur et le code crypté dans la base de donnée
		userIdent.setId(idGen);
		userIdent.setCode(enCode);

		userJDBC.createUser(user, userIdent);

		// Enregistrement avec succès
		userJDBC.saveAction("Register", "SUCCESS", "NULL", idGen);

		// Décrypter le code pour l'envoyer à l'utilisateur
		String deCode = userIdent.decryptCode(enCode);
		userIdent.setCode(deCode);

		// re-encrypt for testing
		// log.info("Re-encypted: " + userIdent.encryptCode(deCode));

		return userIdent;
	}

	@Override
	public void authUser(UserIdentifier userIdentifier)
			throws UserNotFoundException, UserMultipleRecordsException, NoActionFoundException {
		log.info("User authentification for user id: " + userIdentifier.getId());

		// Encrypt user code to check in DB
		String code = userIdentifier.getCode();
		String encryptedCode = userIdentifier.encryptCode(code);
		userIdentifier.setCode(encryptedCode);

		userJDBC.findByIdentifier(userIdentifier);

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
