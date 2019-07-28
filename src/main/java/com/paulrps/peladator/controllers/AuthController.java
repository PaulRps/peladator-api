package com.paulrps.peladator.controllers;

import com.paulrps.peladator.config.security.TokenService;
import com.paulrps.peladator.domain.dto.LoginFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid LoginFormDto dto){

        UsernamePasswordAuthenticationToken loginData = dto.convert();

        try {

            Authentication authenticate = authManager.authenticate(loginData);
            String token = tokenService.createToken(authenticate);
            System.out.println(token);
            return ResponseEntity.ok().build();

        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }

    }
}
