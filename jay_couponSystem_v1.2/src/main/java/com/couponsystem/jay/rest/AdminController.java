package com.couponsystem.jay.rest;

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
import com.couponsystem.jay.exceptions.NoAccessException;
import com.couponsystem.jay.exceptions.NotFoundException;
import com.couponsystem.jay.service.AdminFacadeService;

@RestController
@RequestMapping("admin")
//@CrossOrigin
public class AdminController implements ApplicationContextAware {
	
	
	//private Map<String, Cl>
	private ConfigurableApplicationContext ctx;
	@Autowired
	private AdminFacadeService admin;
	
	
	// Company mapping
	
	@RequestMapping(value = "/addcompany", method = RequestMethod.POST)
	public ResponseEntity<?> AddCompany(@RequestBody Company company) throws AlreadyExistsException{
		admin.createCompany(company);
		return new ResponseEntity<>(("Company "+company+ " Added!" ),HttpStatus.CREATED);
	}
	
//	@PutMapping("updateCompany")
//	public ResponseEntity<?> updateCompany(@RequestBody Company company) throws NoAccessException, NotFoundException{
//		admin.updateCompany(company);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	
//	@DeleteMapping
//	public ResponseEntity<?> deleteCompany(@RequestBody int companyID) throws NotFoundException{
//		admin.deleteCompany(companyID);
//		return new ResponseEntity<>(HttpStatus.GONE);
//	}
//	
	@RequestMapping(value = "get-all-companies", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Company> getAllCompanies(){
		return admin.getallCompanies();
	}
	
	@RequestMapping(value = "get-one-company/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneCompany(@PathVariable int companyID) throws NotFoundException{
		return new ResponseEntity<>(admin.getOneCompany(companyID),HttpStatus.OK);
	}
	
//	@GetMapping
//	public ResponseEntity<?> findOneCompany(@RequestBody int companyID) throws NotFoundException{
//		admin.findCompanyByID(companyID);
//		return new ResponseEntity<>(HttpStatus.OK);
//		
//	}
//	
//	// customer mapping
//	
//	@PostMapping
//	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws AlreadyExistsException{
//		admin.addCustomer(customer);
//		return new ResponseEntity<>(HttpStatus.CREATED);
//	}
//	
//	@PutMapping
//	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) throws NoAccessException, NotFoundException{
//		admin.updateCustomer(customer);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	
//	@DeleteMapping
//	public ResponseEntity<?> deleteCustomer(@RequestBody int customerID) throws NotFoundException{
//		admin.deleteCustomer(customerID);
//		return new ResponseEntity<>(HttpStatus.GONE);
//	}
//	
//	@GetMapping
//	public ResponseEntity<?> getallCustomer(){
//		admin.getAllCustomers();
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	
//	@GetMapping
//	public ResponseEntity<?> getOneCustomer(@RequestBody int customerID) throws NotFoundException{
//		admin.getOneCustomer(customerID);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	
//	@GetMapping
//	public ResponseEntity<?> findOneCustomer(@RequestBody int customerID) throws NotFoundException{
//		admin.findCustomerById(customerID);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		if (ctx instanceof ConfigurableApplicationContext) {
			this.ctx = (ConfigurableApplicationContext) ctx;
		}
	}
	
	
	
}
