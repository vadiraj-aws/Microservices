package com.codeninja.paymentservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRANSACTION_DETAILS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "ORDER_ID")
	private long orderId;
	
	@Column(name = "MODE")
	private String paymentMode;
	
	@Column(name="REFERENCE_NUMBER")
	private String referenceNumber;
	
	@Column(name = "PAYMENT_DATE")
	private LocalDateTime paymentDate;
	
	@Column(name = "PAYMENT_STATUS")
	private String paymentStatus;
	
	@Column(name="AMOUNT")
	private long amount;

}
