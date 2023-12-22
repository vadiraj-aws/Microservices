package com.codeninja.orderservice.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeninja.orderservice.entity.Order;
import com.codeninja.orderservice.external.client.ProductService;
import com.codeninja.orderservice.model.OrderRequest;
import com.codeninja.orderservice.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductService productService;

	/**
	 * OrderEntity -> save the data with status order created. Product service to
	 * block our product (reduce the quantity). 
	 * Payment service -> payment -> success -> Complete else cancel
	 */

	@Override
	public long placeOrder(OrderRequest orderRequest) {

		log.info("Placing order request : {}", orderRequest);
		
		log.info("Reducing the Quantity {} ", orderRequest.getQuantity());
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

		log.info("Creating order with status CREATED");
		Order order = Order.builder().productId(orderRequest.getProductId()).quantity(orderRequest.getQuantity())
				.amount(orderRequest.getTotalAmount()).orderStatus("CREATED").orderDate(Instant.now()).build();

		order = repository.save(order);

		log.info("Order placed successfully with order id : {}", order.getId());

		return order.getId();
	}

}
