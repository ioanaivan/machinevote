package com.mdv.model;

import java.sql.Timestamp;

/**
 * @brief Class to model the user data
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
public class User {
	/**
	 * @brief User's first name
	 */
	private String firstName;
	/**
	 * @brief User's last name
	 */
	private String name;
	/**
	 * @brief User's location
	 */
	private String location;
	/**
	 * @brief User's national card ID
	 */
	private String nationalCardId;
	/**
	 * @brief User's security card ID
	 */
	private String securityCardId;

	/**
	 * @brief User's registration date
	 * 
	 * @author Sara Jabert
	 * @date 29/05/2020
	 */
	private Timestamp joinedAt;

	/**
	 * @brief Getter for first name
	 * @return <b>String</b> the firs name
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @brief Setter for first name
	 * @param String the user's first name
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @brief Getter for name
	 * @return <b>String</b> name
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String getName() {
		return name;
	}

	/**
	 * @brief Setter for last name
	 * @param String the user's last names
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @brief Getter for location
	 * @return <b>String</b> location
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @brief Setter for location
	 * @param String the user's location
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @brief Getter for national card ID
	 * @return <b>String</b> the national card ID
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String getNationalCardId() {
		return nationalCardId;
	}

	/**
	 * @brief Setter for national card ID
	 * @param String the national card ID
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void setNationalCardId(String nationalCardId) {
		this.nationalCardId = nationalCardId;
	}

	/**
	 * @brief Getter for security card ID
	 * @return <b>String</b> the security card ID
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020s
	 */
	public String getSecurityCardId() {
		return securityCardId;
	}

	/**
	 * @brief Setter for security card ID
	 * @param String the security card ID
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void setSecurityCardId(String securityCardId) {
		this.securityCardId = securityCardId;
	}

	/**
	 * @brief Getter for date of registration
	 * @return <b>String</b> the date joined
	 * 
	 * @author Sara Jabert
	 * @date 29/05/2020
	 */
	public Timestamp getJoinedAt() {
		return joinedAt;
	}

	/**
	 * @brief Setter for date of registration
	 * @param Timestamp the data of user registration
	 * @return void
	 * 
	 * @author Sara Jabert
	 * @date 29/05/2020
	 */
	public void setJoinedAt(Timestamp joinedAt) {
		this.joinedAt = joinedAt;
	}

}
