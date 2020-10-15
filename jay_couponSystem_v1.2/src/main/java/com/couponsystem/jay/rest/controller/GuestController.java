package com.couponsystem.jay.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.jay.beans.Coupon;
import com.couponsystem.jay.service.CouponService;

@RestController
@RequestMapping("guest")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class GuestController {
		
	@Autowired
	CouponService coupon;
	
	@GetMapping("getCoupons")
	public ResponseEntity<?> getAllCoupons(){
		return new ResponseEntity<List<Coupon>>(coupon.getAllCoupons(),HttpStatus.CREATED);
	}

}
