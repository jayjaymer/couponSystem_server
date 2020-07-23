package com.couponsystem.jay.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.couponsystem.jay.beans.Customer;
import com.couponsystem.jay.service.CustomerService;
@Component
@Order(value = 3)
public class TestCustomer implements CommandLineRunner {
	@Autowired
	CustomerService customerService;
	@Override
	public void run(String... args) throws Exception {
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~CUSTOMER DUMMY TEST~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		
		Customer customer1 = new Customer();
		customer1.setFirstName("Customer1");
		customer1.setLastName("CustomerLast1");
		customer1.setEmail("customer1@gmail.com");
		customer1.setPassword("1234");
		
		Customer customer2 = new Customer();
		customer2.setFirstName("Customer2");
		customer2.setLastName("CustomerLast2");
		customer2.setEmail("customer2@gmail.com");
		customer2.setPassword("1234");
		
		Customer customer3 = new Customer();
		customer3.setFirstName("Customer3");
		customer3.setLastName("CustomerLast3");
		customer3.setEmail("customer3@gmail.com");
		customer3.setPassword("1234");
		
		customerService.addCustomer(customer1);
		customerService.addCustomer(customer2);
		customerService.addCustomer(customer3);
		System.out.println("customers 1,2,3 are added");
		
		System.out.println("update customer");
		System.out.println("customer1 email before "+customer1.getEmail());
		customer1.setEmail("ahfafdshhafsd");
		customerService.updateCustomer(customer1);
		System.out.println("customer1 email after "+customer1.getEmail());
		
		System.out.println("delete customer");
		customerService.deleteCustomer(3);
		System.out.println("customer one deleted");
		
		System.out.println("get all customers");
		System.out.println(customerService.getAllCustomer());
		
		System.out.println("get one customer by ID");
		System.out.println(customerService.getOneCustomer(1));
		
	}

}
