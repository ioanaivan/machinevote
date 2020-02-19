package com.mdv.services;

import java.util.UUID;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mdv.model.User;

public class UserIdentifierImpl implements UserIdentifier {
	
	private static Logger log = LoggerFactory.getLogger(User.class);

	private String id;
	private String code;
	
	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}
	
	UserIdentifierImpl(String id, String code){
		this.id = id;
		this.code = code;
	}
	
	public UserIdentifierImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String generateCode() {
		this.setCode(this.generatePassayCode());
		return this.getCode();
	}
	
	// TODO write a proper one -> courtesy of 
	public String generatePassayCode() {
	    PasswordGenerator gen = new PasswordGenerator();
	    CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
	    CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
	    lowerCaseRule.setNumberOfCharacters(2);
	 
	    CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
	    CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
	    upperCaseRule.setNumberOfCharacters(2);
	 
	    CharacterData digitChars = EnglishCharacterData.Digit;
	    CharacterRule digitRule = new CharacterRule(digitChars);
	    digitRule.setNumberOfCharacters(2);
	 
	    CharacterData specialChars = new CharacterData() {
	        public String getErrorCode() {
	            return "CHAR_DATA_ERR";
	        }
	 
	        public String getCharacters() {
	            return "!@#$%^&*()_+";
	        }
	    };
	    CharacterRule splCharRule = new CharacterRule(specialChars);
	    splCharRule.setNumberOfCharacters(2);
	 
	    String password = gen.generatePassword(10, splCharRule, lowerCaseRule, 
	      upperCaseRule, digitRule);
	    return password;
	}
	
	public String generateId() {
		this.setId(UUID.randomUUID().toString());
		log.info("Id generated for user: " + this.getId());
		return this.getId();
	}

}
