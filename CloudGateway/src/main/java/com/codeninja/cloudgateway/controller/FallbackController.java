package com.codeninja.cloudgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

	@GetMapping("/orderServiceFallback")
	public String orderServiceFallback() {
		return "Order Service is down!";
	}
	
	@GetMapping("/paymentServiceFallback")
	public String paymentServiceFallback() {
		return "PaymentService is down!";
	}
	
	@GetMapping("/productServiceFallback")
	public String productServiceFallback() {
		return "Product Service is down!";
	}
	
}
