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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
import com.couponsystem.jay.exceptions.TokenNotExistsException;
import com.couponsystem.jay.login.ClientType;
import com.couponsystem.jay.login.LoginResponse;
import com.couponsystem.jay.service.AdminFacadeService;
import com.couponsystem.jay.service.CompanyFacadeService;

@RestController
@RequestMapping("company")
public class CompanyController extends ClientController {

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password)
			throws NotFoundException, NoAccessException, LoginFailledException {
		try {
			String token = managerLogin.loginC(email, password, ClientType.COMPANY);
			LoginResponse responseLogin = new LoginResponse();
			responseLogin.setToken(token);
			System.out.println(token);
			return new ResponseEntity<LoginResponse>(responseLogin,HttpStatus.CREATED);
		} catch (LoginFailledException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("createCoupon")
	public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "Token", required = false) String token) throws AlreadyExistsException {
		try {
			managerToken.isTokenExists(token);
			((CompanyFacadeService) managerToken.getType(token)).createCoupon(coupon);
			return new ResponseEntity<>("Coupon added", HttpStatus.CREATED);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (AlreadyExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("updateCoupon")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "Token", required = false) String token) throws NoAccessException, NotFoundException {
		try {
			managerToken.isTokenExists(token);
			((CompanyFacadeService) managerToken.getType(token)).updateCoupon(coupon);
			return new ResponseEntity<>("Coupon was updated!", HttpStatus.CREATED);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NoAccessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("deleteCoupon/{couponID}")
	public ResponseEntity<?> deleteCoupon(@PathVariable int couponID,
			@RequestHeader(name = "Token", required = false) String token) throws NotFoundException {
		try {
			managerToken.isTokenExists(token);
			((CompanyFacadeService) managerToken.getType(token)).deleteCoupon(couponID);
			return new ResponseEntity<>("Company was Deleted", HttpStatus.ACCEPTED);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("getCoupons")
	public ResponseEntity<?> getCompanyCoupons(@RequestHeader(name = "Token", required = false) String token)
			throws NotFoundException {
		try {
			managerToken.isTokenExists(token);
			return new ResponseEntity<List<Coupon>>(
					((CompanyFacadeService) managerToken.getType(token)).getCompanyCoupons(), HttpStatus.OK);

		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("getCouponsByCategory/{category}")
	public ResponseEntity<?> getCompanyCouponsByCategory(@PathVariable Category category,
			@RequestHeader(name = "Token", required = false) String token) throws NotFoundException {
		try {
			managerToken.isTokenExists(token);
			return new ResponseEntity<List<Coupon>>(
					((CompanyFacadeService) managerToken.getType(token)).getCompanyCouponsByCategory(category),
					HttpStatus.OK);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("getCouponsByMaxPrice/{maxPrice}")
	public ResponseEntity<?> getCompanyCouponsByMaxPrice(@PathVariable double maxPrice,
			@RequestHeader(name = "Token", required = false) String token) throws NotFoundException {
		try {
			managerToken.isTokenExists(token);
			return new ResponseEntity<List<Coupon>>(
					((CompanyFacadeService) managerToken.getType(token)).getCompanyCouponsByMaxPrice(maxPrice),
					HttpStatus.OK);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("getCompanyDetails")
	public ResponseEntity<?> getDetails(@RequestHeader(name = "Token", required = false) String token)
			throws NotFoundException {
		try {
			managerToken.isTokenExists(token);
			return new ResponseEntity<Company>(((CompanyFacadeService) managerToken.getType(token)).getCompanyDetails(),
					HttpStatus.OK);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	
	
	@GetMapping("getOneCoupon/{couponID}")
	public ResponseEntity<?> getOneCoupon(@PathVariable int couponID,@RequestHeader(name = "Token", required = false) String token){
		try {
			managerToken.isTokenExists(token);
			
			return new ResponseEntity<Coupon>(((CompanyFacadeService)managerToken.getType(token)).getOneCoupon(couponID), HttpStatus.OK);
		}  catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
//	@PutMapping("updateCompany")
//	public ResponseEntity<?> updateCompany(@RequestBody Company company,@RequestHeader(name = "Token", required = false) String token) throws TokenNotExistsException{
//		try {
//			managerToken.isTokenExists(token);
//			((CompanyFacadeService) managerToken.getType(token)).updateCompany(company);
//			return new ResponseEntity<>("Coupon was updated!", HttpStatus.CREATED);
//		} catch (TokenNotExistsException | NotFoundException e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
//			}
//		
//	}

}
