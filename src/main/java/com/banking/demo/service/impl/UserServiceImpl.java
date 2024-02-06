package com.banking.demo.service.impl;

import com.banking.demo.dto.BankResponse;
import com.banking.demo.dto.EnquiryRequest;
import com.banking.demo.dto.UserRequest;

public interface UserServiceImpl {

	
	BankResponse createAccount(UserRequest userRequest);

	BankResponse balanceEnquiry(EnquiryRequest request);

	String nameEnquiry(EnquiryRequest request);
}
