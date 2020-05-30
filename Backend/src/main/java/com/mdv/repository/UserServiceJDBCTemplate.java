/**
 * @package com.mdv.repository
 * @brief Connections to database
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

import com.mdv.exceptions.NoActionFoundException;
import com.mdv.exceptions.UserAlreadyFoundException;
import com.mdv.exceptions.UserMultipleRecordsException;
import com.mdv.exceptions.UserNotFoundException;
import com.mdv.model.*;

/**
 * @brief Class to model the vote identifier
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
@Transactional
@Repository
public class UserServiceJDBCTemplate {
	/**
	 * @brief Logger
	 */
	private Logger log = LoggerFactory.getLogger(UserServiceJDBCTemplate.class);

	/**
	 * @brief Template for Jdbc queries
	 */
	JdbcTemplate jdbcTemplate;

	/**
	 * @brief Setter for datasource in jdbctemplate
	 * @param DataSource the connection parameters to the database
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * @brief Call DB for inserting user
	 * @param User           the user to be created
	 * @param UserIdentifier the identifiers for the user
	 * @return User
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public User createUser(User newUser, UserIdentifier userIdent) {
		log.info("JDBC call for user creation for user: firstname: " + newUser.getFirstName() + ", name: "
				+ newUser.getName() + ", location: " + newUser.getLocation());

		jdbcTemplate.update(
				"INSERT INTO mdv.electeur(idelecteur, nom, prenom, commune, carteid, secuid, code) VALUES (?, ?, ?, ?, ?, ?, ?)",
				new Object[] { userIdent.getId(), newUser.getName(), newUser.getFirstName(), newUser.getLocation(),
						newUser.getNationalCardId(), newUser.getSecurityCardId(), userIdent.getCode() });

		return newUser;
	}

	/**
	 * @brief Call DB for searching user by ID card
	 * @param String the national card ID
	 * @exception UserAlreadyFoundException    if user already found in database
	 * @exception UserMultipleRecordsException if multiple users found in database
	 * @return void
	 * 
	 * 
	 * @author Sara Jabert
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
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
			log.info("User not found in DB, can be created");
		} catch (IncorrectResultSizeDataAccessException ex) {
			log.info("User already registered");
			saveAction("Register", "FAILED", "User already registered. Multiple records found.", null);
			throw new UserMultipleRecordsException("User already registered. Multiple records found.");
		}
	}

	/**
	 * @brief Call DB for searching user by security card ID
	 * @param String the security card ID
	 * @exception UserAlreadyFoundException    if user already found in database
	 * @exception UserMultipleRecordsException if multiple users found in database
	 * @return void
	 * 
	 * 
	 * @author Ioana Ivan
	 * @date 30/05/2020
	 */
	public void findBySecuCard(String secu) throws UserAlreadyFoundException, UserMultipleRecordsException {
		log.info("JDBC call for user verification security ID: " + secu);

		String secuID = "";
		String sql = "SELECT SecuID FROM electeur WHERE SecuID = ?";
		try {
			secuID = jdbcTemplate.queryForObject(sql, new Object[] { secu }, String.class);

			if (!secuID.isEmpty()) {
				log.info("User already registered for security card: " + secu);
				throw new UserAlreadyFoundException("User already registered.");
			}
		} catch (EmptyResultDataAccessException e) {
			log.info("User not found in DB, can be created");
		} catch (IncorrectResultSizeDataAccessException ex) {
			log.info("User already registered");
			saveAction("Register", "FAILED", "User already registered. Multiple records found.", null);
			throw new UserMultipleRecordsException("User already registered. Multiple records found.");
		}
	}

	/**
	 * @brief Call DB for searching user by User identifiers
	 * @param UserIdentifier the user's identifiers
	 * @exception UserNotFoundException        if user identifiers not found in
	 *                                         database
	 * @exception UserMultipleRecordsException if multiple users identifiers found
	 *                                         in database
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void findByIdentifier(UserIdentifier userIdentifier)
			throws UserNotFoundException, UserMultipleRecordsException {
		log.info("JDBC call for user authentification ID and code " + userIdentifier.getCode() + " "
				+ userIdentifier.getId());

		try {
			jdbcTemplate.queryForObject("SELECT 1 FROM electeur WHERE code = ? AND idElecteur = ?",
					new Object[] { userIdentifier.getCode(), userIdentifier.getId() }, Integer.class);

		} catch (EmptyResultDataAccessException e) {
			log.info("Login failed.");
			saveAction("Authenticate", "FAILED", "User not found.", userIdentifier.getId());
			throw new UserNotFoundException("User not found. Please register.");
		} catch (IncorrectResultSizeDataAccessException ex) {
			log.info("Multiple users found.");
			saveAction("Authenticate", "FAILED", "Multiple users found.", userIdentifier.getId());
			throw new UserMultipleRecordsException("Multiple records found");
		}
	}

	/**
	 * @brief Call DB for searching user by user unique identifier
	 * @param String the user's unique id
	 * @return void
	 * @exception UserNotFoundException        if user identifier not found in
	 *                                         database
	 * @exception UserMultipleRecordsException if multiple users identifiers found
	 *                                         in database
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String findById(String id) throws UserNotFoundException, UserMultipleRecordsException {
		log.info("JDBC call for user authentification ID " + id);

		try {
			return jdbcTemplate.queryForObject("SELECT code FROM electeur WHERE idElecteur = ?", new Object[] { id },
					String.class);

		} catch (EmptyResultDataAccessException e) {
			log.info("User not found. Please register first.");
			saveAction("Authenticate", "FAILED", "User not found.", id);
			throw new UserNotFoundException("User not found. Please register.");
		} catch (IncorrectResultSizeDataAccessException ex) {
			log.info("Multiple users found.");
			saveAction("Authenticate", "FAILED", "Multiple users found.", id);
			throw new UserMultipleRecordsException("Multiple records found");
		}
	}

	/**
	 * @brief Call DB for searching a specific action made by the user
	 * @param String the user's id
	 * @param String the action performed (Registrer, Authenticate, Vote)
	 * @param String (SUCCESS, FAILED)
	 * 
	 * @exception NoActionFoundException if user identifier not found in database
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String getAction(String userId, String action, String type) throws NoActionFoundException {
		log.info("JDBC call for checking status for action: " + action + ", for userId: " + userId);
		String actionId = "";
		try {
			actionId = jdbcTemplate.queryForObject(
					"SELECT idAction FROM actions WHERE idElecteur = ? and type = ? and statut = ? LIMIT 1 ",
					new Object[] { userId, action, type }, String.class);

		} catch (EmptyResultDataAccessException e) {
			log.info("No records returned for  " + action + " first. " + userId);
		}

		return actionId;
	}

	/**
	 * @brief Call DB for inserting vote in database
	 * @param VoteIdentifier the vote's identifier
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void createVote(VoteIdentifier voteIdentifier) {
		log.info("JDBC call for vote for ID: " + voteIdentifier.getId());

		jdbcTemplate.update("INSERT INTO mdv.vote(voterid, optionvote) VALUES (?, ?)",
				new Object[] { voteIdentifier.getId(), voteIdentifier.getVoteOption() });
	}

	/**
	 * @brief Call DB for inserting a specific action in the DB
	 * @param String  (Registrer, Authenticate, Vote)
	 * @param String  (SUCCESS, FAILED)
	 * @param String  the error message
	 * @param Strings the user's unique identifier
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void saveAction(String type, String status, String error, String voterid) {
		log.info("JDBC call for registering action: " + type + ", intiated by: " + voterid);

		jdbcTemplate.update("INSERT INTO mdv.actions(type, statut, erreur, idelecteur) VALUES (?, ?, ?, ?)",
				new Object[] { type, status, error, voterid });
	}
}