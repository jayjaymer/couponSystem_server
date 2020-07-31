package com.couponsystem.jay.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.couponsystem.jay.service.CustomerFacadeService;

//@Component
//@Order(value = 6)
public class CustomerFacadeTEST implements CommandLineRunner {
	@Autowired
	CustomerFacadeService customerFacadeService;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		
		
	}

}
