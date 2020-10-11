package com.couponsystem.jay.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@Entity
//@Table(name = "security")
//@NoArgsConstructor
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
//@Builder
public class Access {
	
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private int accessType;
	
	
	
	

}	
