package com.codeninja.orderservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codeninja.orderservice.entity.Order;
import com.codeninja.orderservice.exception.CustomException;
import com.codeninja.orderservice.external.client.PaymentService;
import com.codeninja.orderservice.external.client.ProductService;
import com.codeninja.orderservice.model.OrderRequest;
import com.codeninja.orderservice.model.OrderResponse;
import com.codeninja.orderservice.model.PaymentRequest;
import com.codeninja.orderservice.model.PaymentResponse;
import com.codeninja.orderservice.model.ProductResponse;
import com.codeninja.orderservice.repository.OrderRepository;
import com.codeninja.orderservice.util.DateTimeUtil;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private RestTemplate restTemplate;

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
				.amount(orderRequest.getTotalAmount()).orderStatus("CREATED").orderDate(LocalDateTime.now())
				.build();

		order = repository.save(order);
		
		log.info("Calling Payment Service to complete the payment ");
		
		PaymentRequest paymentRequest = PaymentRequest.builder()
				.orderId(order.getId())
				.amount(order.getAmount())
				.paymentMode(orderRequest.getPaymentMode())
				.build();
		
		String orderStatus = null;
		try {
			paymentService.doPayment(paymentRequest);
			log.info("Payment done successfully, changing order status to PLACED");
			orderStatus = "PLACED";
		} catch (Exception e) {
			log.error("Error occured in payment. changind order status to FAILED");
			orderStatus = "FAILED";
		}
		
		order.setOrderStatus(orderStatus);
		repository.save(order);

		log.info("Order placed successfully with order id : {}", order.getId());
		
		return order.getId();
	}

	@Override
	public OrderResponse getOrderDetails(long id) {
		log.info("Get order details for orderId : {}", id);
		
		Order order = repository.findById(id).orElseThrow(() -> new CustomException("Order not found for Id : " + id,
				"NOT_FOUND", 404, DateTimeUtil.getZonedDateTime()));
		
		log.info("Invoking Product Service to fetch  the product for id : {}" + order.getProductId());
		
		ProductResponse productResponse = restTemplate
				.getForObject("http://PRODUCT-SERVICE/product/" + order.getProductId(), ProductResponse.class);
		
		
		OrderResponse.ProductDetails productDetails= OrderResponse.ProductDetails.builder()
			.productId(productResponse.getProductId())
			.productName(productResponse.getProductName())
			.quantity(productResponse.getQuantity())
			.price(productResponse.getPrice())
			.build();
		
		log.info("Invoking payment details for order id : {} ", order.getId());
		PaymentResponse paymentResponse = restTemplate
				.getForObject("http://PAYMENT-SERVICE/payment/"+order.getId(), PaymentResponse.class);
		
		OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails.builder()
				.amount(paymentResponse.getAmount())
				.paymentMode(paymentResponse.getPaymentMode())
				.paymentStatus(paymentResponse.getPaymentStatus())
				.referenceNumber(paymentResponse.getReferenceNumber())
				.build();
		
		return OrderResponse.builder()
				.amount(order.getAmount())
				.orderDate(order.getOrderDate())
				.orderStatus(order.getOrderStatus())
				.orderId(order.getId())
				.productDetails(productDetails)
				.paymentDetails(paymentDetails)
				.build();
		
	}

}
