package com.paulrps.peladator.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.paulrps.peladator.domain.dto.PlayerFormDto;
import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import com.paulrps.peladator.repositories.PlayerResository;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
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
    service.update(player);

    //    then
    //    assertThat().isNotNull();
  }

  @Test
  void delete() {
    Long id = 1l;
    player.setId(id);
    given(repository.findById(id)).willReturn(Optional.of(player));

    //    when

    //    then
    assertDoesNotThrow(() -> service.delete(id));
    assertThrows(RuntimeException.class, () -> service.delete(null));
  }

  @Test
  void getOneById() {
    // given
    Long id = 1l;
    player.setId(id);
    given(repository.findById(id)).willReturn(Optional.of(player));

    //    when
    Player found = service.find(id);

    // then
    assertThat(found).isNotNull();
    assertThat(found.getId()).isEqualTo(id);
    assertThrows(RuntimeException.class, () -> service.find(null));
  }

  @Test
  void getAll() {
    //    given
    List<Player> players = Arrays.asList(player, new Player(), new Player());
    List<Payment> payments = Arrays.asList(Payment.builder().player(player).build());

    given(repository.findAll()).willReturn(players);
    given(paymentService.findAll(LocalDate.now().getMonthValue(), players)).willReturn(payments);

    //    when
    List<Player> all = service.findAll();

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

  //  @Disabled // TODO: fix error
  @Test
  void groupByPositionAndSort() throws ParseException {
    //    given
    player.setPosition(PlayerPositionEnum.GK);
    player.setId(1l);
    List<Player> players =
        Arrays.asList(
            player,
            Player.builder().id(2l).position(PlayerPositionEnum.ATA).build(),
            Player.builder().id(3l).position(PlayerPositionEnum.MDC).build());
    List<Payment> payments = Arrays.asList(Payment.builder().player(player).build());

    given(repository.findAll()).willReturn(players);
    given(service.findAll()).willReturn(players);
    given(paymentService.findAll(LocalDate.now().getMonthValue(), players)).willReturn(payments);

    //    when
    Map<String, List<Player>> map = service.groupByPositionAndSort();

    // then
    assertThat(map).isNotNull().isNotEmpty();
    assertThat(map.get(PlayerPositionEnum.GK.getName()).size()).isEqualTo(1);
    assertThat(map.get(PlayerPositionEnum.ATA.getName()).size()).isEqualTo(1);
    assertThat(map.get(PlayerPositionEnum.MDC.getName()).size()).isEqualTo(1);
  }

  @Test
  void formData() {

    //    given

    //    when
    PlayerFormDto playerFormDto = service.formData();

    //    then
    assertThat(playerFormDto).isNotNull();
    assertThat(playerFormDto.getPositions())
        .isNotNull()
        .isNotEmpty()
        .hasSize(PlayerPositionEnum.values().length);
    assertThat(playerFormDto.getSkillLevels())
        .isNotNull()
        .isNotEmpty()
        .hasSize(PlayerLevelEnum.values().length);
  }
}
