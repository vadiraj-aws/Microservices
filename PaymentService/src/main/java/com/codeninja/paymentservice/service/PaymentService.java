package com.codeninja.paymentservice.service;

import com.codeninja.paymentservice.model.PaymentRequest;
import com.codeninja.paymentservice.model.PaymentResponse;

public interface PaymentService {

	long doPayment(PaymentRequest request);

	PaymentResponse getPaymentDetailsByOrderId(long id);

}
