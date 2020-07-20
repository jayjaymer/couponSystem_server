package com.couponsystem.jay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsystem.jay.beans.Company;
import com.couponsystem.jay.beans.Coupon;
import com.couponsystem.jay.repo.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repo;
	
	public void addCompany(Company company) {
		
	}
	
	public void updateCompany(Company company, int companyID) {
		
	}
	public void deleteCompany(int companyID) {
		
	}
	public void getAllCompanies() {
		
	}
	public void getOneCompanyByID(int companyID) {
		
	}
	public void checkIfCompany(String email, String password) {
		
	}
	public void getOneCompanyByEmailAndPassword(String email, String password) {
		
	}
	
	public List<Coupon> getCompanyCoupons(){
		return null;
	}
	
	
}
