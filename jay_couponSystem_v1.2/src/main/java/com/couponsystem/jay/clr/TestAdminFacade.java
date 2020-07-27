package com.couponsystem.jay.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.couponsystem.jay.beans.Company;
import com.couponsystem.jay.login.ClientType;
import com.couponsystem.jay.login.LoginManager;
import com.couponsystem.jay.service.AdminFacadeService;
import com.couponsystem.jay.util.Print;
@Component
@Order(value = 4)
public class TestAdminFacade implements CommandLineRunner {
	
	

	@Override
	public void run(String... args) throws Exception {
		System.out.println();
		System.out.println();
		System.out.println();
		
		Print print = new Print();
		System.out.println();
		print.facadeTest(null);
		System.out.println();
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~Admin Facade TEST~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
//		AdminFacadeService admin = new AdminFacadeService();
//		
//		// Login test
//		System.out.println("*******admin facade - login as admin*******");
//		
//		
//		System.out.println();
//		// add existing company name
//				System.out.println();
//				System.out.println("*******admin facade - creating an existing company name or email*******");
//				Company testCompany = new Company();
//				testCompany.setName("tesla");
//				testCompany.setEmail("Tesla@gmail.com");
//				testCompany.setPassword("1234");
//				admin.createCompany(testCompany);
//				System.out.println();
//				System.out.println("*******admin facade - get all companies*******");
//				System.out.println(admin.getallCompanies());
		
		
		// add existing company name
	//	System.out.println("*******admin facade - creating an existing company name or email*******");

	}

}
