package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.dto.PaymentFormData;
import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.services.PaymentService;
import com.paulrps.peladator.services.PlayerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
    origins = {"http://localhost:4200", "https://peladator.netlify.com"},
    maxAge = 3600)
@RestController
@RequestMapping("payment")
public class PaymentController {

  private static PaymentService paymentService;
  private static PlayerService playerService;

  @Autowired
  PaymentController(final PaymentService paymentService, final PlayerService playerService) {
    PaymentController.paymentService = paymentService;
    PaymentController.playerService = playerService;
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("form-data")
  public ResponseEntity<PaymentFormData> getFormData() {
    return ResponseEntity.ok(paymentService.formData(playerService.findAll()));
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping
  public ResponseEntity<List<Payment>> getAll() {
    return ResponseEntity.ok(paymentService.findAll());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  public ResponseEntity<Payment> save(@RequestBody Payment payment) {
    return new ResponseEntity(paymentService.save(payment), HttpStatus.CREATED);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping
  public ResponseEntity<Void> update(@RequestBody Payment payment) {
    paymentService.update(payment);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("{id}")
  public ResponseEntity<Void> update(@PathVariable Long id) {
    paymentService.delete(id);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}
