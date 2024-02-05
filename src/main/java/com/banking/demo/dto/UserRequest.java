package com.banking.demo.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

private String firstname;
	
	private String lastname;
	
	private String othername;
	
	private String gender;
	
	private String address;
	
	private String startedOfOrigin;
	
	private String email;
	
	private String phoneNumber;
	
	private String alternativePhoneNumber;
	
	
}
