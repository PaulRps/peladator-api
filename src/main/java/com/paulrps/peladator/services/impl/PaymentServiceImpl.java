package com.paulrps.peladator.services.impl;

import static java.util.stream.Collectors.toList;

import com.paulrps.peladator.config.exceptions.ApiException;
import com.paulrps.peladator.config.exceptions.ApiMessageEnum;
import com.paulrps.peladator.domain.dto.PaymentFormData;
import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.repositories.PaymentRepository;
import com.paulrps.peladator.services.PaymentService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

  private static PaymentRepository paymentRepository;

  @Autowired
  PaymentServiceImpl(final PaymentRepository paymentRepository) {
    PaymentServiceImpl.paymentRepository = paymentRepository;
  }

  @Override
  public Payment save(Payment payment) {

    Optional.ofNullable(payment)
        .orElseThrow(
            () -> {
              log.error("Parameter user is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "payment");
            });

    try {
      log.debug("creating payment {}", payment.toString());
      return paymentRepository.save(payment);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      log.error(payment.toString());
      throw new ApiException(ApiMessageEnum.ERROR_ON_SAVE_ENTITY, e, payment.toString());
    }
  }

  @Override
  public void update(Payment payment) {
    Optional.ofNullable(payment)
        .orElseThrow(
            () -> {
              log.error("Parameter payment is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "payment");
            });

    Optional.ofNullable(payment.getId())
        .orElseThrow(
            () -> {
              log.error("PAYMENT_ID parameter is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "PAYMENT_ID");
            });
    try {
      log.debug("updating payment {}", payment.toString());
      paymentRepository.save(payment);
      log.debug("updated payment");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      log.error(payment.toString());
      throw new ApiException(ApiMessageEnum.ERROR_ON_SAVE_ENTITY, e, payment.toString());
    }
  }

  @Override
  public void delete(Long id) {

    Optional.ofNullable(id)
        .orElseThrow(
            () -> {
              log.error("PAYMENT_ID parameter is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "PAYMENT_ID");
            });

    if (!paymentRepository.existsById(id)) {
      log.error("Payment[id={}] do not exist", id);
      throw new ApiException(
          ApiMessageEnum.ERROR_RESOURCE_NOT_FOUND, String.format("Payment[id=%d]", id));
    }

    try {
      log.debug("deleting payment [id={}]", id);
      paymentRepository.deleteById(id);
      log.debug("deleted payment");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(
          ApiMessageEnum.ERROR_ON_DELETE_ENTITY, e, String.format("Payment[id=%d]", id));
    }
  }

  @Override
  public List<Payment> findAll() {
    try {
      log.debug("querying all users");
      return paymentRepository.findAll();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(ApiMessageEnum.ERROR_INTERNAL_SERVER, e, "all payments");
    }
  }

  @Override
  public Payment find(Long id) {
    Optional.ofNullable(id)
        .orElseThrow(
            () -> {
              log.error("Parameter PAYMENT_ID is null");
              return new ApiException(ApiMessageEnum.ERROR_PARAMETER_NOT_PRESENT, "PAYMENT_NAME");
            });
    try {
      log.debug("querying payment[id={}]", id);
      return paymentRepository.findById(id).orElse(null);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(
          ApiMessageEnum.ERROR_RESOURCE_NOT_FOUND, e, String.format("Payment[id=%d]", id));
    }
  }

  @Override
  public List<Payment> findAll(Integer month, List<Player> players) {

    try {
      log.debug(
          "querying payments by {}",
          String.format("month=%d, playersList=%d items", month, players.size()));
      return paymentRepository.findByDateAndPlayerIn(
          month, players.stream().map(Player::getId).collect(toList()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException(
          ApiMessageEnum.ERROR_RESOURCE_NOT_FOUND,
          e,
          String.format("Payments by [month=%d]", month));
    }
  }

  @Override
  public PaymentFormData formData(List<Player> players) {
    return PaymentFormData.builder().players(players).build();
  }
}
