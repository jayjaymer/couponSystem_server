package com.couponsystem.jay.rest.controller;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.jay.beans.Category;
import com.couponsystem.jay.beans.Coupon;
import com.couponsystem.jay.beans.Customer;
import com.couponsystem.jay.exceptions.AlreadyExistsException;
import com.couponsystem.jay.exceptions.LoginFailledException;
import com.couponsystem.jay.exceptions.NoAccessException;
import com.couponsystem.jay.exceptions.NotFoundException;
import com.couponsystem.jay.service.CustomerFacadeService;

@RestController
@RequestMapping("customer")
public class CustomerController extends ClientController{

	private ConfigurableApplicationContext ctx;
	@Autowired
	private CustomerFacadeService cust;
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public boolean login(@RequestParam String email,@RequestParam String password)
			throws NotFoundException, NoAccessException, LoginFailledException {
		return cust.login(email, password);
	}
	
	@RequestMapping(value = "purchase-coupon",method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void purchaseCoupon(@RequestBody Coupon coupon) throws NoAccessException, AlreadyExistsException, NotFoundException {
		cust.purchaseCoupon(coupon);
	}
	
	@RequestMapping(value = "get-customer-coupons")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Coupon> getCustomerCoupons() throws NotFoundException{
		return cust.getCustomerCoupons();
	}
	
	@RequestMapping(value = "get-customer-coupons-by-category/{category}",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Coupon> getCustomerCouponsByCategory(@PathVariable Category category) throws NotFoundException{
		return cust.getCustoCouponsByCategory(category);
	}
	
	@RequestMapping(value = "get-customer-coupons-by-maxprice/{maxprice}")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Coupon> getCustomerCouponsByMaxPrice(@PathVariable double maxPrice) throws NotFoundException{
		return cust.getCutomerCouponsByMaxPrice(maxPrice);
	}
	
	@RequestMapping(value = "get-customer-details",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Customer getCustomerDetails() throws NotFoundException {
		return cust.getCustomerDetails();
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		// TODO Auto-generated method stub
//		
//	}
	

}
