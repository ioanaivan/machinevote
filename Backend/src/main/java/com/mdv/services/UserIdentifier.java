package com.mdv.services;

public interface UserIdentifier {
	public void setCode(String code);
	public void setId(String id);
	public String generateId();
	public String generateCode();
}
