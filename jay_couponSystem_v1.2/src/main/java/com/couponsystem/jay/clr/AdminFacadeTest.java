package com.couponsystem.jay.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.couponsystem.jay.beans.Company;
import com.couponsystem.jay.exceptions.AlreadyExistsException;
import com.couponsystem.jay.exceptions.NoAccessException;
import com.couponsystem.jay.login.ClientType;
import com.couponsystem.jay.login.LoginManager;
import com.couponsystem.jay.service.AdminFacadeService;
import com.couponsystem.jay.util.Print;

@Component
@Order(value = 4)
public class AdminFacadeTest implements CommandLineRunner {

	@Autowired
	AdminFacadeService admin = new AdminFacadeService();

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
		System.out.println("*******admin facade - creating an existing company name or email*******");
		System.out.println("~~~trying to use existing name~~~");
		Company c1 = Company.builder().
				name("Cola").
				email("cola@gmail.com").
				password("1234").
				build();

		try {
			admin.createCompany(c1);
		} catch (AlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("~~~trying to use existing email~~~");
		Company c2 = Company.builder().
				name("Colaaaa").
				email("cola@gmail.com").
				password("1234").
				build();

		try {
			admin.createCompany(c2);
		} catch (AlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
		
		
		// Cannot Update Company name and id
		System.out.println("*******admin facade - try update company`s name and id*******");
		System.out.println("~~~trying to change id, name and email~~~");
		Company company = admin.getOneCompany(1);
		System.out.println("Company 1 before attempt");
		System.out.println(admin.getOneCompany(1));

		try {
			company.setId(99);
			admin.updateCompany(company, 1);
		} catch (NoAccessException e) {
			System.out.println("Cannot change company ID!");
		}

		try {
			company.setName("sadgads");
			admin.updateCompany(company, 1);
		} catch (NoAccessException e) {
			System.out.println("Cannot change company name!");
		}
		System.out.println();
//		System.out.println("~~~trying to change email and password~~~");
//		try {
//			company.setEmail("dasgasdgadsggasddasga");
//			company.setPassword("asdhdsahadshadshdsahsdh");
//			admin.updateCompany(company, 1);
//		} catch (NoAccessException e) {
//		}
//
//		System.out.println("Company 1 after attempt");
//		System.out.println(admin.getOneCompany(1));
		
		
//		System.out.println("Retrun info to original.");
//		try {
//			company.setEmail("cola@gmail.com");
//			company.setPassword("1234");
//			admin.updateCompany(company, 1);
//		} catch (Exception e) {
//		}
//		System.out.println(admin.getOneCompany(1));
//		System.out.println();
		
		// Delete Coupons Purchase history + delete available coupons from this company
		System.out.println("*******admin facade - remove FK and purchased coupons.*******");
		System.out.println("Removing A company.");
		admin.deleteCompany(8);
		System.out.println("Company 8 Deleted");
		System.out.println();
		
		// Return all companies
		System.out.println("*******admin facade - get all companies*******");
		System.out.println(admin.getallCompanies());
		System.out.println();
		
		// Return one Company
		System.out.println("*******admin facade - get one company*******");
		System.out.println(admin.getOneCompany(1));
		System.out.println();
		
		
		
	}

}
