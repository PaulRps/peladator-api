package com.paulrps.peladator.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.services.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@GetMapping("/{id}")
	Payment getPlayer(@PathVariable(value="id") Long id) {
		
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
		
		return addPayment;
	}
	
	@GetMapping("/")
	List<Payment> getAllPlayers() {
		return paymentService.getAllPayments();
	}
	
	@PostMapping("/")
	Payment addPlayer(@RequestBody Payment payment) {
		return paymentService.addPayment(payment);		
	}
}
