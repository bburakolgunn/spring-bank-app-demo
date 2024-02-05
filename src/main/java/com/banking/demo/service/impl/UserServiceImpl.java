package com.banking.demo.service.impl;

import com.banking.demo.dto.BankResponse;
import com.banking.demo.dto.UserRequest;

public interface UserServiceImpl {

	
	BankResponse createAccount(UserRequest userRequest);
}
