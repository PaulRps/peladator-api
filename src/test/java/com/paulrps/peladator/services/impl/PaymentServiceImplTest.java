package com.paulrps.peladator.services.impl;

import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentServiceImplTest implements ServiceTest {

  @Mock PaymentRepository repository;
  @InjectMocks PaymentServiceImpl service;

  Payment payment;

  @BeforeEach
  void setUp() {
    payment = new Payment();
  }

  @Test
  void save() {
    //    given
    given(repository.save(payment)).willReturn(payment);

    //    when
    Payment save = service.save(payment);

    //    then
    assertThat(save).isNotNull();
    assertThrows(RuntimeException.class, () -> service.save(null));
  }

  @Test
  void update() {
    //    given
    given(repository.save(payment)).willReturn(payment);

    //    when

    //    then
    assertDoesNotThrow(() -> service.update(payment));
  }

  @Test
  void delete() {
    //    given
    Long id = 1l;
    payment.setId(id);
    given(repository.findById(id)).willReturn(Optional.ofNullable(payment));

    //    when

    //    then
    assertDoesNotThrow(() -> service.delete(id));
    assertThrows(RuntimeException.class, () -> service.delete(null));
  }

  @Test
  void getAll() {
    //    given
    List<Payment> payments = Arrays.asList(payment, payment);
    given(repository.findAll()).willReturn(payments);

    //    when
    List<Payment> all = service.findAll();

    //    then
    assertThat(all).isNotNull().isNotEmpty().hasSize(payments.size());
  }

  @Test
  void getOne() {
    //    given
    Long id = 1l;
    payment.setId(1l);
    given(repository.findById(id)).willReturn(Optional.ofNullable(payment));

    //    when
    Payment one = service.find(id);

    //    then
    assertThat(one).isNotNull();
    assertThat(service.find(-1l)).isNull();
    assertThrows(RuntimeException.class, () -> service.find(null));
  }

  @Test
  void findByPlayersAndDate() {
    //    given
    int monthValue = LocalDate.now().getMonthValue();
    List<Payment> payments = Arrays.asList(payment, payment);
    List<Player> players =
        Arrays.asList(Player.builder().id(1l).build(), Player.builder().id(2l).build());
    given(
            repository.findByDateAndPlayerIn(
                monthValue, players.stream().map(Player::getId).collect(toList())))
        .willReturn(payments);

    //    when
    List<Payment> byPlayersAndDate = service.findAll(monthValue, players);

    //    then
    assertThat(byPlayersAndDate).isNotNull().isNotEmpty().hasSize(2);
  }
}
