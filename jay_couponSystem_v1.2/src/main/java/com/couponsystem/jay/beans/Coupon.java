package com.couponsystem.jay.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "coupons")
@Builder
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private int companyID;
	@Column(nullable = false)
	private Category category;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private Date startDate;
	@Column(nullable = false)
	private Date endDate;
	@Column(nullable = false)
	private int amount;
	@Column(nullable = false)
	private double price;
	@Column(nullable = false)
	private String image;

}
