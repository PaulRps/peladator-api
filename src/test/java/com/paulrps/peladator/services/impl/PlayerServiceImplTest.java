package com.paulrps.peladator.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import com.paulrps.peladator.repositories.PlayerResository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerServiceImplTest implements ServiceTest {

  @Mock PlayerResository repository;
  @InjectMocks PlayerServiceImpl service;
  @Mock PaymentServiceImpl paymentService;

  Player player;

  @BeforeEach
  void setUp() {
    player = new Player();
  }

  @Test
  void save() {
    //    given
    player.setId(1l);
    given(repository.save(player)).willReturn(player);

    //    when
    Player save = service.save(player);

    //    then
    assertThat(save).isNotNull();
    assertThat(save.getId()).isNotNull();
    assertThrows(RuntimeException.class, () -> service.save(null));
  }

  @Test
  void update() {
    //    given
    player.setId(1l);
    given(repository.save(player)).willReturn(player);

    //    when
    Player update = service.update(player);

    //    then
    assertThat(update).isNotNull();
  }

  @Test
  void delete() {
    Long id = 1l;
    player.setId(id);
    given(repository.findById(id)).willReturn(Optional.of(player));
    //    given(repository.findById(null)).willThrow(RuntimeException.class);

    //    when
    boolean delete = service.delete(id);

    //    then
    assertThat(delete).isEqualTo(true);
    assertThrows(RuntimeException.class, () -> service.delete(null));
  }

  @Test
  void getOneById() {
    // given
    Long id = 1l;
    player.setId(id);
    given(repository.findById(id)).willReturn(Optional.of(player));

    //    when
    Player found = service.getOne(id);

    // then
    assertThat(found).isNotNull();
    assertThat(found.getId()).isEqualTo(id);
    assertThrows(RuntimeException.class, () -> service.getOne(null));
  }

  @Test
  void getAll() throws ParseException {
    //    given
    List<Player> players = Arrays.asList(player, new Player(), new Player());
    List<Payment> payments = Arrays.asList(Payment.builder().player(player).build());
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    given(repository.findAll()).willReturn(players);
    given(paymentService.findByPlayersAndDate(format.parse(format.format(new Date())), players))
        .willReturn(payments);

    //    when
    List<Player> all = service.getAll();

    //    then
    assertThat(all).isNotNull().isNotEmpty();
    assertThat(all.size()).isEqualTo(players.size());
  }

  @Test
  void getPlayerPositions() {
    //    given

    //    when
    List<PlayerPositionEnum> playerPositions = service.getPlayerPositions();

    //    then
    assertThat(playerPositions).isNotNull().isNotEmpty();
  }

  @Test
  void getPlayerLevels() {

    //    given

    //    when
    List<PlayerLevelEnum> playerLevels = service.getPlayerLevels();

    //    then
    assertThat(playerLevels).isNotNull().isNotEmpty();
  }

  @Disabled // TODO: fix error
  @Test
  void groupByPositionAndSort() throws ParseException {
    //    given
    player.setPosition(PlayerPositionEnum.GK);
    List<Player> players =
        Arrays.asList(
            player,
            Player.builder().position(PlayerPositionEnum.ATA).build(),
            Player.builder().position(PlayerPositionEnum.MDC).build());
    List<Payment> payments = Arrays.asList(Payment.builder().player(player).build());
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    given(paymentService.findByPlayersAndDate(format.parse(format.format(new Date())), players))
        .willReturn(payments);
    given(service.getAll()).willReturn(players);

    //    when
    Map<String, List<Player>> map = service.groupByPositionAndSort();

    // then
    assertThat(map).isNotNull().isNotEmpty();
    assertThat(map.get(PlayerPositionEnum.GK.getName()).size()).isEqualTo(1);
    assertThat(map.get(PlayerPositionEnum.ATA.getName()).size()).isEqualTo(1);
    assertThat(map.get(PlayerPositionEnum.MDC.getName()).size()).isEqualTo(1);
  }
}
