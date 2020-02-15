package com.paulrps.peladator.services;

import com.paulrps.peladator.domain.entities.Payment;

import java.util.List;

public interface PaymentService {

    public Payment save(Payment payment);

    public void update(Payment payment);

    public boolean delete(Long id);

    public List<Payment> getAll();

    public Payment getOne(Long id);
}
