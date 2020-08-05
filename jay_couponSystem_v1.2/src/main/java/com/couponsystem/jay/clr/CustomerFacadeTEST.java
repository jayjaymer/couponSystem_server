package com.couponsystem.jay.clr;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.couponsystem.jay.beans.Category;
import com.couponsystem.jay.beans.Coupon;
import com.couponsystem.jay.beans.Customer;
import com.couponsystem.jay.exceptions.AlreadyExistsException;
import com.couponsystem.jay.exceptions.LoginFailledException;
import com.couponsystem.jay.exceptions.NoAccessException;
import com.couponsystem.jay.exceptions.NotFoundException;
import com.couponsystem.jay.login.ClientType;
import com.couponsystem.jay.login.LoginManager;
import com.couponsystem.jay.service.AdminFacadeService;
import com.couponsystem.jay.service.CouponService;
import com.couponsystem.jay.service.CustomerFacadeService;
import com.couponsystem.jay.util.DateUtil;
import com.couponsystem.jay.util.Print;

@Component
@Order(value = 6)
public class CustomerFacadeTEST implements CommandLineRunner {
	@Autowired
	AdminFacadeService admin;
	@Autowired
	private CustomerFacadeService customerFacadeService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private LoginManager managerLogin;
	private int customerID;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println();
		System.out.println();
		System.out.println();
		Print print = new Print();
		
//		Coupon coupon1 = Coupon.builder()
//				.companyID(1)
//				.category(Category.RESTAURANTS)
//				.title("asdgo")
//				.description("dasg")
//				.startDate(DateUtil.changeDateType(new Date(2020,1,1)))
//				.endDate(DateUtil.changeDateType(new Date(2020, 9, 10)))
//				.amount(10)
//				.price(4.99)
//				.image("asas")
//				.build();
//		
//		Coupon coupon2 = Coupon.builder()
//				.companyID(1)
//				.category(Category.RESTAURANTS)
//				.title("assa")
//				.description("dsdsd")
//				.startDate(DateUtil.changeDateType(new Date(2020,1,1)))
//				.endDate(DateUtil.changeDateType(new Date(2020, 9, 10)))
//				.amount(10)
//				.price(4.99)
//				.image("rerewq")
//				.build();
//		List<Coupon> coupons = Arrays.asList(coupon1, coupon2);
		Customer Eugene = Customer.builder()
				.firstName("Eugene")
				.lastName("Merc")
				.email("eugene@gmail.com")
				.password("123")
				.coupons(null)
				.build();
		admin.addCustomer(Eugene);
		
		
		
		
		// Customer facade TEST
				CustomerFacadeService eugeneCustomer = null;
				System.out.println(
						"**********************************************************************Customer Facade test**********************************************************************");
				System.out.println();
				try {
					System.out.println("*******customer facade - login test*******");
					System.out.println();
					System.out.println("*fake info test*");
					eugeneCustomer = (CustomerFacadeService) managerLogin.login("adsgasd", "12223", ClientType.CUSTOMER);
					System.out.println();
				} catch (LoginFailledException e) {
					System.out.println(e.getMessage());
				}
				System.out.println();

				try {
					System.out.println("*real info test*");
					eugeneCustomer = (CustomerFacadeService) managerLogin.login("eugene@gmail.com", "123",
							ClientType.CUSTOMER);
					//eugeneCustomer.setCustomerID(1);
				} catch (LoginFailledException e) {
					System.out.println(e.getMessage());
				}
				System.out.println();
				System.out.println("######### EUGENE INFO #########");
				System.out.println(eugeneCustomer.getCustomerService().findCustomerByID(Eugene.getId()));
				eugeneCustomer.setCustomerID(11);
				//System.out.println(customerFacadeService.getCustomerService().getAllPurchasedCoupons());
				
				/***
				 * 
				 * customer cannot purchase coupon if ( more than 1 time, if coupon amount is 0,
				 * if enddate passed,) THAN ~lower amount of coupon after purchase by 1~
				 * 
				 * 
				 */

				System.out.println("*******customer facade - purchase test*******");
				System.out.println();
				System.out.println("~~~~~~~~~~~coupon already purchased TEST~~~~~~~~~~~");
				// trying to purchase the same coupon twice!!!
				// ADDING FIRST TIME!
				Coupon purchasedCoupon1 = couponService.getOneCouponByID(10);
				try {
					eugeneCustomer.purchaseCoupon(purchasedCoupon1);
				} catch (AlreadyExistsException e) {
					System.out.println(e.getMessage());
				}
				System.out.println();
				
//				// ADDING SECOND TIME TO SHOW ERROR
				System.out.println("###### Trying to purchase same coupon second time!! ######");
				try {
					eugeneCustomer.purchaseCoupon(purchasedCoupon1);
				} catch (AlreadyExistsException e) {
					System.out.println(e.getMessage());
				}

				System.out.println();

				System.out.println("~~~~~~~~~~~coupon is out of stock~~~~~~~~~~~");
				Coupon purchasedCoupon2 = couponService.getOneCouponByID(7);
				// ADDING AN EMPTY COUPON
				try {
					eugeneCustomer.purchaseCoupon(purchasedCoupon2);
				} catch (NoAccessException e) {
					System.out.println(e.getMessage());
				}

				System.out.println("~~~~~~~~~~~coupon is outdated~~~~~~~~~~~");
				Coupon purchasedCoupon3 = couponService.getOneCouponByID(8);
				System.out.println(purchasedCoupon3.getEndDate());
				try {
					eugeneCustomer.purchaseCoupon(purchasedCoupon3);
				} catch (NoAccessException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("~~~~~~~~~~~lowering amount by one~~~~~~~~~~~");
				Coupon purchasedCoupon = couponService.getOneCouponByID(3);
				try {
					eugeneCustomer.purchaseCoupon(purchasedCoupon);
				} catch (NoAccessException e) {
					System.out.println(e.getMessage());
				}
				System.out.println();

//				// Get all customer coupons
				System.out.println("*******customer facade - get all coupons*******");
				System.out.println(eugeneCustomer.getCustomerCoupons());
				System.out.println();

				// Get all coupons from specific category
				System.out.println("*******customer facade - get coupons by category*******");
				System.out.println("*fake info test*");
				try {
					System.out.println(eugeneCustomer.getCustoCouponsByCategory(Category.ESPORTS));
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("*real info test*");
				try {
					System.out.println(eugeneCustomer.getCustoCouponsByCategory(Category.CARS));
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
				System.out.println();

				// get all purchased coupons maxprice
				System.out.println("*******customer facade - get coupons by maxprice*******");
				System.out.println();
				// testing price
				try {
					System.out.println("*fake test info*");
					System.out.println(eugeneCustomer.getCutomerCouponsByMaxPrice(2));
					System.out.println();
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
				System.out.println();
				System.out.println("*real test info*");
				System.out.println(eugeneCustomer.getCutomerCouponsByMaxPrice(50));
				System.out.println();

				// get all customer details.
				System.out.println("*******customer facade - get customer details*******");
				System.out.println(eugeneCustomer.getCustomerDetails());
				System.out.println();
//				TODO DAILY JOB
//				// adding expired coupons to test daily job thread.
//				couponsDAO.addCoupon(cJob1);
//				couponsDAO.addCoupon(cJob2);
//				couponsDAO.addCoupon(cJob3);
//				couponsDAO.addCouponPurchase(3, 12);
//				couponsDAO.addCouponPurchase(4, 13);
//				couponsDAO.addCouponPurchase(5, 14);
//
//				
				print.endPrint(null);
	
		
	}

}
