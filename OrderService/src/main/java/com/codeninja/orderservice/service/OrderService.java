package com.codeninja.orderservice.service;

import com.codeninja.orderservice.model.OrderRequest;
import com.codeninja.orderservice.model.OrderResponse;

public interface OrderService {

	long placeOrder(OrderRequest orderRequest);

	OrderResponse getOrderDetails(long id);

}
