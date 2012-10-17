/**
 * 
 */
package com.emc.xcp.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emc.xcp.dao.UserDao;
import com.emc.xcp.domain.User;

/**
 * @author jajarp
 *
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	/* (non-Javadoc)
	 * @see com.emc.xcp.dao.UserDao#getUserById(int)
	 */
	@Override
	public User getUserById(String id) {
		logger.debug("Getting User by id: "+ id.toString());
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	/* (non-Javadoc)
	 * @see com.emc.xcp.dao.UserDao#getAllUsers()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see com.emc.xcp.dao.UserDao#saveUser(com.emc.xcp.domain.User)
	 */
	@Override
	public String saveUser(User user) {
		logger.debug("Saving User to database: "+ user.toString());
		return (String) sessionFactory.getCurrentSession().save(user);
	}

	/* (non-Javadoc)
	 * @see com.emc.xcp.dao.UserDao#updateUser(com.emc.xcp.domain.User)
	 */
	@Override
	public void updateUser(User user) {
		logger.debug("Updating User from database: " + user.toString());
		sessionFactory.getCurrentSession().merge(user);
	}

	/* (non-Javadoc)
	 * @see com.emc.xcp.dao.UserDao#deleteUser(int)
	 */
	@Override
	public void deleteUser(String id) {
		User userToDelete = getUserById(id);
		logger.debug("Deleting User from database: " + userToDelete);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}

}
