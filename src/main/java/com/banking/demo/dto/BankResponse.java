package com.banking.demo.dto;

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
public class BankResponse {

	private String responseCode;
	
	private String responseMessage;
	
	private AccountInfo accountInfo;
	
	
}
