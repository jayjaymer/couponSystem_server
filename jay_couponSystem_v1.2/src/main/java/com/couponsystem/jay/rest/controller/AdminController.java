package com.couponsystem.jay.rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.jay.beans.Company;
import com.couponsystem.jay.beans.Customer;
import com.couponsystem.jay.exceptions.AlreadyExistsException;
import com.couponsystem.jay.exceptions.LoginFailledException;
import com.couponsystem.jay.exceptions.NoAccessException;
import com.couponsystem.jay.exceptions.NotFoundException;
import com.couponsystem.jay.service.AdminFacadeService;

@RestController
@RequestMapping("admin")
//@CrossOrigin
public class AdminController extends ClientController {
	// implements ApplicationContextAware
	// private Map<String, Cl>
	private ConfigurableApplicationContext ctx;
	@Autowired
	private AdminFacadeService admin;
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public boolean login(@RequestParam String email,@RequestParam String password)
			throws NotFoundException, NoAccessException, LoginFailledException {
		return admin.login(email, password);
	}
	
	// Company mapping
	// TODO
	@RequestMapping(value = "add-company", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void AddCompany(@RequestBody Company company) throws AlreadyExistsException {
		admin.createCompany(company);
	}

	@RequestMapping(value = "update-company", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void updateCompany(@RequestBody Company company) throws NoAccessException, NotFoundException {
		admin.updateCompany(company);
	}

	@RequestMapping(value = "delete-company/{companyID}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.GONE)
	public void deleteCompany(@PathVariable int companyID) throws NotFoundException {
		admin.deleteCompany(companyID);
	}

	@RequestMapping(value = "get-all-companies", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Company> getAllCompanies() {
		return admin.getallCompanies();
	}

	@RequestMapping(value = "get-one-company/{companyID}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Company getOneCompany(@PathVariable int companyID) throws NotFoundException {
		return admin.getOneCompany(companyID);
	}

	@RequestMapping(value = "find-one-company/{companyID}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Company findOneCompany(@PathVariable int companyID) throws NotFoundException {
		return admin.findCompanyByID(companyID);

	}

	// customer mapping
	@RequestMapping(value = "add-customer", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void addCustomer(@RequestBody Customer customer) throws AlreadyExistsException {
		admin.addCustomer(customer);
	}

	@RequestMapping(value = "update-customer", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void updateCustomer(@RequestBody Customer customer) throws NoAccessException, NotFoundException {
		admin.updateCustomer(customer);
	}

	@RequestMapping(value = "delete-customer/{customerID}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.GONE)
	public void deleteCustomer(@PathVariable int customerID) throws NotFoundException {
		admin.deleteCustomer(customerID);
	}

	@RequestMapping(value = "get-all-customers", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Customer> getallCustomer() {
		return admin.getAllCustomers();
	}

	@RequestMapping(value = "get-one-customer/{customerID}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Customer getOneCustomer(@PathVariable int customerID) throws NotFoundException {
		return admin.getOneCustomer(customerID);
	}

	@RequestMapping(value = "find-one-customer/{customerID}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Customer findOneCustomer(@PathVariable int customerID) throws NotFoundException {
		return admin.findCustomerById(customerID);
	}
	
	
	
	
	
	
	

//	@Override
//	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
//		if (ctx instanceof ConfigurableApplicationContext) {
//			this.ctx = (ConfigurableApplicationContext) ctx;
//		}
//	}



}
