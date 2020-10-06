package com.paulrps.peladator.services;

import org.springframework.security.core.Authentication;

public interface TokenService {

  String createToken(Authentication auth);

  boolean isValidToken(String token);

  Long getUserId(String token);

  String getTokenType();
}
