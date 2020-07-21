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
	
	public void addCustomer(Customer customer) {
		repo.save(customer);
	}
	
	public void updateCustomer(Customer customer) {
		repo.saveAndFlush(customer);
	}
	
	public void deleteCustomer(int customerID) {
		repo.deleteById(customerID);
		
	}
	
	public List<Customer> getAllCustomer(){
		
		return repo.findAll();
	}
	
	public Customer getOneCustomer(int customerID) {
		return repo.getOne(customerID);
	}
	
//	public boolean checkIfCustomer(String email, String password) {
//		return false;
//	}
//	
//	public Customer getOneCustomerByEmailAndPassword(String email, String password) {
//		return null;
//	}
}
