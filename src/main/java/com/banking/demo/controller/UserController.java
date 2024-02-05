package com.banking.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.demo.dto.BankResponse;
import com.banking.demo.dto.UserRequest;
import com.banking.demo.service.UserService;

@RestController
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	@PostMapping("api/user")
	public BankResponse createAccount(@RequestBody UserRequest userRequest) {
		return userService.createAccount(userRequest);
	}
	
}
