package com.codeninja.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeninja.orderservice.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
