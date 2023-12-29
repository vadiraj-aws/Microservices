package com.codeninja.orderservice.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeninja.orderservice.exception.CustomException;
import com.codeninja.orderservice.util.DateTimeUtil;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/**
 * name -> name of the service (mentioned in property file of product service
 * [application:name]) 
 * serice_name/api_name
 */
@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PRODUCT-SERVICE/product")
public interface ProductService {

	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity);

	default void fallback(Exception e) {
		throw new CustomException("Product Service is down!", "UNAVIALBLE", 500,
				DateTimeUtil.getZonedDateTime());
	}
	
}
