/*
 * Class in charge of modeling the user data received from client
 */

package com.mdv.model;

import java.sql.Timestamp;

public class User {
	private String firstName;
	private String name;
	private String location;
	private String nationalCardId;
	private String securityCardId;

	// When the User is creating is account.
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

	public Timestamp getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(Timestamp joinedAt) {
		this.joinedAt = joinedAt;
	}

}
