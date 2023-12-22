package com.codeninja.orderservice.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * name -> name of the service (mentioned in property file of product service
 * [application:name]) 
 * serice_name/api_name
 */
@FeignClient(name = "PRODUCT-SERVICE/product")
public interface ProductService {

	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity);

}
