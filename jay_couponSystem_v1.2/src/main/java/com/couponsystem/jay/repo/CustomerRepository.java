
package com.couponsystem.jay.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.couponsystem.jay.beans.Coupon;
import com.couponsystem.jay.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	Customer findByEmailAndPassword(String email, String password);
	Customer findById(int customerID);
	
	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM customers_coupons", nativeQuery = true)
	List<Coupon> getCustomersVsCoupons();

}
