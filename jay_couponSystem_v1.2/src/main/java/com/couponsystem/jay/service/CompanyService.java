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
		repo.save(company);
	}
	
	public void updateCompany(Company company, int companyID) {
		repo.saveAndFlush(company);
	}
	public void deleteCompany(int companyID) {
		repo.deleteById(companyID);
	}
	public List<Company> getAllCompanies() {
		return repo.findAll();
	}
	public Company getOneCompanyByID(int companyID) {
		return repo.getOne(companyID);
	}
	
//	public boolean checkIfCompany(String email, String password) {
//		return false;
//	}
//	public Company getOneCompanyByEmailAndPassword(String email, String password) {
//		return repo.;
//	}
	
//	public List<Coupon> getCompanyCoupons(){
//		return repo.findAll();
//	}
	
	
}
