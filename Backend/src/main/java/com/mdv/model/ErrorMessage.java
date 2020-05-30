/**
 * @package com.mdv.model
 * @brief Representations of objects 
 */
package com.mdv.model;

/**
 * @brief Class to model error message
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
public class ErrorMessage {
	/**
	 * @brief Error message
	 */
	String message;

	/**
	 * @brief Constructor
	 */
	public ErrorMessage(String message) {
		super();
		this.message = message;
	}

	/**
	 * @brief Getter for error message
	 * @return <b>String</b> the error message
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @brief Setter for error message
	 * @param Message the error message to be set
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
