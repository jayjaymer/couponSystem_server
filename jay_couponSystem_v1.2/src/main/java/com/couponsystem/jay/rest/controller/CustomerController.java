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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.couponsystem.jay.beans.Customer;
import com.couponsystem.jay.exceptions.AlreadyExistsException;
import com.couponsystem.jay.exceptions.LoginFailledException;
import com.couponsystem.jay.exceptions.NoAccessException;
import com.couponsystem.jay.exceptions.NotFoundException;
import com.couponsystem.jay.exceptions.TokenNotExistsException;
import com.couponsystem.jay.login.ClientType;
import com.couponsystem.jay.login.LoginManager;
import com.couponsystem.jay.login.LoginResponse;
import com.couponsystem.jay.login.TokenManager;
import com.couponsystem.jay.service.CompanyFacadeService;
import com.couponsystem.jay.service.CustomerFacadeService;

@RestController
@RequestMapping("customer")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CustomerController extends ClientController {

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password)
			throws NotFoundException, NoAccessException, LoginFailledException {

		try {
			String token = managerLogin.loginC(email, password, ClientType.CUSTOMER);
			LoginResponse responseLogin = new LoginResponse();
			responseLogin.setToken(token);
			System.out.println(token);
			return new ResponseEntity<LoginResponse>(responseLogin,HttpStatus.CREATED);
		} catch (LoginFailledException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("purchaseCoupon")
	public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "Token", required = false) String token)
			throws NoAccessException, AlreadyExistsException, NotFoundException {
		try {
			managerToken.isTokenExists(token);
			((CustomerFacadeService) managerToken.getType(token)).purchaseCoupon(coupon);
			return new ResponseEntity<>("Coupon purchased!", HttpStatus.CREATED);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NoAccessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (AlreadyExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("getCustomerCoupons")
	public ResponseEntity<?> getCustomerCoupons(@RequestHeader(name = "Token", required = false) String token)
			throws NotFoundException {
		try {
			managerToken.isTokenExists(token);
			return new ResponseEntity<List<Coupon>>(
					((CustomerFacadeService) managerToken.getType(token)).getCustomerCoupons(), HttpStatus.OK);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("getCustomerCouponsByCategory/{category}")
	public ResponseEntity<?> getCustomerCouponsByCategory(@PathVariable Category category,
			@RequestHeader(name = "Token", required = false) String token) throws NotFoundException {
		try {
			managerToken.isTokenExists(token);
			return new ResponseEntity<List<Coupon>>(
					((CustomerFacadeService) managerToken.getType(token)).getCustoCouponsByCategory(category),
					HttpStatus.OK);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("getCustomerCouponsByMaxPrice/{maxPrice}")
	public ResponseEntity<?> getCustomerCouponsByMaxPrice(@PathVariable double maxPrice,
			@RequestHeader(name = "Token", required = false) String token) throws NotFoundException {
		try {
			managerToken.isTokenExists(token);
			return new ResponseEntity<List<Coupon>>(
					((CustomerFacadeService) managerToken.getType(token)).getCutomerCouponsByMaxPrice(maxPrice),
					HttpStatus.OK);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("getCustomerDetails")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "Token", required = false) String token)
			throws NotFoundException {
		try {
			managerToken.isTokenExists(token);
			return new ResponseEntity<Customer>(
					((CustomerFacadeService) managerToken.getType(token)).getCustomerDetails(), HttpStatus.OK);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	

}
