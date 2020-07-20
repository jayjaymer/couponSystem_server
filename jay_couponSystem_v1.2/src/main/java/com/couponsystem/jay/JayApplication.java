package com.couponsystem.jay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JayApplication {

	public static void main(String[] args) {
		System.out.println("START");
		SpringApplication.run(JayApplication.class, args);
		System.out.println("END");
	}

}
