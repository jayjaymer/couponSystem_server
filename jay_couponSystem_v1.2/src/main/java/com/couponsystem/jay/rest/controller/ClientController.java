package com.couponsystem.jay.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.jay.exceptions.LoginFailledException;
import com.couponsystem.jay.exceptions.NoAccessException;
import com.couponsystem.jay.exceptions.NotFoundException;

@RestController
@RequestMapping("/api")
public abstract class ClientController {
	
	@Autowired
	protected AdminController admin;
	@Autowired
	protected CustomerController customer;
	@Autowired
	protected CompanyController company;
	
	public ClientController() {
		super();
	}
	
	public abstract boolean login (String email, String password) throws NotFoundException, NoAccessException , LoginFailledException;
	
	

	
	
	
}
