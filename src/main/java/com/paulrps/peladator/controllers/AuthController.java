package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.dto.LoginFormDto;
import com.paulrps.peladator.domain.dto.TokenDto;
import com.paulrps.peladator.services.impl.AuthService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private static AuthService authService;

  @Autowired
  public AuthController(final AuthService authService) {
    AuthController.authService = authService;
  }

  @PostMapping
  public ResponseEntity<TokenDto> auth(@RequestBody @Valid LoginFormDto dto) {

    return ResponseEntity.ok(authService.getToken(dto));
  }
}
