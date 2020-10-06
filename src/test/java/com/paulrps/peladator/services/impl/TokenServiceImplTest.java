package com.paulrps.peladator.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.paulrps.peladator.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TokenServiceImplTest implements ServiceTest {

  @InjectMocks TokenServiceImpl service;
  @Mock Authentication auth;

  private User user;
  private final String TOKEN_TYPE = "Bearer";

  @BeforeEach
  void setUp() {

    user = User.builder().id(1l).build();

    ReflectionTestUtils.setField(service, "expiration", 86400000l);
    ReflectionTestUtils.setField(service, "secret", "secret");
    ReflectionTestUtils.setField(service, "tokenType", TOKEN_TYPE);
  }

  @Test
  void createToken() {
    //    given
    User user = User.builder().id(1l).build();
    given(auth.getPrincipal()).willReturn(user);

    //    when
    String token = service.createToken(auth);

    //    then
    assertThat(token).isNotNull().isNotEmpty();
  }

  @Test
  void isValidToken() {

    given(auth.getPrincipal()).willReturn(user);
    String token = service.createToken(auth);

    assertThat(service.isValidToken(token)).isTrue();
    assertThat(service.isValidToken(null)).isFalse();
  }

  @Test
  void getUserId() {
    given(auth.getPrincipal()).willReturn(user);
    String token = service.createToken(auth);

    assertThat(service.getUserId(token)).isNotNull().isNotNegative().isEqualTo(user.getId());
  }

  @Test
  void getTokenType() {

    assertThat(service.getTokenType()).isNotNull().isNotEmpty().isEqualTo(TOKEN_TYPE);
  }
}
