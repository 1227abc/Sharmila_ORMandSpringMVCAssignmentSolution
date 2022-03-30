package com.gl.customerrelationship.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gl.customerrelationship.entity.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService {
	private SessionFactory sessionfactory;
	private Session session;

	@Autowired
	public CustomerServiceImpl(SessionFactory sessionFactory) {
		this.session = sessionFactory.openSession();
	}

	@Transactional
	public List<Customer> findAll() {
		List<Customer> customerList = session.createQuery("from Customer", Customer.class).list();
		return customerList;
	}

	@Transactional
	public Customer findById(int id) {
		Customer customer = session.get(Customer.class, id);
		return customer;
	}

	@Transactional
	public void save(Customer customer) {
		session.beginTransaction();
		session.saveOrUpdate(customer);
		session.getTransaction().commit();
	}

	@Transactional
	public void deleteById(int theId) {
		session.beginTransaction();
		Customer customer = session.get(Customer.class, theId);
		System.out.println("Customer details:" + customer.getId() + customer.getFirstName());
		session.delete(customer);
		session.getTransaction().commit();

	}

}