package com.couponsystem.jay.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Customers")
@Builder
public class Customer {
	
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Coupon> coupons;

}
