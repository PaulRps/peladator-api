package com.paulrps.peladator.repositories;

import com.paulrps.peladator.domain.entities.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

  @Query(
      "SELECT p "
          + "FROM Payment p JOIN p.player py "
          + "WHERE month(p.date) = ?1 AND (py.id IN (?2))")
  List<Payment> findByDateAndPlayerIn(Integer month, List<Long> ids);
}
