package com.couponsystem.jay.login;

import org.springframework.beans.factory.annotation.Autowired;

import com.couponsystem.jay.service.ClientFacadeService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomSession {
	
	private ClientFacadeService clientFacadeService;
	
	private long timestamp;

}
