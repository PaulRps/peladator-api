package com.paulrps.peladator.services;

import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.domain.entities.Player;
import java.util.List;

public interface PaymentService {

  Payment save(Payment payment);

  Payment update(Payment payment);

  boolean delete(Long id);

  List<Payment> getAll();

  Payment getOne(Long id);

  List<Payment> findByPlayersAndDate(Integer month, List<Player> players);
}
