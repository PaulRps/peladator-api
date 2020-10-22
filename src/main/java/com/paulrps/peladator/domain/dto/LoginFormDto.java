package com.paulrps.peladator.domain.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
public class LoginFormDto {

  @NotBlank(message = "user name is required")
  private String userName;

  @NotBlank(message = "user password is required")
  private String password;

  public UsernamePasswordAuthenticationToken convert() {
    return new UsernamePasswordAuthenticationToken(this.userName, this.password);
  }
}
