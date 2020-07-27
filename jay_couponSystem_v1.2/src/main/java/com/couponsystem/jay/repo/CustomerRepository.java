
package com.couponsystem.jay.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.couponsystem.jay.beans.Coupon;
import com.couponsystem.jay.beans.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	Customer findByEmailAndPassword(String email, String password);

}
