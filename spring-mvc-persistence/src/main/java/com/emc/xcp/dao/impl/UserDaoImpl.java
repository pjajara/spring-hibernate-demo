/**
 * 
 */
package com.emc.xcp.dao.impl;

import java.util.List;

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

	@Autowired
	private SessionFactory sessionFactory;
	
	
	/* (non-Javadoc)
	 * @see com.emc.xcp.dao.UserDao#getUserById(int)
	 */
	@Override
	public User getUserById(String id) {
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
		return (String) sessionFactory.getCurrentSession().save(user);
	}

	/* (non-Javadoc)
	 * @see com.emc.xcp.dao.UserDao#updateUser(com.emc.xcp.domain.User)
	 */
	@Override
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().merge(user);
	}

	/* (non-Javadoc)
	 * @see com.emc.xcp.dao.UserDao#deleteUser(int)
	 */
	@Override
	public void deleteUser(String id) {
		User userToDelete = getUserById(id);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}

}
