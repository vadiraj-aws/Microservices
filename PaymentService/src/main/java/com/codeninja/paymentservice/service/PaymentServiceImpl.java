package com.codeninja.paymentservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeninja.paymentservice.entity.TransactionDetails;
import com.codeninja.paymentservice.model.PaymentRequest;
import com.codeninja.paymentservice.repository.TransactionRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public long doPayment(PaymentRequest request) {
		log.info("Recording payment details : {}" + request);

		TransactionDetails transactionDetails = TransactionDetails.builder().orderId(request.getOrderId())
				.amount(request.getAmount()).paymentMode(request.getPaymentMode().name())
				.referenceNumber(request.getReferenceNumber()).paymentStatus("SUCCESS").paymentDate(LocalDateTime.now())
				.build();

		transactionRepository.save(transactionDetails);
		
		log.info("Transaction is completed with Id : {}" + transactionDetails.getId());
		
		return transactionDetails.getId();
	}

}
