package com.banking.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="users")
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstname;
	
	private String lastname;
	
	private String othername;
	
	private String gender;
	
	private String address;
	
	private String startedOfOrigin;
	
	private String accountNumber;
	
	private BigDecimal accountBalance;
	
	private String email;
	
	private String phoneNumber;
	
	private String alternativePhoneNumber;
	
	private String status;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
		
	@UpdateTimestamp
	private LocalDateTime modifiedAt;

}
