package com.mdv.model;

public class UserDocuments {
	private String CarteID;
	private String SecuID;

	public UserDocuments(String nationalCardId, String securityCardId) {
		this.CarteID = nationalCardId;
		this.SecuID = securityCardId;
	}

	public String getCarteID() {
		return CarteID;
	}

	public void setCarteID(String carteID) {
		this.CarteID = carteID;
	}

	public String getSecuID() {
		return SecuID;
	}

	public void setSecuID(String secuID) {
		this.SecuID = secuID;
	}
}
