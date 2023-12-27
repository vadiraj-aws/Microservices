package com.codeninja.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeninja.paymentservice.entity.TransactionDetails;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetails, Long> {

}
