package com.codeninja.orderservice.service;

import com.codeninja.orderservice.model.OrderRequest;

public interface OrderService {

	long placeOrder(OrderRequest orderRequest);

}
