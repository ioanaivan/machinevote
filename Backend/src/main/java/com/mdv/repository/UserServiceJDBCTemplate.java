package com.mdv.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Timestamp;

import com.mdv.model.*;
import com.mdv.services.UserRowMapper;

@Transactional
@Repository
public class UserServiceJDBCTemplate {
	
	private Logger log = LoggerFactory.getLogger(UserServiceJDBCTemplate.class);

    JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User createUser(User newUser) {
    	log.debug("JDBC call for user creation ID: " + newUser.getId() + " firstname " + newUser.getFirstName() + " name : " + newUser.getName());
    	
    	jdbcTemplate.update("INSERT INTO mdv.electeur(idelecteur, nom, prenom, commune, carteid, secuid, code) VALUES (?, ?, ?, ?, ?, ?, ?)",
          new Object[] { newUser.getId(), newUser.getName(), newUser.getFirstName(), newUser.getLocation(), newUser.getNationalCardId(), newUser.getSecurityCardId(), newUser.getCode() });   
      
    	return newUser;    
    }

    public List<User> findAll() {
        List<User> list =  jdbcTemplate.query(
            "SELECT idelecteur, nom, prenom FROM electeur",
            new RowMapper<User>() {      
              public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getString("id")); 
                user.setName(rs.getString("nom")); 
                user.setFirstName(rs.getString("prenom"));
                return user;
              }                
        });
        return list;
    }

    public void delete(int id) {
       jdbcTemplate.update("DELETE FROM electeur where id = ? ", id);
    }

    public User updateTodo(User user) {
      jdbcTemplate.update("UPDATE electeur SET nom= ?, prenom = ?, commune = ?, carteid = ?, secuid = ? WHERE id = ?" , 
          new Object[] {user.getName(), user.getFirstName(), user.getLocation(), user.getNationalCardId(), user.getSecurityCardId(), user.getId()});
      return user;   
    }
    

    //Find a User by its ID Card.
    public User findByIdCard(String card) {
    	
    	User user = null;

    	String sql = "SELECT * FROM electeur WHERE CarteID = ?";
    	
    	try {
    	     user = jdbcTemplate.queryForObject(sql, new Object[] {card}, new UserRowMapper());
    	}
    	catch (EmptyResultDataAccessException e)
    	{
    		return null;
    	}

        return user;
    	
    }   
}