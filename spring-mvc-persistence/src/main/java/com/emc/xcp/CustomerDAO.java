/**
 * 
 */
package com.emc.xcp;

/**
 * @author jajarp
 *
 */

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Customer getById(int id){
		return (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> searchCustomersByFirstName(String firstName){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.add(Restrictions.ilike("firstName", firstName+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> searchCustomerByLastName(String lastName){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.add(Restrictions.ilike("lastName", lastName+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomers(){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		return criteria.list();
	}

	public int save(Customer customer){
		return (Integer) sessionFactory.getCurrentSession().save(customer);
	}
	
	public void update(Customer customer){
		sessionFactory.getCurrentSession().merge(customer);
	}
	
	public void delete(int id){
		Customer customer = getById(id);
		sessionFactory.getCurrentSession().delete(customer);
	}
}
