package com.codeninja.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeninja.paymentservice.model.PaymentRequest;
import com.codeninja.paymentservice.model.PaymentResponse;
import com.codeninja.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest request){
		return new ResponseEntity<>(paymentService.doPayment(request), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentResponse> getPaymentDetails(@PathVariable long id){
		return new ResponseEntity<>(paymentService.getPaymentDetailsByOrderId(id), HttpStatus.OK);
	}

}
