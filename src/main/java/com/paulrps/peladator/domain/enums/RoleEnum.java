package com.paulrps.peladator.domain.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
  ROLE_ADMIN,
  ROLE_USER,
  ROLE_GUEST;

  @Override
  public String getAuthority() {
    return name();
  }
}
