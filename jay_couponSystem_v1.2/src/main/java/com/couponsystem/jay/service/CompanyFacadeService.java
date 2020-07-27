package com.couponsystem.jay.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.couponsystem.jay.beans.Category;
import com.couponsystem.jay.beans.Company;
import com.couponsystem.jay.beans.Coupon;
import com.couponsystem.jay.exceptions.NoAccessException;
import com.couponsystem.jay.exceptions.NotFoundException;

import lombok.Getter;
import lombok.Setter;

@Service
@Scope("prototype")
public class CompanyFacadeService extends ClientFacadeService {
	CompanyService companyService = new CompanyService();
	CouponService couponService = new CouponService();
	Company company;
	
//	@Getter
//	@Setter
	private int companyID;

	@Override
	public boolean login(String email, String password) {
		List<Company> companies = companyService.getAllCompanies();
		System.out.println(email + " " + password);
		for (Company company : companies) {
			if (company.getEmail().equalsIgnoreCase(email) && company.getPassword().equalsIgnoreCase(password)) {
				System.out.println("Company logged in");
				return true;
			}
		}
		System.out.println("Company not found.");
		return false;
	}

	/**
	 * on this method the company is creating new coupon ONLY IF : the new coupon is
	 * not used by other coupon from the same company ALLOWED - other company to add
	 * the same name of a coupon.
	 * 
	 * @param coupon - insert a coupon to Database
	 * @throws TitleUsedException - if condition not passed.
	 */
//	public void createCoupon(Coupon coupon) throws NoAccessException {
//		// cant add exsiting name of a coupon from the SAME company.
//		// can add coupon with the same name from OTHER company
//
//		List<Coupon> coupons = 
//
//		for (Coupon cpn : coupons) {
//			if (coupon.getTitle().equalsIgnoreCase(cpn.getTitle())) {
//				throw new NoAccessException("title is used!");
//			}
//		}
//		couponService.addCoupon(coupon);
//	}

	// cant update coupon id or company id
	public void updateCoupon(Coupon coupon) throws NoAccessException {
		if (companyID != coupon.getCompanyID()) {
			throw new NoAccessException("No access to change company ID");
		}

		couponService.updateCoupon(coupon);
	}

	// to delete coupon most delete all connections to the coupon
//		public void deleteCoupon(int couponID) {
//			List<Coupon> coupons = couponService.getAllCoupons();
//			for (Coupon coupon : coupons) {
//				if (coupon.getId() == couponID) {
//					System.out.println("deleting purchase history ");
//					couponService.deletePurchaseCoupon(customerID, couponID);
//					System.out.println("deleted.");
//				}
//			}
//			couponService.deleteCoupon(couponID);
//		}

	// get all the coupons of the connected company
//		public List<Coupon> getCompanyCoupons() throws NoAccessException {
//
//			if (couponsDAO.getCouponsByCompanyID(companyID) != null) {
//				return couponsDAO.getCouponsByCompanyID(companyID);
//			} else {
//				throw new NoAccessException("no coupons found for this company!");
//			}
//		}

	// get all the coupons of the connected company by category
	public List<Coupon> getCompanyCouponsByCategory(Category category) {
		List<Coupon> result = new ArrayList<Coupon>();
		List<Coupon> coupons = couponService.getAllCoupons();

		if (coupons != null) {
			for (Coupon coupon : coupons) {
				if (coupon.getCategory().equals(category) && coupon.getCompanyID() == companyID) {
					result.add(coupon);
				}
			}
			if (result.isEmpty() == true) {
				System.out.println("no coupons");
			}
		}
		return result;

	}

	// get all the coupons of the connected company by max price
//		public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) {
//			List<Coupon> result = new ArrayList<Coupon>();
//			List<Coupon> coupons = couponsDAO.getCouponsByCompanyID(companyID);
//			for (Coupon coupon : coupons) {
//				if (coupon.getPrice() <= maxPrice) {
//					result.add(coupon);
//
//				}
//			}
//			if (result.isEmpty() == true) {
//				System.out.println("no coupons for this price");
//			}
//			return result;
//		}

	// get all company info including coupons
//	public Company getCompanyDetails()  {
//			Company companies = companyService.getOneCompanyByID(companyID);
//			companies.setCoupons(getCompanyCoupons());
//			return companies;
//		}

	// just a getter for client facade company login
//	public int getCompanyID(String email, String password) throws NotFoundException {
//		Company company = companyService.getOneCompanyByEmailAndPassword(email, password);
//
//		return company.getId();
//	}


}
