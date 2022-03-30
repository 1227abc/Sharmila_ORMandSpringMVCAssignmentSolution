package com.gl.customerrelationship.main;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gl.customerrelationship.entity.Customer;
import com.gl.customerrelationship.service.CustomerService;
import com.gl.customerrelationship.service.CustomerServiceImpl;

public class Appmain {

	public static void main(String[] args) {
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
		SessionFactory sessionFactory = con.buildSessionFactory();

		CustomerService customerService = new CustomerServiceImpl(sessionFactory);

	}

}
