package com.couponsystem.jay.clr;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.couponsystem.jay.beans.Category;
import com.couponsystem.jay.beans.Coupon;
import com.couponsystem.jay.service.CouponService;
@Component
@Order(value = 2)
public class TestCoupon implements CommandLineRunner {
	@Autowired
	CouponService couponService;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~COUPON DUMMY TEST~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		Coupon coupon1 = new Coupon();
		coupon1.setCompanyID(1);
		coupon1.setCategory(Category.CARS);
		coupon1.setTitle("TestTitle");
		coupon1.setDescription("eatyaesy");
		coupon1.setStartDate(new Date(2020,01,01));
		coupon1.setEndDate(new Date(2020,10,10));
		coupon1.setAmount(10);
		coupon1.setPrice(10);
		coupon1.setImage("http://test");
		
		
		Coupon coupon2 = new Coupon();
		coupon2.setCompanyID(2);
		coupon2.setCategory(Category.CARS);
		coupon2.setTitle("TestTitle22222");
		coupon2.setDescription("eatyaesy222222222");
		coupon2.setStartDate(new Date(2020,01,01));
		coupon2.setEndDate(new Date(2020,10,20));
		coupon2.setAmount(20);
		coupon2.setPrice(20);
		coupon2.setImage("http://test22222222222");
		
		
		Coupon coupon3 = new Coupon();
		coupon3.setCompanyID(2);
		coupon3.setCategory(Category.CARS);
		coupon3.setTitle("TestTitle33333333333333333");
		coupon3.setDescription("eatyaesy33333333333333333");
		coupon3.setStartDate(new Date(2020,01,01));
		coupon3.setEndDate(new Date(2020,10,30));
		coupon3.setAmount(30);
		coupon3.setPrice(30);
		coupon3.setImage("http://test333333333333");
		
		
		couponService.addCoupon(coupon1);
		couponService.addCoupon(coupon2);
		couponService.addCoupon(coupon3);
		System.out.println("coupons 1,2,3 are added");
		
		System.out.println("update coupon");
		System.out.println("coupon2 title before "+coupon2.getTitle());
		coupon2.setTitle("emaiLLLLLLLLLLLLLLLLLLLLLLLL");
		couponService.updateCoupon(coupon2);
		System.out.println("coupon title after "+coupon2.getTitle());
		
		System.out.println("delete coupon");
		couponService.deleteCoupon(1);
		System.out.println("coupon one deleted");
		
		System.out.println("get all coupons");
		System.out.println(couponService.getAllCoupons());
		
		System.out.println("get one coupon by ID");
		System.out.println(couponService.getOneCouponByID(2));
		
		System.out.println("check if coupon exists");
		System.out.println(couponService.checkIfCoupon(3));
		
		System.out.println("get one coupon info by coupon ID");
		System.out.println(couponService.getOneCouponByID(3));
		
		System.out.println("test adding purchased coupon");
		couponService.addPurchaseCoupon(2, 2);
		couponService.addPurchaseCoupon(1, 3);
		System.out.println("coupons purchased");
		
		System.out.println("delete purchased coupons");
	//	couponService.deletePurchaseCoupon(1, 3);
		System.out.println("coupon 2 deleted");
		
		System.out.println("get all purchased");
		System.out.println(couponService.getAllPurchasedCoupons());
		
	}

}
