/*
 * Interface exposing UserServiceImpl methods
 */

package com.mdv.services;

import com.mdv.model.User;
import com.mdv.model.UserIdentifier;

public interface UserService {

	public UserIdentifier createUser(User user);

}
