package com.couponsystem.jay.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.jay.exceptions.LoginFailledException;
import com.couponsystem.jay.exceptions.NoAccessException;
import com.couponsystem.jay.exceptions.NotFoundException;
import com.couponsystem.jay.login.LoginManager;
import com.couponsystem.jay.login.LoginResponse;
import com.couponsystem.jay.login.TokenManager;

@RestController
@RequestMapping
public abstract class ClientController {
	
	@Autowired
	LoginManager managerLogin;
	@Autowired
	TokenManager managerToken;
	
	
	
	public abstract ResponseEntity<?> login (@RequestParam String email, @RequestParam String password) throws NotFoundException, NoAccessException , LoginFailledException;
	
	
	
	
	
	
}
