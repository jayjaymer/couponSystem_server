package com.couponsystem.jay.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.couponsystem.jay.beans.Company;
import com.couponsystem.jay.beans.Coupon;
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Company findByEmailAndPassword(String email, String password);
	//List<Coupon> getCompanyCoupons();
	Company findById(int companyID);
	
	
}
