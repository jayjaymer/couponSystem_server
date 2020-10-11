package com.couponsystem.jay.clr;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.couponsystem.jay.beans.Coupon;
import com.couponsystem.jay.login.TokenManager;
@Component
@Order(value = 4)

public class TokenTest implements CommandLineRunner {

	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public void run(String... args) throws Exception {
//		String tokenTeString = restTemplate.postForObject("http://localhost:2000/admin/login?email=admin@admin.com&password=admin" , null, String.class);
//		System.out.println(tokenTeString);
//		System.out.println(restTemplate.postForEntity("http://localhost:2000/admin/login?email=admin@admin.com&password=admin",null, String.class));
		
//		List<Coupon> coupons = restTemplate.getForObject("http://localhostL8080/admin/get-all-coupons/"+tokenTeString, POGO.class);
		
	}

}
