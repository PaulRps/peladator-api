package com.paulrps.peladator.services.impl;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.repositories.PaymentRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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
    Payment update = service.update(payment);

    //    then
    assertThat(update).isNotNull();
  }

  @Test
  void delete() {
    //    given
    Long id = 1l;
    payment.setId(id);
    given(repository.findById(id)).willReturn(Optional.ofNullable(payment));

    //    when
    boolean delete = service.delete(id);

    //    then
    assertThat(delete).isTrue();
    assertThat(service.delete(-1l)).isFalse();
    assertThrows(RuntimeException.class, () -> service.delete(null));
  }

  @Test
  void getAll() {
    //    given
    List<Payment> payments = Arrays.asList(payment, payment);
    given(repository.findAll()).willReturn(payments);

    //    when
    List<Payment> all = service.getAll();

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
    Payment one = service.getOne(id);

    //    then
    assertThat(one).isNotNull();
    assertThat(service.getOne(-1l)).isNull();
    assertThrows(RuntimeException.class, () -> service.getOne(null));
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
    List<Payment> byPlayersAndDate = service.findByPlayersAndDate(monthValue, players);

    //    then
    assertThat(byPlayersAndDate).isNotNull().isNotEmpty().hasSize(2);
  }
}
