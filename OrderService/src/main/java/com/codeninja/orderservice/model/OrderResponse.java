package com.codeninja.orderservice.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

	private long orderId;
	private LocalDateTime orderDate;
	private String orderStatus;
	private long amount;
	private ProductDetails productDetails;
	private PaymentDetails paymentDetails;

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ProductDetails {

		private long productId;
		private String productName;
		private long quantity;
		@JsonIgnore
		private long price;

	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class PaymentDetails {
		@JsonIgnore
		private long orderId;
		private long amount;
		private String referenceNumber;
		private PaymentMode paymentMode;
		private String paymentStatus;
	}

}
