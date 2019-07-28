package com.paulrps.peladator.config.security;

import com.paulrps.peladator.domain.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${api.jwt.expiration}")
    private Long expiration;

    @Value("${api.jwt.secret}")
    private String secret;

    public String createToken(Authentication auth){
        User user = (User) auth.getPrincipal();
        Date today = new Date();
        //TODO: change sum of dates
        Date expiration = new Date(today.getTime() + this.expiration);
        return Jwts.builder()
                .setIssuer("peladator API")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
