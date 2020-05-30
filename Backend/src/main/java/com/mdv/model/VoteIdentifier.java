package com.mdv.model;

/**
 * @brief Class to model the vote identifier
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
public class VoteIdentifier {

	/**
	 * @brief user unique id
	 */
	String id;
	/**
	 * @brief vote option
	 */
	String voteOption;

	/**
	 * @brief Constructor
	 */
	public VoteIdentifier(String id, String voteOption) {
		this.id = id;
		this.voteOption = voteOption;
	}

	/**
	 * @brief Default Constructor
	 */
	public VoteIdentifier() {
		this.id = "";
		this.voteOption = "";
	}

	/**
	 * @brief Getter for user's unique ID
	 * @return <b>String</b> id
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String getId() {
		return id;
	}

	/**
	 * @brief Setter for user's id
	 * @param id
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @brief Getter for vote option
	 * @return <b>String</b> voteOption
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String getVoteOption() {
		return voteOption;
	}

	/**
	 * @brief Setter for user's vote option
	 * @param voteOption
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void setVoteOption(String voteOption) {
		this.voteOption = voteOption;
	}

}
