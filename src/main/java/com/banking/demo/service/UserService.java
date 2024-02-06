package com.banking.demo.service;

import java.math.BigDecimal;

import com.banking.demo.dto.*;
import com.banking.demo.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.demo.entity.User;
import com.banking.demo.repository.UserRepository;
import com.banking.demo.service.impl.UserServiceImpl;
import com.banking.demo.utils.AccountUtils;

@Service
public class UserService implements UserServiceImpl {

	private UserRepository userRepository;


	@Autowired
	private EmailServiceImpl emailServiceImpl;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override //Yeni bir account açma , yeni user olarak db ye kaydetme
	public BankResponse createAccount(UserRequest userRequest) {

		if (userRepository.existsByEmail(userRequest.getEmail())) {
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

		//Email Görderme
		EmailDetails emailDetails = EmailDetails.builder()
				.recipient(savedUser.getEmail())
				.subject("Account Created")
				.messageBody("Congrats!Your account has ben successfuly created. \n Your Account Details : \n " +
						"Account Name: " + savedUser.getFirstname() + " " + savedUser.getLastname() +
						" " + savedUser.getOthername() +
						"\n Account Number: " + savedUser.getAccountNumber())
				.build();
		emailServiceImpl.sendEmailAlert(emailDetails);

		return BankResponse.builder()
				.responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
				.responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
				.accountInfo(AccountInfo.builder().accountBalance(savedUser.getAccountBalance())
						.accountNumber(savedUser.getAccountNumber())
						.accountName(savedUser.getFirstname() + " " + savedUser.getLastname() + " " + savedUser.getOthername())
						.build())
				.build();
	}

	@Override
	//sağlanan hesap numarasının veritabanında mevcut olup olmadığını kontrol et.
	public BankResponse balanceEnquiry(EnquiryRequest request) {
		boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
		if (!isAccountExist) {
			return BankResponse.builder()
					.responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
					.responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
					.accountInfo(null)
					.build();
		}
		User foundUser = userRepository.findByAccountNumber(request.getAccountNumber());
		return BankResponse.builder()
				.responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
				.responseMessage(AccountUtils.ACCOUNT_FOUND_SUCCESS)
				.accountInfo(AccountInfo.builder()
						.accountBalance(foundUser.getAccountBalance())
						.accountNumber(request.getAccountNumber())
						.accountName(foundUser.getFirstname() + " " + foundUser.getLastname())
						.build())
				.build();
	}

	@Override
	public String nameEnquiry(EnquiryRequest request) {
		boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
		if (!isAccountExist) {
			return AccountUtils.ACCOUNT_EXISTS_MESSAGE;
		}
		User foundUser = userRepository.findByAccountNumber(request.getAccountNumber());
		return foundUser.getFirstname() + " " + foundUser.getLastname();
	}

}