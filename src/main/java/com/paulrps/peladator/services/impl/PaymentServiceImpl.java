package com.paulrps.peladator.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.repositories.PaymentRepository;
import com.paulrps.peladator.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Payment addPayment(Payment payment) {
		
		if (!Optional.ofNullable(payment).isPresent()) {
			throw new RuntimeException("");
		}
		
		if (Optional.ofNullable(payment.getId()).isPresent()) {
			
			Optional<Payment> findById = paymentRepository.findById(payment.getId());
			
			if (findById.isPresent()) {
				return findById.get();
			}
			
		}
		
		return paymentRepository.save(payment);
	}

	@Override
	public boolean deletePayment(Long id) {
		
		if (!Optional.ofNullable(id).isPresent()) {
			throw new RuntimeException("");
		}
		
		if (paymentRepository.findById(id).isPresent()) {

			paymentRepository.deleteById(id);
			
			return true;
		}
		
		
		return false;
	}

	@Override
	public List<Payment> getAllPayments() {
		
		return paymentRepository.findAll();
	}

	@Override
	public Payment getOnePayment(Long id) {
		
		if (!Optional.ofNullable(id).isPresent()) {
			throw new RuntimeException("");
		}
		
		return paymentRepository.findById(id).orElse(null);
	}
	
	
	
}
