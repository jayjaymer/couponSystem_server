package com.couponsystem.jay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.couponsystem.jay.beans.Company;
import com.couponsystem.jay.beans.Coupon;
import com.couponsystem.jay.exceptions.NotFoundException;
import com.couponsystem.jay.repo.CompanyRepository;

@Service
@Scope("prototype")
public class CompanyService {
	private Company companyTest;
	
	@Autowired
	private CompanyRepository repo;
	
	//50%
	public void addCompany(Company company) {
		repo.save(company);
	}
	
	public void updateCompany(Company company, int companyID) {
		repo.saveAndFlush(company);
	}
	public void deleteCompany(int companyID) {
		repo.deleteById(companyID);
	}
	public List<Company> getAllCompanies() {
		if ( repo.findAll() != null) {
			
		}
		return repo.findAll();
	}
	public Company getOneCompanyByID(int companyID) throws NotFoundException {
		if (repo.getOne(companyID) !=null ) {
			return repo.getOne(companyID);
		}else {
			throw new NotFoundException("Company not found!");
			
		}
	}
	
	//40%
	public boolean checkIfCompany(String email, String password) {
		if (repo.findByEmailAndPassword(email,password) != null) {
			return true;
		}else {
			
			return false;
		}
	}
	
	

	
	// 10%
	// get the company coupons.
//	public List<Coupon> getCompanyCoupons(){
//		return repo.findAll();
//	}
	
	
}
