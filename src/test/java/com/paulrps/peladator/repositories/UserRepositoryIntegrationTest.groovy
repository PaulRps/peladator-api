package com.paulrps.peladator.repositories

import com.paulrps.peladator.domain.entities.User
import com.paulrps.peladator.domain.enums.RoleEnum
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
class UserRepositoryIntegrationTest {
  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserRepository repository;
//  spring.profiles.active=test
  @Test
  public void testFindByName() {
//    given
    User user = User.builder()
      .name("Paulo")
      .password("password")
      .role(RoleEnum.ROLE_ADMIN)
      .build();
    entityManager.persist(user);
    entityManager.flush();

//    when
    Optional<User> findByName = repository.findByName(user.getUsername());

//    then
    assertThat(findByName.get().getName()).isEqualTo(user.getUsername());
  }
}
