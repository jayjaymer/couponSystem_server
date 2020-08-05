package com.couponsystem.jay.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.couponsystem.jay.exceptions.NoAccessException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder

public class Company {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
//	@OneToMany(targetEntity = Coupon.class,cascade = CascadeType.ALL)
//	@JoinColumn(name = "companyID",referencedColumnName = "id")
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	//@JoinColumn(name = "company_fk", referencedColumnName = "id",)
	private List<Coupon> coupons;
	
	@Override
	public String toString() {
		return "Company #"+ id + ", name=" + name + ", email=" + email + ", password=" + password + ", coupons = "+coupons + "\n";
	}
	
	public void setId(int id) throws NoAccessException{
		if (this.id == 0) {
			this.id = id;
		}else {
			throw new NoAccessException("Cannot change company id!");
		}
		
	}
	
	
	

}
