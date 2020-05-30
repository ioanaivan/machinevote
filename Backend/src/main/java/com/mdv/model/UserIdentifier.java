package com.mdv.model;

/**
 * @brief Interface to model the response to the user creation request
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
public interface UserIdentifier {
	/**
	 * @brief Setter for user's passcode
	 * @param String the user's passcode
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void setCode(String code);

	/**
	 * @brief Setter for user's identifier
	 * @param String the user's identifier
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void setId(String id);

	/**
	 * @brief Generate identifier
	 * @return <b>String</b> id
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String generateId();

	/**
	 * @brief Generate passcode
	 * @return <b>String</b> passcode
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String generateCode();

	/**
	 * @brief Getter for ID
	 * @return <b>String</b> id
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String getId();

	/**
	 * @brief Getter for passcode
	 * @return <b>String</b> passcode
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String getCode();

	/**
	 * @brief Encrypt passcode
	 * @return <b>String</b>
	 * @param String plain code
	 * 
	 * @author Sara Jabert
	 * @date 29/05/2020
	 */
	public String encryptCode(String code);

	/**
	 * @brief Decrypt passcode
	 * @param String encrypted code
	 * @return <b>String</b>
	 * 
	 * @author Sara Jabert
	 * @date 29/05/2020
	 */
	public String decryptCode(String enCode);

}
