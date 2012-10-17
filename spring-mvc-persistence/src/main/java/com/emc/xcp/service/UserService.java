/**
 * 
 */
package com.emc.xcp.service;

import java.util.List;

import com.emc.xcp.domain.User;

/**
 * @author jajarp
 *
 */
public interface UserService {
	
	public User getUserById(String id);

	public List<User> getAllUsers();

	public String saveUser(User user);

	public void updateUser(User user);

	public void deleteUser(String id);

}
