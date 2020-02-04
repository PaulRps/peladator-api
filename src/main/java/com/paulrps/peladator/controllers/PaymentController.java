package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/{id}")
    ResponseEntity<Payment> getPlayer(@PathVariable(value = "id") Long id) {

        Player p = new Player();
        p.setId(1l);
        p.setName("Joao");
        p.setAge(30);

        Payment py = new Payment();

        py.setDate(new Date());
//		py.setId(id);
        py.setPlayer(p);
        py.setValue(10.0);

        Payment addPayment = paymentService.addPayment(py);

        return ResponseEntity.ok(addPayment);
    }

    @GetMapping
    ResponseEntity<List<Payment>> getAllPlayers() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @PostMapping
    ResponseEntity<Payment> addPlayer(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.addPayment(payment));
    }
}
