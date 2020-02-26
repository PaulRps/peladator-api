package com.paulrps.peladator.repositories

import com.paulrps.peladator.domain.entities.Payment
import com.paulrps.peladator.domain.entities.Player
import com.paulrps.peladator.domain.enums.PlayerLevelEnum
import com.paulrps.peladator.domain.enums.PlayerPositionEnum
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import static org.assertj.core.api.Assertions.assertThat

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class PaymentRepositoryIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private PaymentRepository repository;

  @Test
  public void testFindByDateAndPlayerIn() {
//    given
    def player = Player.builder()
      .name("Messi")
      .age(34)
      .shirtNumber(10)
      .skillLevel(PlayerLevelEnum.JOGA_MUITO)
      .position(PlayerPositionEnum.ATA)
      .build();

    def date = new Date();

    def payment = Payment.builder()
      .player(player)
      .value(50)
      .date(date)
      .build();

    entityManager.persist(player)
    entityManager.persist(payment);
    entityManager.flush();

//    when
    def findByDateAndPlayerIn = repository.findByDateAndPlayerIn(date, Arrays.asList(player));

//    then
    assertThat(findByDateAndPlayerIn.get(0).getPlayer().getName()).isEqualTo(player.getName());

  }
}
