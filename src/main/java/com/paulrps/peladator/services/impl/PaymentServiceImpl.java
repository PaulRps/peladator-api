package com.paulrps.peladator.services.impl;

import static java.util.stream.Collectors.toList;

import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.domain.entities.Player;
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
  public Payment update(Payment payment) {
    return save(payment);
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
  public List<Payment> findAll() {
    return paymentRepository.findAll();
  }

  @Override
  public Payment find(Long id) {
    if (!Optional.ofNullable(id).isPresent()) {
      throw new RuntimeException("");
    }
    return paymentRepository.findById(id).orElse(null);
  }

  @Override
  public List<Payment> findAll(Integer month, List<Player> players) {
    return paymentRepository.findByDateAndPlayerIn(
        month, players.stream().map(Player::getId).collect(toList()));
  }
}
