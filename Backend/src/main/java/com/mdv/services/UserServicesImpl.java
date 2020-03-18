/*
 * Class containing services used for processing
 */

package com.mdv.services;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import com.mdv.model.*;
import com.mdv.repository.*;
=======

import com.mdv.model.*;
import com.mdv.repository.*;
import com.mdv.services.*;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.RedirectView;

import java.lang.Exception;
>>>>>>> refs/remotes/origin/VAlpha

@Service
public class UserServicesImpl implements UserService {

	@Autowired
	private UserServiceJDBCTemplate userJDBC;

	private static Logger log = LoggerFactory.getLogger(UserService.class);

	public UserIdentifier createUser(User user) {
		log.info("User creation for user: " + user.getFirstName() + "name : " + user.getName());
<<<<<<< HEAD

		// TODO repair method
		String exist = userJDBC.findByIdCard(user.getNationalCardId());
		// TODO test exist

=======
		
		//User exist = userJDBC.findByIdCard(user.getNationalCardId());
		
		String exist = userJDBC.findByIdCard(user.getNationalCardId());
		
		if (exist == null)
		{	
>>>>>>> refs/remotes/origin/VAlpha
		UserIdentifier userIdent = new UserIdentifierImpl();

		// generate user identifier and password before store in DB
		String idGen = userIdent.generateId();
		String codeGen = userIdent.generateCode();
<<<<<<< HEAD
		userIdent.setId(idGen);
		userIdent.setCode(codeGen);

		userJDBC.createUser(user, userIdent);
=======
		user.setId(idGen);
		user.setCode(codeGen);
		// TODO setCode when DB ready
	
		userJDBC.createUser(user);
>>>>>>> refs/remotes/origin/VAlpha
		return userIdent;
<<<<<<< HEAD
	}
=======
		}

		javax.swing.JOptionPane.showMessageDialog(null,this, "Numéro de carte d'identité existe déjà",0);
		new RedirectView("redirectedUrl");
		return null;
}
>>>>>>> refs/remotes/origin/VAlpha
}

