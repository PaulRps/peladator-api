package com.paulrps.peladator.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.paulrps.peladator.domain.entities.Payment;
import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class PaymentRepositoryIntegrationTest {

  @Autowired private TestEntityManager entityManager;

  @Autowired private PaymentRepository repository;

  @Test
  public void testFindByDateAndPlayerIn() {
    //    given
    Player player =
        Player.builder()
            .name("Messi")
            .age(34)
            .shirtNumber(10)
            .skillLevel(PlayerLevelEnum.JOGA_MUITO)
            .position(PlayerPositionEnum.ATA)
            .build();

    Date date = new Date();

    Payment payment = Payment.builder().player(player).value(50.0).date(date).build();

    entityManager.persist(player);
    entityManager.persist(payment);
    entityManager.flush();

    //    when
    List<Payment> findByDateAndPlayerIn =
        repository.findByDateAndPlayerIn(
            LocalDate.now().getMonthValue(), Arrays.asList(player.getId()));

    //    then
    assertThat(findByDateAndPlayerIn.get(0).getPlayer().getName()).isEqualTo(player.getName());
  }
}
