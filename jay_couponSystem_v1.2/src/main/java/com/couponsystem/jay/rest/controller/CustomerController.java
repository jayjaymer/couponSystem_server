package com.couponsystem.jay.rest.controller;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
import com.couponsystem.jay.exceptions.TokenNotExistsException;
import com.couponsystem.jay.login.ClientType;
import com.couponsystem.jay.login.LoginManager;
import com.couponsystem.jay.login.TokenManager;
import com.couponsystem.jay.service.CustomerFacadeService;

@RestController
@RequestMapping("customer")
public class CustomerController extends ClientController{

	@Autowired
	private CustomerFacadeService cust;
	@Autowired
	private LoginManager managerLogin;
	
	 
	// TODO
	@RequestMapping(value = "/login",method = RequestMethod.POST)
//	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@Override
	public ResponseEntity<?> login(@RequestParam String email,@RequestParam String password)
			throws NotFoundException, NoAccessException, LoginFailledException {
		
		HttpHeaders returnHeaders = new HttpHeaders();
		
		try {
			 String token = managerLogin.loginC(email, password, ClientType.CUSTOMER);
			returnHeaders.set("CS-Header", token);
		} catch (LoginFailledException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		return ResponseEntity.ok().headers(returnHeaders).body("You are Logged in!");
	}
	
	@RequestMapping(value = "purchase-coupon",method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void purchaseCoupon(@RequestBody Coupon coupon) throws NoAccessException, AlreadyExistsException, NotFoundException {
		cust.purchaseCoupon(coupon);
	}
	
//	@RequestMapping(value = "get-customer-coupons")
//	public ResponseEntity<?> getCustomerCoupons(@RequestHeader (name = "CS-Header",)) throws NotFoundException{
//		try {
//			managerToken.isTokenExists(token);
//			return ResponseEntity.ok(((CustomerFacadeService) managerToken.getClientFacadeService);
//		} catch (TokenNotExistsException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//		
//		
//	}
	
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


	
	

	

}
