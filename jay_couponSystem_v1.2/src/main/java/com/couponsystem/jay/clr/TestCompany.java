package com.couponsystem.jay.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.couponsystem.jay.beans.Company;
import com.couponsystem.jay.service.CompanyService;

@Component
@Order (value = 1)
public class TestCompany implements CommandLineRunner {
	@Autowired
	CompanyService companyService;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~COMPANY DUMMY TEST~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(); 
		
		Company c1 = new Company();
		c1.setName("test");
		c1.setEmail("test");
		c1.setPassword("123");
		
		Company c2 = new Company();
		c2.setName("test2");
		c2.setEmail("test2");
		c2.setPassword("1234");
		
		Company c3 = new Company();
		c3.setName("test3");
		c3.setEmail("test3");
		c3.setPassword("123433");
		
		System.out.println("Adding compnies");
		companyService.addCompany(c1);
		companyService.addCompany(c2);
		companyService.addCompany(c3);
		System.out.println("compnies c1,c2,c3 are added.");
		
		System.out.println("update company");
		System.out.println("company name before "+c2.getName());
		c2.setName("afterChange");
		companyService.updateCompany(c2, 2);
		System.out.println("company name after "+c2.getName());
		System.out.println(companyService.getOneCompanyByID(2));
		
		System.out.println("Delete Company");
		companyService.deleteCompany(1);
		System.out.println("company 1 deleted");
		
		System.out.println("Get all companyss");
		System.out.println(companyService.getAllCompanies());
		
		System.out.println("Get one compnay by ID");
		System.out.println(companyService.getOneCompanyByID(2));
		
		
		
	}

}
