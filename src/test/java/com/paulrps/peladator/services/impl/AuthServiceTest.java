package com.paulrps.peladator.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthServiceTest {

  @Mock private UserService userService;
  @InjectMocks private AuthService service;

  private com.paulrps.peladator.domain.entities.User user;
  private final String TESTE_NAME = "teste";

  @BeforeEach
  void setUp() {
    user = User.builder().name(TESTE_NAME).build();
  }

  @Test
  void loadUserByUsername() {

    given(userService.getOne(TESTE_NAME)).willReturn(java.util.Optional.ofNullable(user));

    UserDetails userDetails = service.loadUserByUsername(TESTE_NAME);

    assertThat(userDetails).isNotNull();
    assertThat(userDetails.getUsername()).isNotNull().isNotEmpty().isEqualTo(TESTE_NAME);
  }
}
