package com.mdv.model;

import java.util.UUID;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @brief Class to model the user's identifiers
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
public class UserIdentifierImpl implements UserIdentifier {

	/**
	 * @brief Logger for the GovClient class
	 */
	private static Logger log = LoggerFactory.getLogger(User.class);

	/**
	 * @brief User's unique identifier
	 */
	private String id;
	/**
	 * @brief User's code
	 */
	private String code;
	/**
	 * @brief Encryptor used for password encryption
	 * 
	 * @author Sara Jabert
	 * @date 29/05/2020
	 */
	BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

	/**
	 * @brief Getter for unique ID
	 * @return <b>String</b> id
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String getId() {
		return id;
	}

	/**
	 * @brief Getter for passcode
	 * @return <b>String</b> passcode
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @brief Constructor
	 */
	UserIdentifierImpl(String id, String code) {
		this.id = id;
		this.code = code;
	}

	/**
	 * @brief Default constructor
	 */
	public UserIdentifierImpl() {
		this.code = "";
		this.id = "";
	}

	/**
	 * @brief Setter for user's passcode
	 * @param String user's passcode
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	@Override
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @brief Setter for user's unique identifier
	 * @param String user's unique identifier
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @brief Generate user's passcode
	 * @return <b>String</b> passcode
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String generateCode() {
		this.setCode(this.generatePassayCode());
		return this.getCode();
	}

	/**
	 * @brief Encrypt user's passcode
	 * @return <b>String</b> enCode
	 * @param String plain code
	 * 
	 * @author Sara Jabert
	 * @date 29/05/2020
	 */
	public String encryptCode(String code) {
		textEncryptor.setPassword("machinedevote");
		String enCode = textEncryptor.encrypt(code);
		return enCode;
	}

	/**
	 * @brief Encrypt user's passcode
	 * @return <b>String</b> deCode
	 * @param String encrypted code
	 * 
	 * @author Sara Jabert
	 * @date 29/05/2020
	 */
	public String decryptCode(String code) {
		textEncryptor.setPassword("machinedevote");
		String deCode = textEncryptor.decrypt(code);
		return deCode;
	}

	/**
	 * @brief Generate user's passcode
	 * @return <b>String</b> passcode
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
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

	/**
	 * @brief Generate user's unique id
	 * @return <b>String</b> id
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String generateId() {
		this.setId(UUID.randomUUID().toString());
		log.info("Id generated for user: " + this.getId());
		return this.getId();
	}

}
