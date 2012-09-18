package com.emc.xcp.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.emc.xcp.domain.Contact;

public interface ContactDAO {
	SessionFactory sessionFactory = null;

	public Contact getById(int id);

	public List<Contact> searchContacts(String name);

	public List<Contact> getAllContacts();

	public int save(Contact contact);

	public void update(Contact contact);

	public void delete(int id);

}
