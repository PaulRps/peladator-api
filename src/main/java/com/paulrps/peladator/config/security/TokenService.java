package com.paulrps.peladator.config.security;

import com.paulrps.peladator.domain.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  public static final String TOKEN_TYPE = "Bearer";

  @Value("${api.jwt.expiration}")
  private Long expiration;

  @Value("${api.jwt.secret}")
  private String secret;

  public String createToken(Authentication auth) {
    User user = (User) auth.getPrincipal();
    Date today = new Date();
    // TODO: change sum of dates
    Date expiration = new Date(today.getTime() + this.expiration);
    return Jwts.builder()
        .setIssuer("peladator API")
        .setSubject(user.getId().toString())
        .setIssuedAt(today)
        .setExpiration(expiration)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public boolean isValidToken(String token) {

    try {

      Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      return true;

    } catch (Exception e) {
      return false;
    }
  }

  public Long getUserId(String token) {
    Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    return Long.valueOf(body.getSubject());
  }
}
