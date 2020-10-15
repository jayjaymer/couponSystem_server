package com.couponsystem.jay.login;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.couponsystem.jay.exceptions.TokenNotExistsException;
import com.couponsystem.jay.service.ClientFacadeService;

import lombok.Getter;

@Component
@Getter
public class TokenManager {
	@Autowired
	private Map<String, CustomSession> tokens;

	public String addToken(ClientFacadeService clientFacadeService) {
		String token = UUID.randomUUID().toString();
		tokens.put(token, new CustomSession(clientFacadeService, System.currentTimeMillis()));
		return token;
	}

	public boolean isTokenExists(String token) throws TokenNotExistsException {
		CustomSession customSession = tokens.get(token);
		if (customSession != null) {
			return true;
		}
		throw new TokenNotExistsException("Token isnt found, please try again!");
	}
	
	public ClientFacadeService getType(String token) {
		return tokens.getOrDefault(token, null).getClientFacadeService();
	}
	
	public void deleteToken(String token) {
		tokens.remove(token);
	}
	
	public void removeExpiredToken(String token) {
		 	for(Map.Entry<String, CustomSession> enter : tokens.entrySet()) {
		 		CustomSession customSession = enter.getValue();
		 		token = enter.getKey();
					
				}
	}
	
	public void getCurrentToken(String token) {
		tokens.get(token);
	}
	
}
