package com.gl.customerrelationship.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.customerrelationship.entity.Customer;

@Service
public interface CustomerService {

	public List<Customer> findAll();

	public Customer findById(int id);

	public void save(Customer stu);

	public void deleteById(int id);

}
