/**
 * @package com.mdv.services
 * @brief Services provider
 */
package com.mdv.services;

import com.mdv.exceptions.NoActionFoundException;
import com.mdv.exceptions.UserAlreadyFoundException;
import com.mdv.exceptions.UserMultipleRecordsException;
import com.mdv.exceptions.UserNotFoundException;
import com.mdv.exceptions.VoteAlreadyFoundException;
import com.mdv.model.User;
import com.mdv.model.UserIdentifier;
import com.mdv.model.VoteIdentifier;

/**
 * @brief Interface for the services available for user
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
public interface UserService {

	/**
	 * @brief create a new user
	 * @param User user's data
	 * @return UserIdentifier generated user's identifiers
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public UserIdentifier createUser(User user)
			throws UserAlreadyFoundException, UserMultipleRecordsException, UserNotFoundException;

	/**
	 * @brief authorize a new user's login
	 * @param UserIdentifier the identifiers for login
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void authUser(UserIdentifier userIdentifier)
			throws UserNotFoundException, UserMultipleRecordsException, NoActionFoundException;

	/**
	 * @brief create a new vote
	 * @param VoteIdentifier the vote data
	 * @return void
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 */
	public void createVote(VoteIdentifier voteIdentifier) throws NoActionFoundException, VoteAlreadyFoundException;

}
