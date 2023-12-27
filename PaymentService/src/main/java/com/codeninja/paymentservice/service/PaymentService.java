package com.codeninja.paymentservice.service;

import com.codeninja.paymentservice.model.PaymentRequest;

public interface PaymentService {

	long doPayment(PaymentRequest request);

}
