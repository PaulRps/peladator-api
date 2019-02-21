package com.paulrps.peladator.services;

import java.util.List;

import com.paulrps.peladator.domain.entities.Payment;

public interface PaymentService {
	
	public Payment addPayment(Payment payment);
	
	public boolean deletePayment(Long id);
	
	public List<Payment> getAllPayments();
	
	public Payment getOnePayment(Long id);

}
