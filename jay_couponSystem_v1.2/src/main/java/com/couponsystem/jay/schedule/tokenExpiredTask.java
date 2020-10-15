package com.couponsystem.jay.schedule;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.couponsystem.jay.login.CustomSession;
import com.couponsystem.jay.login.TokenManager;

@Component
public class tokenExpiredTask {
	
	@Autowired
	TokenManager managerToken;
	
	
	
	@Scheduled(fixedDelay = 1000*10)
	public void removeTimeOutToken() {
			
		
	}
	
	

}
