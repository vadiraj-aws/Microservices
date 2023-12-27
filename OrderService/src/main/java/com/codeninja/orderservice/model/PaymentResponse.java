package com.codeninja.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

	private long orderId;
	private long amount;
	private String referenceNumber;
	private PaymentMode paymentMode;
	private String paymentStatus;
	
	
}
