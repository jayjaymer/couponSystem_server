package com.couponsystem.jay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsystem.jay.beans.Company;
import com.couponsystem.jay.beans.Coupon;
import com.couponsystem.jay.repo.CouponRepository;

@Service
public class CouponService {
	@Autowired
	private CouponRepository repo;

	public void addCoupon(Coupon coupon) {
		repo.save(coupon);
	}

	public void updateCoupon(Coupon coupon) {
		repo.saveAndFlush(coupon);
	}

	public void deleteCoupon(int couponID) {
		repo.deleteById(couponID);
	}

	public List<Coupon> getAllCoupons(){
		return repo.findAll();
	}

	public Coupon getOneCouponByID(int couponID) {
		return repo.getOne(couponID);
	}

//	public boolean checkIfCoupon(int couponID) {
//		
//		return false;
//	}
//
//	public List<Coupon> getCouponsByCompanyID(int companyID) {
//		return null;
//	}
	
//	public void addPurchaseCoupon(int customerID, int couponID) {
//		
//	}
//	
//	public void deletePurchaseCoupon(int customerID, int couponID) {
//		
//	}
//	
	// check how to get all purchased coupons

//	public List<Coupon> getAllPurchasedCoupons() {
//		return null;
//	}

}
