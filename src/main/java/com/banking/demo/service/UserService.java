package com.banking.demo.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.banking.demo.dto.AccountInfo;
import com.banking.demo.dto.BankResponse;
import com.banking.demo.dto.UserRequest;
import com.banking.demo.entity.User;
import com.banking.demo.repository.UserRepository;
import com.banking.demo.service.impl.UserServiceImpl;
import com.banking.demo.utils.AccountUtils;

@Service
public class UserService implements UserServiceImpl {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override //Yeni bir account açma , yeni user olarak db ye kaydetme
	public BankResponse createAccount(UserRequest userRequest) {
		
		if(userRepository.existsByEmail(userRequest.getEmail())) {
			return BankResponse.builder()
					.responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
					.responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
					.accountInfo(null)
					.build();
		
		}
		
		User newUser = User.builder()
					.firstname(userRequest.getFirstname())
					.lastname(userRequest.getLastname())
					.othername(userRequest.getOthername())
					.gender(userRequest.getGender())
					.address(userRequest.getAddress())
					.startedOfOrigin(userRequest.getStartedOfOrigin())
					.accountNumber(AccountUtils.generateAccountNumber())
					.accountBalance(BigDecimal.ZERO)
					.email(userRequest.getEmail())
					.phoneNumber(userRequest.getPhoneNumber())
					.alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
					.status("ACTIVE")
		           .build();
		
		User savedUser = userRepository.save(newUser);
		
		return BankResponse.builder()
				.responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
				.responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
				.accountInfo(AccountInfo.builder().accountBalance(savedUser.getAccountBalance())
				.accountNumber(savedUser.getAccountNumber())
				.accountName(savedUser.getFirstname() + " " + savedUser.getLastname() + " " + savedUser.getOthername())
				.build())
				.build();
	}

}
