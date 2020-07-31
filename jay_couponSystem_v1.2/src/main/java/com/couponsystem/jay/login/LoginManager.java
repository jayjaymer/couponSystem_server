package com.couponsystem.jay.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.couponsystem.jay.exceptions.NoAccessException;
import com.couponsystem.jay.exceptions.NotFoundException;
import com.couponsystem.jay.service.AdminFacadeService;
import com.couponsystem.jay.service.ClientFacadeService;
import com.couponsystem.jay.service.CompanyFacadeService;
import com.couponsystem.jay.service.CustomerFacadeService;

import lombok.Data;

@Component
@Lazy
public class LoginManager {

	@Autowired
	private AdminFacadeService adminFacadeService;
	@Autowired
	private CustomerFacadeService customerFacadeService;
	@Autowired
	private CompanyFacadeService companyFacadeService;

	private LoginManager() {

	}

	public ClientFacadeService login(String email, String password, ClientType clientType) throws NotFoundException, NoAccessException {

		switch (clientType) {
		case ADMINISTRATOR:
			if (adminFacadeService.login(email, password)) {
				return adminFacadeService;
			} else {
				return null;
			}
		case COMPANY:
			if (companyFacadeService.login(email, password)) {
				return companyFacadeService;
			} else {
				return null;
			}

		case CUSTOMER:
			if (customerFacadeService.login(email, password)) {
				return customerFacadeService;
			} else {
				return null;
			}
		default:

			return null;
		}
	}

}
