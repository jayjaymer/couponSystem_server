package com.couponsystem.jay.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.couponsystem.jay.login.TokenManager;

@Component
public class tokenExpiredTask {
	
	@Autowired
	TokenManager managerToken;
	
	

}
