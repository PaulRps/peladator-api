package com.paulrps.peladator.services.impl;

import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.repositories.PaymentRepository;
import com.paulrps.peladator.services.PaymentService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

  @Autowired private PaymentRepository paymentRepository;

  @Override
  public Payment save(Payment payment) {

    if (!Optional.ofNullable(payment).isPresent()) {
      throw new RuntimeException("");
    }

    return paymentRepository.save(payment);
  }

  @Override
  public void update(Payment payment) {
    save(payment);
  }

  @Override
  public boolean delete(Long id) {

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
  public List<Payment> getAll() {
    return paymentRepository.findAll();
  }

  @Override
  public Payment getOne(Long id) {
    if (!Optional.ofNullable(id).isPresent()) {
      throw new RuntimeException("");
    }
    return paymentRepository.findById(id).orElse(null);
  }
}
