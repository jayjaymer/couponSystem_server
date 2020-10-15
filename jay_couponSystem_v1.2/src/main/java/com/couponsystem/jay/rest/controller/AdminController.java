
package com.couponsystem.jay.rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.couponsystem.jay.service.AdminFacadeService;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AdminController extends ClientController {

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password)
			throws NotFoundException, NoAccessException, LoginFailledException {
		try {
			String token = managerLogin.loginC(email, password, ClientType.ADMINISTRATOR);
			LoginResponse responseLogin = new LoginResponse();
			responseLogin.setToken(token);
			System.out.println(token);
			return new ResponseEntity<LoginResponse>(responseLogin,HttpStatus.CREATED);
		} catch (LoginFailledException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@DeleteMapping("logout")
	public ResponseEntity<?> logOut(String token) {
		managerToken.getCurrentToken(token);
			managerToken.deleteToken(token);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

//	@PostMapping("login")
//	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password){
//		admin.login(email, password);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

//	@PostMapping("addCompany")
//	public ResponseEntity<?> addCompany(@RequestBody Company company) throws AlreadyExistsException {
//		admin.createCompany(company);
//		return new ResponseEntity<>(HttpStatus.CREATED);
//	}

	// Company mapping
	@PostMapping("addCompany")
	public ResponseEntity<?> AddCompany(@RequestBody Company company,
			@RequestHeader(name = "Token", required = false) String token) throws AlreadyExistsException {
		try {
			managerToken.isTokenExists(token);
			((AdminFacadeService) managerToken.getType(token)).createCompany(company);
			return new ResponseEntity<>("Company added", HttpStatus.CREATED);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (AlreadyExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("updateCompany")
	public ResponseEntity<?> updateCompany(@RequestBody Company company,
			@RequestHeader(name = "Token", required = false) String token) throws NoAccessException, NotFoundException {
		try {
			managerToken.isTokenExists(token);
			((AdminFacadeService) managerToken.getType(token)).updateCompany(company);
			return new ResponseEntity<>("Company was updated!", HttpStatus.CREATED);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);

		} catch (NoAccessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
//	@PutMapping("updateCompany")
//	public ResponseEntity<?> updateCompany(@RequestBody Company company) throws NoAccessException, NotFoundException {
//		admin.updateCompany(company);
//		return new ResponseEntity<>(HttpStatus.ACCEPTED);
//	}

//	@DeleteMapping("deleteCompany/{companyID}")
//	public ResponseEntity<?> deleteCompany(@PathVariable int companyID) throws NotFoundException {
//		admin.deleteCompany(companyID);
//		return new ResponseEntity<>(HttpStatus.ACCEPTED);
//
//	}

	@DeleteMapping("deleteCompany/{companyID}")
	public ResponseEntity<?> deleteCompany(@PathVariable int companyID,
			@RequestHeader(name = "Token", required = false) String token) throws NotFoundException {
		try {
			managerToken.isTokenExists(token);
			((AdminFacadeService) managerToken.getType(token)).deleteCompany(companyID);
			return new ResponseEntity<>("Company was Deleted", HttpStatus.ACCEPTED);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("getAllCompanies")
	public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Token", required = false) String token) {
		try {
			managerToken.isTokenExists(token);
			return new ResponseEntity<List<Company>>(
					((AdminFacadeService) managerToken.getType(token)).getallCompanies(), HttpStatus.OK);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

//	@GetMapping("getAllCompanies")
//	public ResponseEntity<?> getAllCompanies() {
//
//		return new ResponseEntity<>(admin.getallCompanies(), HttpStatus.OK);
//	}

	@GetMapping("getOneCompany/{companyID}")
	public ResponseEntity<?> getOneCompany(@PathVariable int companyID,
			@RequestHeader(name = "Token", required = false) String token) throws NotFoundException {
		try {
			managerToken.isTokenExists(token);
			return new ResponseEntity<Company>(
					((AdminFacadeService) managerToken.getType(token)).getOneCompany(companyID), HttpStatus.OK);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

//	@GetMapping("getOneCompany/{companyID}")
//	public ResponseEntity<?> getOneCompany(@PathVariable int companyID) throws NotFoundException {
//
//		return new ResponseEntity<>(admin.getOneCompany(companyID), HttpStatus.OK);
//
//	}

	// Customer mapping

	@PostMapping("addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer,
			@RequestHeader(name = "Token", required = false) String token) throws AlreadyExistsException {
		try {
			managerToken.isTokenExists(token); 
			((AdminFacadeService) managerToken.getType(token)).addCustomer(customer);
			return new ResponseEntity<>("Customer was added!", HttpStatus.CREATED);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (AlreadyExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

//	@PostMapping("addCustomer")
//	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws AlreadyExistsException {
//		admin.addCustomer(customer);
//		return new ResponseEntity<>(HttpStatus.CREATED);
//	}

	@PutMapping("updateCustomer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer,
			@RequestHeader(name = "Token", required = false) String token) throws NoAccessException, NotFoundException {
		try {
			managerToken.isTokenExists(token);
			((AdminFacadeService) managerToken.getType(token)).updateCustomer(customer);
			return new ResponseEntity<>("Customer was updated!", HttpStatus.CREATED);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (NoAccessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

//	@PutMapping("updateCustomer")
//	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer)
//			throws NoAccessException, NotFoundException {
//		admin.updateCustomer(customer);
//		return new ResponseEntity<>("Customer was updated!", HttpStatus.CREATED);
//	}

	@DeleteMapping("deleteCustomer/{customerID}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerID,
			@RequestHeader(name = "Token", required = false) String token) throws NotFoundException {
		try {
			managerToken.isTokenExists(token);
			((AdminFacadeService) managerToken.getType(token)).deleteCustomer(customerID);
			return new ResponseEntity<>("Customer was Deleted!", HttpStatus.ACCEPTED);
		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

//	@DeleteMapping("deleteCustomer/{customerID}")
//	public ResponseEntity<?> deleteCustomer(@PathVariable int customerID) throws NotFoundException {
//		admin.deleteCustomer(customerID);
//		return new ResponseEntity<>(HttpStatus.ACCEPTED);
//
//	}

	@GetMapping("getAllCustomers")
	public ResponseEntity<?> getallCustomer(@RequestHeader(name = "Token", required = false) String token) {
		try {
			managerToken.isTokenExists(token);
			return new ResponseEntity<List<Customer>>(
					((AdminFacadeService) managerToken.getType(token)).getAllCustomers(), HttpStatus.OK);

		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
//	@GetMapping("getAllCustomers")
//	public ResponseEntity<?> getallCustomer() {
//
//		return new ResponseEntity<>(admin.getAllCustomers(), HttpStatus.OK);
//	}

	@GetMapping("getOneCustomer/{customerID}")
	public ResponseEntity<?> getOneCustomer(@PathVariable int customerID,
			@RequestHeader(name = "Token", required = false) String token) throws NotFoundException {
		try {
			managerToken.isTokenExists(token);
			return new ResponseEntity<Customer>(
					((AdminFacadeService) managerToken.getType(token)).getOneCustomer(customerID), HttpStatus.OK);

		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

//	@GetMapping("getOneCustomer/{customerID}")
//	public ResponseEntity<?> getOneCustomer(@PathVariable int customerID) throws NotFoundException {
//
//		return new ResponseEntity<>(admin.getOneCustomer(customerID), HttpStatus.OK);
//
//	}
	
	// Coupons
	
	@GetMapping("getAllCoupons")
	public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Token", required = false) String token) {
		try {
			managerToken.isTokenExists(token);
			return new ResponseEntity<List<Coupon>>(
					((AdminFacadeService) managerToken.getType(token)).getAllCoupons(), HttpStatus.OK);

		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("getCouponsByCompanyID/{companyID}")
	public ResponseEntity<?> getCouponsByCompanyID(@PathVariable int companyID,@RequestHeader(name = "Token", required = false) String token) throws NotFoundException {
		try {
			managerToken.isTokenExists(token);
			return new ResponseEntity<List<Coupon>>(
					((AdminFacadeService) managerToken.getType(token)).getAllCouponsByCompanyID(companyID) , HttpStatus.OK);

		} catch (TokenNotExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	

}
