package com.paulrps.peladator.services;

import com.paulrps.peladator.domain.entities.Payment;
import java.util.List;

public interface PaymentService {

  Payment save(Payment payment);

  void update(Payment payment);

  boolean delete(Long id);

  List<Payment> getAll();

  Payment getOne(Long id);
}
