package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.dto.PaymentFormData;
import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.services.PaymentService;
import com.paulrps.peladator.services.PlayerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment")
public class PaymentController {

  @Autowired PaymentService paymentService;
  @Autowired PlayerService playerService;

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("form-data")
  ResponseEntity<PaymentFormData> getFormData() {
    return ResponseEntity.ok(PaymentFormData.builder().players(playerService.getAll()).build());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping
  ResponseEntity<List<Payment>> getAll() {
    return ResponseEntity.ok(paymentService.getAll());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  ResponseEntity<List<Payment>> save(@RequestBody Payment payment) {
    paymentService.save(payment);
    return ResponseEntity.ok(paymentService.getAll());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping
  ResponseEntity<List<Payment>> update(@RequestBody Payment payment) {
    paymentService.update(payment);
    return ResponseEntity.ok(paymentService.getAll());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("{id}")
  ResponseEntity<List<Payment>> update(@PathVariable Long id) {
    paymentService.delete(id);
    return ResponseEntity.ok(paymentService.getAll());
  }
}
