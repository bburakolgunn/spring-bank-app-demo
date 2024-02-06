package com.banking.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	Boolean existsByEmail(String email);
	
	Boolean existsByAccountNumber(String accountNumber);

	User findByAccountNumber(String accountNumber);
}
