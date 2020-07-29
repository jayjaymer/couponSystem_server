package com.couponsystem.jay.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.couponsystem.jay.beans.Coupon;
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	Coupon findById(int couponID);
	
	// CustomerVsCoupons
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO customers_coupons (customer_id, coupons_id) VALUES (:customerID, :couponID)", nativeQuery = true)
	void addCouponPurchase(int customerID, int couponID);

	@Transactional
	@Modifying
	@Query(value = "DELETE from customers_coupons WHERE customer_id=:customerID and coupons_id=:couponID", nativeQuery = true)
	void deleteCouponPurchase(int customerID,int couponID);
	
//	@Transactional
//	@Modifying
//	@Query(value = "DELETE from customer_coupons WHERE coupons_id= ?1", nativeQuery = true)
//	void deleteCouponPurchaseByCouponID(int couponID);
	
//	@Transactional
//	@Modifying
//	@Query(value = "SELECT * FROM customers_coupons", nativeQuery = true)
//	List<Coupon[]> getCustomersVsCoupons();
	
	@Transactional
    @Modifying
    @Query(value = "DELETE FROM customers_vs_coupons WHERE coupons_id=:couponID",nativeQuery = true)
    void deletePurchase(@Param("couponID") int couponID);
}
