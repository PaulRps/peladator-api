package com.paulrps.peladator.services;

import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.domain.entities.Player;
import java.util.List;

public interface PaymentService {

  Payment save(Payment payment);

  Payment update(Payment payment);

  boolean delete(Long id);

  List<Payment> findAll();

  Payment find(Long id);

  List<Payment> findAll(Integer month, List<Player> players);
}
