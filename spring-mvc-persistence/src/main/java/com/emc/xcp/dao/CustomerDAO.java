/**
 * 
 */
package com.emc.xcp.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.emc.xcp.domain.Customer;

/**
 * @author jajarp
 * 
 */
public interface CustomerDAO {

	public Customer getById(int id);

	public List<Customer> searchCustomersByFirstName(String firstName);

	public List<Customer> searchCustomerByLastName(String lastName);

	public List<Customer> getAllCustomers();

	public int save(Customer customer);

	public void update(Customer customer);

	public void delete(int id);

}
