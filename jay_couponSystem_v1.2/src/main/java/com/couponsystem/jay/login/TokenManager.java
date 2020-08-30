package com.couponsystem.jay.login;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {
	private Map<String, CustomSession> tokens = new HashMap<>();



public String add(String facade) {
	String token = UUID.randomUUID().toString();
	CustomSession customSession = new CustomSession(facade, new Date());
	tokens.put(token, customSession);
	return token;
}

}

