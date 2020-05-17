/*
 * Class in charge of modeling user identifier returned at user creation step
 */

package com.mdv.model;

import java.util.UUID;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.jasypt.util.text.BasicTextEncryptor;

public class UserIdentifierImpl implements UserIdentifier {

	private static Logger log = LoggerFactory.getLogger(User.class);

	private String id;
	private String code;
	BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	UserIdentifierImpl(String id, String code) {
		this.id = id;
		this.code = code;
	}

	public UserIdentifierImpl() {
		this.code = "";
		this.id = "";
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

	public String enCode(String code) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		String enCode = encoder.encode(code);
		return enCode;
	}

	public String encryptCode(String code) {
		textEncryptor.setPassword(code);
		String enCode = textEncryptor.encrypt(code);
		return enCode;
	}

	public String decryptCode(String code) {
		String deCode = textEncryptor.decrypt(code);
		return deCode;
	}

	// TODO draft method - move to another class
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

		String password = gen.generatePassword(10, splCharRule, lowerCaseRule, upperCaseRule, digitRule);
		return password;
	}

	// TODO generate a shorter id ?
	public String generateId() {
		this.setId(UUID.randomUUID().toString());
		log.info("Id generated for user: " + this.getId());
		return this.getId();
	}

}
