package com.banking.demo.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountInfo {
	
	private String accountName;
	
	private BigDecimal accountBalance;
	
	private String accountNumber;
	
	

}
