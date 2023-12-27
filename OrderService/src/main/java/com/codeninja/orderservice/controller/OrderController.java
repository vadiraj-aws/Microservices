package com.codeninja.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeninja.orderservice.model.OrderRequest;
import com.codeninja.orderservice.model.OrderResponse;
import com.codeninja.orderservice.service.OrderService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

	@Autowired
	private OrderService service;
	
	@PostMapping("/placeOrder")
	public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
		long orderID = service.placeOrder(orderRequest);
		log.info("Order id : {}" , orderID);
		return new ResponseEntity<>(orderID,HttpStatus.OK);
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable("Id") long id){
		return new ResponseEntity<>(service.getOrderDetails(id), HttpStatus.OK);
	}
	
	
}
