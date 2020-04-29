/*
 * Class in charge of 
 */

package com.mdv.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

import com.mdv.exceptions.UserAlreadyFoundException;
import com.mdv.exceptions.UserMultipleRecordsException;
import com.mdv.exceptions.UserNotFoundException;
import com.mdv.model.*;


@Transactional
@Repository
public class UserServiceJDBCTemplate {

	private Logger log = LoggerFactory.getLogger(UserServiceJDBCTemplate.class);

	JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public User createUser(User newUser, UserIdentifier userIdent) {
		log.info("JDBC call for user creation for user: firstname: " + newUser.getFirstName() + ", name: "
				+ newUser.getName() + ", location: " + newUser.getLocation());

		jdbcTemplate.update(
				"INSERT INTO mdv.electeur(idelecteur, nom, prenom, commune, carteid, secuid, code) VALUES (?, ?, ?, ?, ?, ?, ?)",
				new Object[] { userIdent.getId(), newUser.getName(), newUser.getFirstName(), newUser.getLocation(),
						newUser.getNationalCardId(), newUser.getSecurityCardId(), userIdent.getCode() });

		return newUser;
	}

	public void findByIdCard(String card) throws UserAlreadyFoundException, UserMultipleRecordsException {
		log.info("JDBC call for user verification ID: " + card);

		String carte = "";
		String sql = "SELECT CarteID FROM electeur WHERE CarteID = ?";
		try {
			carte = jdbcTemplate.queryForObject(sql, new Object[] { card }, String.class);

			if (!carte.isEmpty()) {
				log.info("User already registered for card: " + card);
				throw new UserAlreadyFoundException("User already registered.");
			}
		} catch (EmptyResultDataAccessException e) {
			log.info("User not found in DB");
		} catch (IncorrectResultSizeDataAccessException ex) {
			// log.info("User already registered");
			throw new UserMultipleRecordsException("User already registered. Multiple records found.");
		}
	}

	// retrieve data corresponding to IdCard and Secu
	public void findByIdCardSecuCard(UserIdentifier userIdentifier)
			throws UserNotFoundException, UserMultipleRecordsException {
		log.info("JDBC call for user authentification ID and code ");

		try {
			jdbcTemplate.queryForObject("SELECT 1 FROM electeur WHERE code = ? AND idElecteur = ?",
					new Object[] { userIdentifier.getCode(), userIdentifier.getId() }, Integer.class);

		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException("User not found. Please register.");
		} catch (IncorrectResultSizeDataAccessException ex) {
			throw new UserMultipleRecordsException("Multiple records found");
		}
	}

	public void registerVote(VoteIdentifier voteIdentifier) {
		log.info("JDBC call for vote for ID: " + voteIdentifier.getId());

		jdbcTemplate.update("INSERT INTO mdv.vote(voterid, optionvote) VALUES (?, ?)",
				new Object[] { voteIdentifier.getId(), voteIdentifier.getVoteOption() });
	}

	public void registerAction(String type, String status, String error, String voterid) {
		log.info("JDBC call for registering action: " + type + ", intiated by: " + voterid);

		if (carte == null) {
			log.info("User not found");
		} else {
			log.info("User found");
		}

		jdbcTemplate.update("INSERT INTO mdv.actions(type, statut, erreur, idelecteur) VALUES (?, ?, ?, ?)",
				new Object[] { type, status, error, voterid });

	}

	public void isRegistered(UserIdentifier userIdentifier) {
		// TODO Auto-generated method stub

	}
}