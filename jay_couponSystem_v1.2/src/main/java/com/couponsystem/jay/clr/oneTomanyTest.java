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
import com.couponsystem.jay.repo.CouponRepository;
import com.couponsystem.jay.repo.CustomerRepository;
import com.couponsystem.jay.util.DateUtil;

//@Component
//@Order(value = 7)
public class oneTomanyTest implements CommandLineRunner {
	@Autowired
	private CustomerRepository repo;
	@Autowired
	private CouponRepository copRepo;

	@Override
	public void run(String... args) throws Exception {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		Coupon coupon1 = Coupon.builder()
				.companyID(1)
				.category(Category.RESTAURANTS)
				.title("Sadsgasdasdgdsao")
				.description("fdshsfdfdhshsfdhfdsfsro")
				.startDate(DateUtil.changeDateType(new Date(2020,1,1)))
				.endDate(DateUtil.changeDateType(new Date(2020, 9, 10)))
				.amount(10)
				.price(4.99)
				.image("sfdhsfdh")
				.build();
		Coupon coupon2 = Coupon.builder()
				.companyID(1)
				.category(Category.RESTAURANTS)
				.title("Sadsgasdasdgdsao222222222")
				.description("fdshsfdfdhshsfdhfdsfsro22222222")
				.startDate(DateUtil.changeDateType(new Date(2020,1,1)))
				.endDate(DateUtil.changeDateType(new Date(2020, 9, 10)))
				.amount(10)
				.price(4.99)
				.image("sfdhsfdh2222222")
				.build();
		
		
		List<Coupon> cps = Arrays.asList(coupon1,coupon2);
		Customer customer1 = Customer.builder()
				.firstName("Jay111111111111")
				.lastName("Mer11111111")
				.email("jay@gmail.com111111111")
				.password("123")
				.coupons(cps)
				.build();
		repo.save(customer1);
		
		System.out.println(cps);
		
//		repo.delete(customer1);
		

	}

}
