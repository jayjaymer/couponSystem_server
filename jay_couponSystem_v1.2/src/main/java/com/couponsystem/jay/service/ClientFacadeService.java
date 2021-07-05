package com.couponsystem.jay.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.couponsystem.jay.exceptions.LoginFailledException;
import com.couponsystem.jay.exceptions.NotFoundException;
import lombok.Getter;

@Service
@Getter
public abstract class ClientFacadeService {
	
	@Autowired
	protected CouponService couponService;
	@Autowired
	protected CompanyService companyService;
	@Autowired
	protected CustomerService customerService;
	
	
	public abstract boolean login (String email, String password) throws LoginFailledException, NotFoundException;
	
	

}
