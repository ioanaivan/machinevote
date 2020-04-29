/*
 * Interface exposing UserServiceImpl methods
 */

package com.mdv.services;

import com.mdv.exceptions.UserAlreadyFoundException;
import com.mdv.exceptions.UserMultipleRecordsException;
import com.mdv.exceptions.UserNotFoundException;
import com.mdv.model.User;
import com.mdv.model.UserIdentifier;
import com.mdv.model.VoteIdentifier;

public interface UserService {

	public UserIdentifier createUser(User user) throws UserAlreadyFoundException, UserMultipleRecordsException;

	public void authUser(UserIdentifier userIdentifier) throws UserNotFoundException, UserMultipleRecordsException;

	public void registerVote(VoteIdentifier voteIdentifier);

}
