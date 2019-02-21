package com.paulrps.peladator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulrps.peladator.domain.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
