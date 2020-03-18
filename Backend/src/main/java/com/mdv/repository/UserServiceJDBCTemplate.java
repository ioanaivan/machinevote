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
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import com.mdv.model.*;
import com.mdv.model.UserIdentifier;

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
		log.debug("JDBC call for user creation for user: firstname: " + newUser.getFirstName() + ", name: "
				+ newUser.getName() + ", location: " + newUser.getLocation());

		jdbcTemplate.update(
				"INSERT INTO mdv.electeur(idelecteur, nom, prenom, commune, carteid, secuid, code) VALUES (?, ?, ?, ?, ?, ?, ?)",
				new Object[] { userIdent.getId(), newUser.getName(), newUser.getFirstName(), newUser.getLocation(),
						newUser.getNationalCardId(), newUser.getSecurityCardId(), userIdent.getCode() });

		return newUser;
	}

	public String findByIdCard(String card) {
		log.debug("JDBC call for user verification ID: " + card);

		String carte = null;
		String sql = "SELECT CarteID FROM electeur WHERE CarteID = ?";
		try {
			carte = jdbcTemplate.queryForObject(sql, new Object[] { card }, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		return carte;
	}
}