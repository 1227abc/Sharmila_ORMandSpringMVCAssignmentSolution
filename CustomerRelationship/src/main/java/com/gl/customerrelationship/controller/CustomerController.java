package com.gl.customerrelationship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.customerrelationship.entity.Customer;
import com.gl.customerrelationship.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String listBooks(Model theModel) {

		System.out.println("request recieved");

		List<Customer> customers = customerService.findAll();

		theModel.addAttribute("Customers", customers);

		return "list-Customers";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Customer customer = new Customer();

		theModel.addAttribute("Customer", customer);

		return "Customer-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

		// get the Book from the service
		Customer customer = customerService.findById(theId);

		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Customer", customer);

		// send over to our form
		return "Customer-form";
	}

	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id, @RequestParam("First Name") String fname,
			@RequestParam("Last Name") String lname, @RequestParam("email") String email) {

		System.out.println(id);
		Customer customer;
		if (id != 0) {
			customer = customerService.findById(id);
			customer.setFirstName(fname);

			customer.setLastName(lname);
			customer.setEmail(email);
		} else
			customer = new Customer(fname, lname, email);
		// save the Book
		customerService.save(customer);

		// use a redirect to prevent duplicate submissions
		return "redirect:/customer/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {
		
		System.out.println("Entering delete");

		customerService.deleteById(theId);

		return "redirect:/customer/list";

	}

}