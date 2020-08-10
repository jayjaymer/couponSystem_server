package com.couponsystem.jay.rest.handler;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdviceError {
	private long code;
	private String message;
		
}
