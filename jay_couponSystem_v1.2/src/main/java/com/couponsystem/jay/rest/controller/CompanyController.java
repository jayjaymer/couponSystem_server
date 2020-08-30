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
import com.couponsystem.jay.beans.Company;
import com.couponsystem.jay.beans.Coupon;
import com.couponsystem.jay.exceptions.AlreadyExistsException;
import com.couponsystem.jay.exceptions.LoginFailledException;
import com.couponsystem.jay.exceptions.NoAccessException;
import com.couponsystem.jay.exceptions.NotFoundException;
import com.couponsystem.jay.service.CompanyFacadeService;

@RestController
@RequestMapping("company")
public class CompanyController extends ClientController {

	@Autowired
	private CompanyFacadeService comp;
	
	
	// TODO
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public boolean login(@RequestParam String email,@RequestParam String password)
			throws NotFoundException, NoAccessException, LoginFailledException {
		return comp.login(email, password);
	}
	
	@RequestMapping(value = "create-coupon",method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createCoupon(@RequestBody Coupon coupon) throws AlreadyExistsException {
		comp.createCoupon(coupon);
	}
	@RequestMapping(value = "update-coupon", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void updateCoupon(@RequestBody Coupon coupon) throws NoAccessException, NotFoundException {
		comp.updateCoupon(coupon);
	}
	
	// TODO
	@RequestMapping(value = "delete-coupon/{couponID}",method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.GONE)
	public void deleteCoupon(@PathVariable int couponID) throws NotFoundException {
		comp.deleteCoupon(couponID);
	}

	@RequestMapping(value = "get-all-coupons", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Coupon> getCompanyCoupons() throws NotFoundException{
		return comp.getCompanyCoupons();
	}
	
	@RequestMapping(value = "get-all-coupons-by-category/{category}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Coupon> getCompanyCouponsByCategory(@PathVariable Category category) throws NotFoundException{
		return comp.getCompanyCouponsByCategory(category);
	}
	
	@RequestMapping(value = "get-all-coupons-by-maxprice/{maxPrice}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Coupon> getCompanyCouponsByMaxPrice(@PathVariable double maxPrice) throws NotFoundException{
		return comp.getCompanyCouponsByMaxPrice(maxPrice);
	}
	
	@RequestMapping(value = "get-company-details",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Company getDetails() throws NotFoundException {
		return comp.getCompanyDetails();
	}
	
	
	
	
	
	
	
	
	
	
	


}
