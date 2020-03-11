package com.mdv.model;

import java.util.UUID;
import java.sql.Timestamp;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.passay.CharacterData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mdv.services.UserService;

public class User {
	private String id;
	private String code;
	private String firstName;
	private String name;
	private String location;
	private String nationalCardId;
	private String securityCardId;
	
	//When the User is creating is account.
	private Timestamp joinedAt;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getNationalCardId() {
		return nationalCardId;
	}
	public void setNationalCardId(String nationalCardId) {
		this.nationalCardId = nationalCardId;
	}
	public String getSecurityCardId() {
		return securityCardId;
	}
	public void setSecurityCardId(String securityCardId) {
		this.securityCardId = securityCardId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public Timestamp getJoinedAt() {
		return joinedAt;
	}
	
	public void setJoinedAt(Timestamp joinedAt) {
		this.joinedAt = joinedAt;
	}
	
}
