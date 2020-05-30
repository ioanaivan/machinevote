package com.mdv.model;

/**
 * @brief Class to model the user documents that are sent to government for
 *        validation
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
public class UserDocuments {
	/**
	 * @brief User's national card ID
	 */
	private String CarteID;
	/**
	 * @brief User's security card ID
	 */
	private String SecuID;

	/**
	 * @brief Constructor
	 */
	public UserDocuments(String nationalCardId, String securityCardId) {
		this.CarteID = nationalCardId;
		this.SecuID = securityCardId;
	}

	/**
	 * @brief Getter for national card ID
	 * @return <b>String</b> CarteID
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String getCarteID() {
		return CarteID;
	}

	/**
	 * @brief Setter for national card ID
	 * @param String the national card ID
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void setCarteID(String carteID) {
		this.CarteID = carteID;
	}

	/**
	 * @brief Getter for security card ID
	 * @return <b>String</b> SecuID
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String getSecuID() {
		return SecuID;
	}

	/**
	 * @brief Setter for security card ID
	 * @param String the security card ID
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void setSecuID(String secuID) {
		this.SecuID = secuID;
	}
}
