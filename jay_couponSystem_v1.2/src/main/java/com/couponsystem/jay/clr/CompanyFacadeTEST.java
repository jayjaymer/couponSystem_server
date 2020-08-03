package com.couponsystem.jay.clr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.couponsystem.jay.beans.Category;
import com.couponsystem.jay.beans.Company;
import com.couponsystem.jay.beans.Coupon;
import com.couponsystem.jay.exceptions.AlreadyExistsException;
import com.couponsystem.jay.exceptions.LoginFailledException;
import com.couponsystem.jay.exceptions.NoAccessException;
import com.couponsystem.jay.exceptions.NotFoundException;
import com.couponsystem.jay.login.ClientType;
import com.couponsystem.jay.login.LoginManager;
import com.couponsystem.jay.service.AdminFacadeService;
import com.couponsystem.jay.service.CompanyFacadeService;
import com.couponsystem.jay.service.CompanyService;
import com.couponsystem.jay.service.CouponService;
import com.couponsystem.jay.util.DateUtil;
@Component
@Order(value = 5)
public class CompanyFacadeTEST implements CommandLineRunner {
	@Autowired
	private CompanyFacadeService companyFacadeService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private LoginManager managerLogin;
	@Autowired
	protected CompanyService admin;

	@Override
	public void run(String... args) throws Exception {
		System.out.println();
		System.out.println();
		System.out.println();
		
		Company porche = Company.builder()
				.name("Porsche")
				.email("porsche@gmail.com")
				.password("123")
				
				.build();
		admin.addCompany(porche);
		
		// COMPANY FACADE TEST
		
				CompanyFacadeService porscheCompany = null;
				
				System.out.println(
						"**********************************************************************Company Facade test**********************************************************************");
				System.out.println();
				
				try {
					// LOGIN TEST
					System.out.println("*******Company facade - login as porsche company*******");
					System.out.println();
					System.out.println("*fake info test*");
					porscheCompany = (CompanyFacadeService) managerLogin.login("asgdasd", "asdgdsa", ClientType.COMPANY);
				} catch (LoginFailledException msg) {
					System.out.println(msg.getMessage());
				}
				System.out.println();

				try {

					System.out.println("*real info test*");
					porscheCompany = (CompanyFacadeService) managerLogin.login(porche.getEmail(), porche.getPassword(), ClientType.COMPANY);
				} catch (LoginFailledException msg) {
					System.out.println(msg.getMessage());
				}
				System.out.println();
				System.out.println("$$$$$$$$ PROSCHE $$$$$$$$");
				System.out.println(companyFacadeService.getCompanyService().findCompanyByID(porche.getId()));
				System.out.println();

				// add company if title is not the same as other coupons
				System.out.println("*******company facade - create coupon test*******");
				System.out.println();
				System.out.println("~~~trying to add an existing title.~~~");
				Coupon coupontest =  Coupon.builder()
						.companyID(4)
						.category(Category.RESTAURANTS)
						.title("Summer Sale - Zero")
						.description("adsag")
						.startDate(DateUtil.changeDateType(new Date(2020,1,1)))
						.endDate(DateUtil.changeDateType(new Date(2020, 9, 10)))
						.amount(10)
						.price(4.99)
						.image("http://adgadsg")
						.build();
				try {
					companyFacadeService.createCoupon(coupontest);
				} catch (AlreadyExistsException e) {
					System.out.println(e.getMessage());
				}

				Coupon porscheCoupon1 =  Coupon.builder()
						.companyID(porche.getId())
						.category(Category.CARS)
						.title("Porsche NEW Taycan")
						.description("Choose any color for new order")
						.startDate(DateUtil.changeDateType(new Date(2020,1,1)))
						.endDate(DateUtil.changeDateType(new Date(2020, 9, 10)))
						.amount(10)
						.price(1000)
						.image("http://porsche")
						.build();
				
				Coupon porscheCoupon2 =  Coupon.builder()
						.companyID(porche.getId())
						.category(Category.CARS)
						.title("GT3 RS Wheels")
						.description("Limited coupon for 20inch Limited edition wheels")
						.startDate(DateUtil.changeDateType(new Date(2020,1,1)))
						.endDate(DateUtil.changeDateType(new Date(2020, 9, 10)))
						.amount(5)
						.price(500)
						.image("http://Porsche")
						.build();
				
				try {
					System.out.println("~~~adding unique coupons~~~");
					companyFacadeService.createCoupon(porscheCoupon1);
					companyFacadeService.createCoupon(porscheCoupon2);
					System.out.println("Coupons Added.");
				} catch (AlreadyExistsException e) {
					System.out.println(e.getMessage());
				}
				
		//		List<Coupon> porscheCoupons = Arrays.asList(porscheCoupon1,porscheCoupon2);
				List<Coupon> porscheCoupons = new ArrayList<Coupon>();
		//		porche.setCoupons(porscheCoupons);
				porscheCoupons.add(porscheCoupon1);
				porscheCoupons.add(porscheCoupon2);
				
				System.out.println(companyFacadeService.getCompanyService().findCompanyByID(porche.getId()));
				System.out.println();
				

//				System.out.println(colaCompany.getCompanyCoupons());
//				System.out.println();
				
//				// updateCoupon method
//				System.out.println("*******company facade - cant change company and coupon ID*******");
//				System.out.println("~~~trying to change id.~~~");
//				System.out.println(couponService.getOneCouponByID(coupontest1.getId()));
//				try {
//					Coupon coupon = couponService.getOneCouponByID(coupontest1.getId());
//					coupon.setId(21312321);
//					
//					couponService.updateCoupon(coupon);
//				} catch (NoAccessException e) {
//					System.out.println(e.getMessage());
//				}
//				try {
//					Coupon coupon = couponService.getOneCouponByID(coupontest1.getId());
//					coupon.setcompanyID(124);
//					
//					couponService.updateCoupon(coupon);
//				} catch (NoAccessException e) {
//					System.out.println(e.getMessage());
//				}
//				System.out.println();
//				
//				System.out.println("~~~trying to change title.~~~");
//				System.out.println("BEFORE");
//				System.out.println(couponService.getOneCouponByID(coupontest1.getId()));
//				Coupon coupon = couponService.getOneCouponByID(coupontest1.getId());
//				coupon.setTitle("sadfghsdagdasdsggdasadsgadsgasdg");
//				couponService.updateCoupon(coupon);
//				System.out.println("AFTER");
//				System.out.println(couponService.getOneCouponByID(coupontest1.getId()));
//				System.out.println("Title Changed.");
				
				
//				// delete purchased coupons history
//				System.out.println("*******company facade - delete coupon test*******");
//				colaCompany.deleteCoupon(8);
//				System.out.println();

				// get all coupons from current company login
//				System.out.println("*******company facade - get all coupons by company*******");
//				System.out.println(couponService.getCouponsByCompanyID(colaCompany.));
//				System.out.println();

//				// get all coupons from specific category
//				System.out.println("*******company facade - get all coupons by category*******");
//				System.out.println("Available coupons :");
//				System.out.println(colaCompany.getCompanyCouponsByCategory(Category.FOOD));
//				System.out.println("None Available coupons :");
//				System.out.println(colaCompany.getCompanyCouponsByCategory(Category.ESPORTS));
//				System.out.println();
//
//				// get all coupons of company till maxprice
//				System.out.println("*******company facade - get all coupon by max price*******");
//				System.out.println("Available price");
//				System.out.println(colaCompany.getCompanyCouponsByMaxPrice(50));
//				System.out.println();
//				System.out.println("None Available price");
//				System.out.println(colaCompany.getCompanyCouponsByMaxPrice(1.99));
//				System.out.println();
//
//				// get company info
//				// FOR SOME REASON RETURNS ALSO DETAILS WITH NULL
//				System.out.println("*******company facade - get company info*******");
//				System.out.println(colaCompany.getCompanyDetails());
//				System.out.println();
		

	}

}
