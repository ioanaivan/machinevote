/*
 * Class containing services used for processing
 */

package com.mdv.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mdv.model.*;
import com.mdv.repository.*;

@Service
public class UserServicesImpl implements UserService {

	@Autowired
	private UserServiceJDBCTemplate userJDBC;

	private static Logger log = LoggerFactory.getLogger(UserService.class);

	public UserIdentifier createUser(User user) {
		log.info("User creation for user: " + user.getFirstName() + "name : " + user.getName());

		// TODO repair method
		String exist = userJDBC.findByIdCard(user.getNationalCardId());
		// TODO test exist

		UserIdentifier userIdent = new UserIdentifierImpl();

		// generate user identifier and password before store in DB
		String idGen = userIdent.generateId();
		String codeGen = userIdent.generateCode();
		userIdent.setId(idGen);
		userIdent.setCode(codeGen);

		userJDBC.createUser(user, userIdent);
		return userIdent;
	}
}
