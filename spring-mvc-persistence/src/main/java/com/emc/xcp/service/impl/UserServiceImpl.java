/**
 * 
 */
package com.emc.xcp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.xcp.dao.UserDao;
import com.emc.xcp.domain.User;
import com.emc.xcp.service.UserService;

/**
 * @author jajarp
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	User user=null;
	
	@Override
	public User getUserById(String id) {
		user = userDao.getUserById(id);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> userList = userDao.getAllUsers();
		return userList;
	}

	@Override
	public String saveUser(User user) {
		String res = userDao.saveUser(user);
		return res;
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void deleteUser(String id) {
		userDao.deleteUser(id);
	}

}
