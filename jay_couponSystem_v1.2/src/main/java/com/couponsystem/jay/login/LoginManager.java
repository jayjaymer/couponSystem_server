//package com.couponsystem.jay.login;
//
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//
//import com.couponsystem.jay.exceptions.NotFoundException;
//import com.couponsystem.jay.service.AdminFacadeService;
//import com.couponsystem.jay.service.ClientFacadeService;
//import com.couponsystem.jay.service.CompanyFacadeService;
//import com.couponsystem.jay.service.CustomerFacadeService;
//
//import lombok.Data;
//
//@Controller
//@Lazy
//public class LoginManager {
//	private static LoginManager instance = null;
//	private ClientFacadeService clientFacadeService;
//	
//	private LoginManager() {
//		
//	}
//	
//	public ClientFacadeService login(String email, String password, ClientType clientType) throws NotFoundException {
//		
//		switch (clientType) {
//		case ADMINISTRATOR:
//			clientFacadeService = (ClientFacadeService) new AdminFacadeService();
//			if (clientFacadeService.login(email, password)) {
//				return clientFacadeService;
//			}else {
//				return null;
//			}
//		case COMPANY:
//			clientFacadeService = (ClientFacadeService) new CompanyFacadeService();
//			int companyID = ((CompanyFacadeService)clientFacadeService).getCompanyID(email,password);
//			return clientFacadeService;
//		} else {
//			return null;
//		}
//			
//		case CUSTOMER:
//			clientFacadeService = (ClientFacadeService) new CustomerFacadeService();
//			if (clientFacadeService.login(email, password)) {
//				int customerID = ((CustomerFacadeService) clientFacadeService).getCustomerID(email, password);
//				((CustomerFacadeService) clientFacadeService).setCustomerID(customerID);
//				return clientFacadeService;
//			}
//		default:
//			clientFacadeService = null;
//			break;
//		}
//		return null;
//	}
//	
//}
