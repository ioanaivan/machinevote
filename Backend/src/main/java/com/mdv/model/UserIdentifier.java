/*
 * Class to model the response to the user creation request
 */

package com.mdv.model;

public interface UserIdentifier {
	public void setCode(String code);

	public void setId(String id);

	public String generateId();

	public String generateCode();

	public String getId();

	public String getCode();

	public String encryptCode(String code);

	public String decryptCode(String enCode);

}
