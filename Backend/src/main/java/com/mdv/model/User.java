package com.mdv.model;

public class User {
	private String id;
	private String firstName;
	private String name;
	private String location;
	private String nationalCardId;
	private String securityCardId;
	
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
}
