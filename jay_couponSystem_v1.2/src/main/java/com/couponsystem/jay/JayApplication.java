package com.couponsystem.jay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
//@EnableScheduling // <-- Enables the Daily Job
public class JayApplication {

	public static void main(String[] args) {
		System.out.println("START");

		ConfigurableApplicationContext ctx = SpringApplication.run(JayApplication.class, args); // <-- Start Application 

		System.out.println("Coupon System application is up and running! ");
		System.out.println();

	//	ctx.close(); // <-- Closing the Application
	//	System.out.println();
	//	System.out.println("Coupon System application Shutdown Complete! ");

	}
	

}
