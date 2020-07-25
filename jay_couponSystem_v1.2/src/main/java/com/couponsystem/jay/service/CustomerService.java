package com.couponsystem.jay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsystem.jay.beans.Customer;
import com.couponsystem.jay.repo.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repo;
	
	//50%
	public void addCustomer(Customer customer) {
		repo.save(customer);
	}

	public void updateCustomer(Customer customer) {
		repo.saveAndFlush(customer);
	}

	public void deleteCustomer(int customerID) {
		repo.deleteById(customerID);

	}

	public List<Customer> getAllCustomer() {

		return repo.findAll();
	}

	public Customer getOneCustomerByCustomerID(int customerID) {
		return repo.getOne(customerID);
	}
	
	//40%
	public boolean checkIfCustomer(String email, String password) {
		return repo.findByEmailAndPassword(email, password) != null;
	}

	public Customer getOneCustomerByEmailAndPassword(String email, String password) {
		return repo.findCustomerByEmailAndPassword(email, password);
	}
	
	//10%
	// get the customer coupons.
	
}
