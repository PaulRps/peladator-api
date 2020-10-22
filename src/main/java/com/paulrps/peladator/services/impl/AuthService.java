package com.paulrps.peladator.services.impl;

import com.paulrps.peladator.config.exceptions.ApiException;
import com.paulrps.peladator.config.exceptions.ApiMessageEnum;
import com.paulrps.peladator.domain.dto.LoginFormDto;
import com.paulrps.peladator.domain.dto.TokenDto;
import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.services.TokenService;
import com.paulrps.peladator.services.UserService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService implements UserDetailsService {

  private static UserService userService;
  private static AuthenticationManager authManager;
  private static TokenService tokenService;

  @Autowired
  AuthService(
      final UserService userService,
      final AuthenticationManager authManager,
      final TokenService tokenService) {
    AuthService.userService = userService;
    AuthService.authManager = authManager;
    AuthService.tokenService = tokenService;
  }

  @Override
  public UserDetails loadUserByUsername(String name) {
    log.debug("loading user[name={}]", name);
    Optional<User> user = userService.find(name);

    if (user.isPresent()) {
      log.debug("user found");
      return user.get();
    }

    log.error("user {} not found", name);
    throw new ApiException(ApiMessageEnum.ERROR_AUTHENTICATION_USER_NOT_FOUND, name);
  }

  public TokenDto getToken(LoginFormDto dto) {

    UsernamePasswordAuthenticationToken loginData = dto.convert();
    Authentication authenticate = authManager.authenticate(loginData);

    try {
      log.debug("creating token to user {}", dto.getUserName());
      String token = tokenService.createToken(authenticate);
      User user = (User) authenticate.getPrincipal();
      log.debug("token created");
      return (TokenDto.builder()
          .id(user.getId())
          .value(token)
          .type(tokenService.getTokenType())
          .role(user.getRole())
          .build());
    } catch (AuthenticationException e) {
      log.error("error on token creation", e);
      throw new ApiException(ApiMessageEnum.ERROR_TOKEN_CREATION, dto.getUserName());
    }
  }
}
