package com.codeninja.orderservice.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.codeninja.orderservice.exception.CustomException;
import com.codeninja.orderservice.model.PaymentRequest;
import com.codeninja.orderservice.util.DateTimeUtil;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentService {
	
	@PostMapping
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest request);
	
	default void fallback(Exception e) {
		throw new CustomException("Payment Service is down!", "UNAVIALBLE", 500,
				DateTimeUtil.getZonedDateTime());
	}

}
