package com.mdv.model;

public class VoteIdentifier {

	String id;
	String voteOption;

	public VoteIdentifier(String id, String voteOption) {
		this.id = id;
		this.voteOption = voteOption;
	}

	public VoteIdentifier() {
		this.id = "";
		this.voteOption = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVoteOption() {
		return voteOption;
	}

	public void setVoteOption(String voteOption) {
		this.voteOption = voteOption;
	}

}
