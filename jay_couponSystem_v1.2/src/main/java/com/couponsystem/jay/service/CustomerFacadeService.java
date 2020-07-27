package com.couponsystem.jay.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.couponsystem.jay.beans.Coupon;
import com.couponsystem.jay.beans.Customer;
import com.couponsystem.jay.exceptions.NoAccessException;

@Service
public class CustomerFacadeService extends ClientFacadeService{
	CustomerService customerService = new CustomerService();
	CouponService couponService = new CouponService();
	Customer customer;
	private int customerID;
	
	
	@Override
	public boolean login(String email, String password) {
		List<Customer> customers = customerService.getAllCustomer();
		for (Customer custum : customers) {
			if (custum.getEmail().equalsIgnoreCase(email) && custum.getPassword().equalsIgnoreCase(password)) {
				System.out.println("Customer logged in.");
				return true;
			}
		}
		System.out.println("Customer not found.");
		return false;

	}
	
	/**
	 * on this method the customer is purchasing a coupon ONLY IF : the coupon is
	 * NOT purchased by the same customer the amount of the coupon is NOT 0 the
	 * coupon is NOT expired. AFTER A purchase is made, amount decreased by 1.
	 * 
	 * @param coupon - insert a coupon to Database
	 * @throws CouponPurchaseException - if one of the conditions not passed
	 */
//	public void purchaseCoupon(Coupon coupon) throws NoAccessException {
//		List<CustomersVsCoupons> customerVsCoupons = couponService.getAllPurchasedCoupons();
//		// not allowed to buy a purchased coupon.
//		for (CustomersVsCoupons customersVsCoupons : customersVsCoupons) {
//			if (customerVsCoupon.getCustomerID() == this.customerID
//					&& customerVsCoupon.getCouponID() == coupon.getId()) {
//				throw new NoAccessException("Coupon already Purchased once by customer " + customerID);
//
//			}
//		}
//
//		// cant purchase coupon when amount is 0
//		Coupon couponpurchase = couponService.getOneCouponByID(coupon.getId());
//		if (couponpurchase.getAmount() <= 0) {
//			throw new NoAccessException("Coupon out of stock");
//		}
//
//		// if date is expired, coupon is not available.
//		if (couponpurchase.getEndDate().before(new Date())) {
//			throw new NoAccessException("Coupon is Expired!");
//		}
//
//		// decrease coupon amount by 1 if purchased.
//		System.out.println("purchase coupon in proccess.... decreasing coupon amount.");
//		System.out.println("amount before decrease : " + couponpurchase.getAmount());
//		couponpurchase.setAmount(couponpurchase.getAmount() - 1);
//		couponService.updateCoupon(couponpurchase);
//		System.out.println("amount after decrease : " + couponpurchase.getAmount());
//		couponService.addPurchaseCoupon(this.customerID, coupon.getId());
//		;
//		System.out.println("coupon was purchased.");
//
//	}
	
	// get all the coupons of the connected to the customer
//		public List<Coupon> getCustomerCoupons()  {
//			List<Coupon> getall = new ArrayList<>();
//			List<CustomerVsCoupon> customerVsCoupons = couponService.getAllPurchasedCoupons();
//			for (CustomerVsCoupon customerVsCoupon : customerVsCoupons) {
//				if (customerVsCoupon.getCustomerID() == customerID) {
//					getall.add(coupondao.getOneCoupon(customerVsCoupon.getCouponID()));
//				}
//			}
//			return getall;
//		}
	

}
